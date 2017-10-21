package cn.blingfeng.commons.vo;

import cn.blingfeng.commons.utils.PageQueryBean;

/**
 * @author : blingfeng
 * @date : 2017/10/20
 * @description
 **/
public class MailQueryVo extends PageQueryBean{
    private Byte status;

    private Byte isDel;

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
