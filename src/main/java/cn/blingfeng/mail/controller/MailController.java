package cn.blingfeng.mail.controller;

import cn.blingfeng.commons.pojo.WorkResult;
import cn.blingfeng.commons.vo.MailQueryVo;
import cn.blingfeng.mail.pojo.Mail;
import cn.blingfeng.mail.service.MailService;
import cn.blingfeng.user.pojo.User;
import cn.blingfeng.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : blingfeng
 * @date : 2017/10/20
 * @description
 **/
@RequestMapping("mail")
@Controller
public class MailController {
    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;

    @RequestMapping()
    public String mailBoxList(MailQueryVo mailQueryVo, Model model) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        mailQueryVo.setUserId(user.getId());
        MailQueryVo mailQueryBean = mailService.getMailListByQueryBean(mailQueryVo);
        model.addAttribute("mailQueryBean", mailQueryBean);
        return "/mail/mailbox";
    }

    @RequestMapping("/read/{mailId}")
    public String readMail(@PathVariable Long mailId, Model model) {
        /**
         * 改邮件状态为已读
         */
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        Mail mail = mailService.getContentByMailId(user.getId(), mailId);
        model.addAttribute("mail", mail);
        return "/mail/read-mail";
    }

    @RequestMapping("/send")
    public String send() {
        return "/mail/send-mail";
    }

    @RequestMapping("/newmail")
    @ResponseBody
    public WorkResult newMail() {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        WorkResult result = mailService.getnewMailCount(user.getId());
        return result;
    }

    @RequestMapping("/sendMail")
    @ResponseBody
    public WorkResult  sendMail(Mail mail){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        mail.setSendUserid(user.getId());
        WorkResult result = mailService.sendMail(mail);
        return result;
    }
    @RequestMapping("/checkUserExit")
    @ResponseBody
    public Boolean checkUserExist(String receiveUsername){
        Boolean isExist = userService.checkUserExistByUsername(receiveUsername);
        return isExist;

    }
    @RequestMapping("/sendbox")
    public String sendBoxList(MailQueryVo mailQueryVo,Model model){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        mailQueryVo.setUserId(user.getId());
        MailQueryVo MailQueryVo = mailService.getSendMailList(mailQueryVo);
        model.addAttribute("mailQueryBean",MailQueryVo);
        return "/mail/sendbox";
    }
    @RequestMapping("/trashbox")
    public String trashbox(MailQueryVo mailQueryVo,Model model){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        mailQueryVo.setUserId(user.getId());
        MailQueryVo MailQueryVo = mailService.getSendMailList(mailQueryVo);
        model.addAttribute("mailQueryBean",MailQueryVo);
        return "/mail/trashbox";
    }
}
