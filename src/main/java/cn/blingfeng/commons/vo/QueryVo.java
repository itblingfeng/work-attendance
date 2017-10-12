package cn.blingfeng.commons.vo;

import cn.blingfeng.commons.utils.PageQueryBean;
/**
* @Author blingfeng
* @Date 2017/10/11/011
* @Description
**/
public class QueryVo extends PageQueryBean{
    private String startDate;
    private String endDate;
    private String rangeDate;

    public String getRangeDate() {
        return rangeDate;
    }

    public void setRangeDate(String rangeDate) {
        this.rangeDate = rangeDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
