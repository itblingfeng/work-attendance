package cn.blingfeng.commons.pojo;

public class WorkResult {
    //    返回状态码
    private Integer status;
    //    返回信息
    private String msg;
    //    返回数据
    private Object data;

    public WorkResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static WorkResult ok() {
        return new WorkResult(200, null, null);
    }

    public static WorkResult error(Integer status, String errorMsg) {
        return new WorkResult(status, errorMsg, null);
    }

    public static WorkResult ok(Object data) {
        return new WorkResult(200, null, data);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
