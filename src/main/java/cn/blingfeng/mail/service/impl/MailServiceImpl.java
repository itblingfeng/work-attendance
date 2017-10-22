package cn.blingfeng.mail.service.impl;

import cn.blingfeng.commons.pojo.WorkResult;
import cn.blingfeng.commons.vo.MailQueryVo;
import cn.blingfeng.mail.mapper.MailMapper;
import cn.blingfeng.mail.pojo.Mail;
import cn.blingfeng.mail.service.MailService;
import cn.blingfeng.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author : blingfeng
 * @date : 2017/10/20
 * @description
 **/
@Service
public class MailServiceImpl implements MailService{
    @Autowired
    private MailMapper mailMapper;
    @Autowired
    private UserMapper userMapper;

    private final Byte MAIL_STATUS_UNDELETE = 0;

    private final Byte MAIL_STATUS_DELETE = 1;

    private final Byte MAIL_STATUS_UNREAD = 0;

    private final Byte MAIL_STATUS_READ = 1;

    private final Byte MAIL_IS_SEND = 1;

    private final Byte MAIL_IS_REC = 2;
    @Override
    public MailQueryVo getMailListByQueryBean(MailQueryVo mailQueryVo) {
        /**
         * 先查询邮件记录的总量，然后返回设置总量
         * 查询所有邮件的记录
         * */
        mailQueryVo.setIsSend(MAIL_IS_REC);
        int validMailCount = mailMapper.selectValidCount(mailQueryVo.getUserId(), MAIL_STATUS_UNDELETE,MAIL_IS_REC);
        mailQueryVo.setTotalRows(validMailCount);
        if(validMailCount <=0){
            return mailQueryVo;
        }
        mailQueryVo.setIsDel(MAIL_STATUS_UNDELETE);
        List<Mail> mailList = mailMapper.selectVaildList(mailQueryVo);
        mailQueryVo.setItems(mailList);
        return mailQueryVo;
    }

    @Override
    public WorkResult getnewMailCount(Long userId) {
        int newMailCount = mailMapper.selectUnReadMailCount(userId, MAIL_STATUS_UNDELETE, MAIL_STATUS_UNREAD,MAIL_IS_REC);
        return WorkResult.ok(newMailCount);
    }

    @Override
    public Mail getContentByMailId(Long userId, Long mailId,Integer flag) {
        Mail mail ;
        if(flag ==1) {
         mail=  mailMapper.selectMailByMailId(userId, mailId, MAIL_IS_SEND);
        }else{
            mail = mailMapper.selectMailByMailId(userId, mailId, MAIL_IS_REC);
        }
        mail.setStatus(MAIL_STATUS_READ);
        mailMapper.updateByPrimaryKeySelective(mail);
        return mail;
    }

    /**
     * 用户名不存在时的bug处理
     * @param mail
     * @return
     */
    @Override
    public WorkResult sendMail(Mail mail) {
        mail.setStatus(MAIL_STATUS_UNREAD);
        mail.setIsDel(MAIL_STATUS_UNDELETE);
        mail.setSendTime(new Date());
       try {
           Long receiveUserId = userMapper.selectUserIdByUsername(mail.getReceiveUsername());
           mail.setReceiveUserid(receiveUserId);
           mail.setIsSend(MAIL_IS_REC);
           mailMapper.insertSelective(mail);
           mail.setIsSend(MAIL_IS_SEND);
           mailMapper.insertSelective(mail);
       }catch(Exception e){
           throw e;
       }

        return WorkResult.ok();
    }

    @Override
    public MailQueryVo getSendMailList(MailQueryVo mq) {
        mq.setIsSend(MAIL_IS_SEND);
        int validMailCount = mailMapper.selectValidSendCount(mq.getUserId(),mq.getIsSend());
        mq.setTotalRows(validMailCount);
        if(validMailCount <=0){
            return mq;
        }
        List<Mail> mailList = mailMapper.selectVaildSendList(mq);
        mq.setItems(mailList);
        return mq;
    }

    @Override
    public WorkResult deleteMail(Long[] ids) {
        for(Long id :ids) {
            Mail mail = new Mail();
            mail.setMailid(id);
            mail.setIsDel(MAIL_STATUS_DELETE);
           mailMapper.updateByPrimaryKeySelective(mail);
        }
        return WorkResult.ok("删除成功！");
    }

    @Override
    public MailQueryVo getTrashMailList(MailQueryVo mailQueryVo) {
        mailQueryVo.setIsSend(MAIL_IS_REC);
        int validMailCount = mailMapper.selectValidCount(mailQueryVo.getUserId(), MAIL_STATUS_DELETE,MAIL_IS_REC);
        mailQueryVo.setTotalRows(validMailCount);
        if(validMailCount <=0){
            return mailQueryVo;
        }
        mailQueryVo.setIsDel(MAIL_STATUS_DELETE);
        List<Mail> mailList = mailMapper.selectVaildList(mailQueryVo);
        mailQueryVo.setItems(mailList);
        return mailQueryVo;
    }

}
