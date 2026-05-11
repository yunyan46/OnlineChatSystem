package com.im.pojo;

public class FriendRequest {
    private Integer id;
    private Integer fromUid;
    private Integer toUid;
    private String message;
    private Integer status;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getFromUid() { return fromUid; }
    public void setFromUid(Integer fromUid) { this.fromUid = fromUid; }
    public Integer getToUid() { return toUid; }
    public void setToUid(Integer toUid) { this.toUid = toUid; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}