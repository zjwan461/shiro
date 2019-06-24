package com.itsu.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: 苏犇
 * @date: 2019/6/19 1:33
 */
@TableName("tb_user")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 5654948216481582756L;
    @TableId("uid")
    private int uid;
    private String userName;
    private String password;
    @TableField(exist = false)
    private boolean rememberMe;
    private int status;
}
