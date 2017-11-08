package cn.blingfeng.mail.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mail {
    private Long mailid;

    private String sendUsername;

    private Long sendUserid;

    private String receiveUsername;

    private Long receiveUserid;

    private String attachment;

    private Byte status;

    private String title;

    private Date sendTime;

    private Byte isDel;

    private String content;

    private Byte isSend;

    public Byte getIsSend() {
        return isSend;
    }

    public void setIsSend(Byte isSend) {
        this.isSend = isSend;
    }

    public String getSendUsername() {
        return sendUsername;
    }

    public void setSendUsername(String sendUsername) {
        this.sendUsername = sendUsername;
    }

    public String getReceiveUsername() {
        return receiveUsername;
    }

    public void setReceiveUsername(String receiveUsername) {
        this.receiveUsername = receiveUsername;
    }

    public Long getMailid() {
        return mailid;
    }

    public void setMailid(Long mailid) {
        this.mailid = mailid;
    }

    public Long getSendUserid() {
        return sendUserid;
    }

    public void setSendUserid(Long sendUserid) {
        this.sendUserid = sendUserid;
    }

    public Long getReceiveUserid() {
        return receiveUserid;
    }

    public void setReceiveUserid(Long receiveUserid) {
        this.receiveUserid = receiveUserid;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment == null ? null : attachment.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getLastTime() throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String fromDate = simpleFormat.format(sendTime);
        String toDate = simpleFormat.format(new Date());
        long from = simpleFormat.parse(fromDate).getTime();
        long to = simpleFormat.parse(toDate).getTime();
        int minutes = (int) ((to - from)/(1000 * 60));
        if(minutes < 60){
            return minutes+" minutes ago";
        }else {
            int hours = (int) ((to - from)/(1000 * 60 * 60));
            if(hours<24){
                return hours +" hours ago";
            }else{
                int days = (int) ((to - from)/(1000 * 60 * 60 * 24));
                return days +" days ago";
            }
        }
    }
}