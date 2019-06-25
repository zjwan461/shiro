package com.itsu.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author: 苏犇
 * @date: 2019/6/19 1:33
 */
@TableName("tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 5654948216481582756L;
    @TableId("uid")
    private int uid;
    private String userName;
    private String password;
    @TableField(exist = false)
    private boolean rememberMe;
    private int status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
