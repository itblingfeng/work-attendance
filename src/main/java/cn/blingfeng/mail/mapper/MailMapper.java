package cn.blingfeng.mail.mapper;

import cn.blingfeng.commons.utils.PageQueryBean;
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

    int selectValidCount(Long userId, Byte isdel);

    int selectUnReadMailCount(Long userId, Byte isdel,Byte status);

    List<Mail> selectVaildList(PageQueryBean pageQueryBean);

    Mail selectMailByMailId(Long userId,Long mailId);

}