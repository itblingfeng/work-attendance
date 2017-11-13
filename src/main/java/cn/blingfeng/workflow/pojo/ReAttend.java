package cn.blingfeng.workflow.pojo;

import java.io.Serializable;
import java.util.Date;

public class ReAttend implements Serializable{


    private Long id;

    private Long attendId;


    private Date attendDate;

    private String attendDate_string;

    private String reAttendStarter;

    private Date reAttendEve;

    private Date reAttendMor;

    private String currentHandler;

    private Byte status;

    private String comments;

    private String taskId;

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAttendId() {
        return attendId;
    }

    public void setAttendId(Long attendId) {
        this.attendId = attendId;
    }

    public Date getAttendDate() {
        return attendDate;
    }

    public void setAttendDate(Date attendDate) {
        this.attendDate = attendDate;
    }

    public String getReAttendStarter() {
        return reAttendStarter;
    }

    public void setReAttendStarter(String reAttendStarter) {
        this.reAttendStarter = reAttendStarter == null ? null : reAttendStarter.trim();
    }

    public Date getReAttendEve() {
        return reAttendEve;
    }

    public void setReAttendEve(Date reAttendEve) {
        this.reAttendEve = reAttendEve;
    }

    public Date getReAttendMor() {
        return reAttendMor;
    }

    public void setReAttendMor(Date reAttendMor) {
        this.reAttendMor = reAttendMor;
    }

    public String getCurrentHandler() {
        return currentHandler;
    }

    public void setCurrentHandler(String currentHandler) {
        this.currentHandler = currentHandler == null ? null : currentHandler.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public String getAttendDate_string() {
        return attendDate_string;
    }

    public void setAttendDate_string(String attendDate_string) {
        this.attendDate_string = attendDate_string;
    }

}