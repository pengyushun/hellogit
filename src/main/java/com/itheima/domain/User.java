package com.itheima.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.UUID;

//lombok

@Data
@TableName("tbl_user")
public class User {
//    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @TableField(value = "name")
    private String name;
    @TableField(select = false)
    private String password;
    private Integer age;
    private String tel;
    @TableField(exist = false)
    private String Oline;
    @TableLogic(value = "0",delval = "1")
    private Integer deleted;

    @Version
    private Integer version;
}
