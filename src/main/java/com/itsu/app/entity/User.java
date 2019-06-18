package com.itsu.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created by Su Ben on 2019/6/17
 */
@TableName("tb_user")
@Data
public class User {
    @TableId("uid")
    private int uid;
    private String userName;
    private String password;
    private int status;
}
