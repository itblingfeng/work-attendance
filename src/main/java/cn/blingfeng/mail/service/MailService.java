package cn.blingfeng.mail.service;

import cn.blingfeng.commons.pojo.WorkResult;
import cn.blingfeng.commons.vo.MailQueryVo;
import cn.blingfeng.mail.pojo.Mail;

/**
 * @author : blingfeng
 * @date : 2017/10/20
 * @description
 **/
public interface MailService {
    MailQueryVo getMailListByQueryBean(MailQueryVo pb);

    WorkResult getnewMailCount(Long userId);

    Mail getContentByMailId(Long userId, Long mailId,Integer flag);

    WorkResult sendMail(Mail mail);

    MailQueryVo getSendMailList(MailQueryVo mq);

    WorkResult deleteMail(Long ids[]);

    MailQueryVo getTrashMailList(MailQueryVo mailQueryVo);

}
