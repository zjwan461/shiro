package com.itsu.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itsu.app.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Su Ben on 2019/6/17
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select password from tb_user a where user_name = #{value}")
    String selectPwdByUserName(String userName);

    @Select("select status from tb_user a where user_name = #{value}")
    int selectStatusByUserName(String userName);
}
