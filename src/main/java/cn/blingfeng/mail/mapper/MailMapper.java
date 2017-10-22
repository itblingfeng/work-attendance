package cn.blingfeng.mail.mapper;

import cn.blingfeng.commons.vo.MailQueryVo;
import cn.blingfeng.mail.pojo.Mail;

import java.util.List;

public interface MailMapper {
    int deleteByPrimaryKey(Long mailid);

    int insert(Mail record);

    int insertSelective(Mail record);

    Mail selectByPrimaryKey(Long mailid);

    int updateByPrimaryKeySelective(Mail record);

    int updateByPrimaryKeyWithBLOBs(Mail record);

    int updateByPrimaryKey(Mail record);
    int selectValidCount(Long userId, Byte isdel,Byte isSend);

    int selectUnReadMailCount(Long userId, Byte isdel,Byte status,Byte isSend);

    List<Mail> selectVaildList(MailQueryVo mailQueryVo);

    Mail selectMailByMailId(Long userId,Long mailId,Byte isSend);

    int selectValidSendCount(Long userId,Byte isSend);

    List<Mail> selectVaildSendList(MailQueryVo mailQueryVo);

}