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
    @Override
    public MailQueryVo getMailListByQueryBean(MailQueryVo mailQueryVo) {
        /**
         * 先查询邮件记录的总量，然后返回设置总量
         * 查询所有邮件的记录
         * */
        int validMailCount = mailMapper.selectValidCount(mailQueryVo.getUserId(), MAIL_STATUS_UNDELETE);
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
        int newMailCount = mailMapper.selectUnReadMailCount(userId, MAIL_STATUS_UNDELETE, MAIL_STATUS_UNREAD);
        return WorkResult.ok(newMailCount);
    }

    @Override
    public Mail getContentByMailId(Long userId, Long mailId) {
        Mail mail = mailMapper.selectMailByMailId(userId, mailId);
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
           mailMapper.insertSelective(mail);
       }catch(Exception e){
           throw e;
       }

        return WorkResult.ok();
    }

    @Override
    public MailQueryVo getSendMailList(MailQueryVo mq) {
        int validMailCount = mailMapper.selectValidSendCount(mq.getUserId());
        mq.setTotalRows(validMailCount);
        if(validMailCount <=0){
            return mq;
        }
        List<Mail> mailList = mailMapper.selectVaildSendList(mq);
        mq.setItems(mailList);
        return mq;
    }

}
