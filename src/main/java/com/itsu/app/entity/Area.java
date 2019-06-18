package com.itsu.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created by Su Ben on 2019/6/17
 */
@Data
@TableName("bas_area")
public class Area {
    @TableId("area_id")
    private int areaId;
    private String areaName;
    private String cityCode;
    private String address;
}
