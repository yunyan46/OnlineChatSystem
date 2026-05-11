package com.im.pojo;

import java.util.Date;

public class User {
    private Integer id;
    private String account;
    private String password;
    private String nickname;
    private String avatar;
    private String signature;
    private Integer gender;
    private Integer status;
    private Date lastLogin;
    private Date createdAt;
    private Date updateAt;

    // 无参、有参、get/set
    public User(){}

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public String getAccount() {return account;}
    public void setAccount(String account) {this.account = account;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public String getNickname() {return nickname;}
    public void setNickname(String nickname) {this.nickname = nickname;}
    public String getAvatar() {return avatar;}
    public void setAvatar(String avatar) {this.avatar = avatar;}
    public String getSignature() {return signature;}
    public void setSignature(String signature) {this.signature = signature;}
    public Integer getGender() {return gender;}
    public void setGender(Integer gender) {this.gender = gender;}
    public Integer getStatus() {return status;}
    public void setStatus(Integer status) {this.status = status;}
    public Date getLastLogin() {return lastLogin;}
    public void setLastLogin(Date lastLogin) {this.lastLogin = lastLogin;}
    public Date getCreatedAt() {return createdAt;}
    public void setCreatedAt(Date createdAt) {this.createdAt = createdAt;}
    public Date getUpdateAt() {return updateAt;}
    public void setUpdateAt(Date updateAt) {this.updateAt = updateAt;}
}