package com.riverluoo.common.plugin;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.riverluoo.service.LuooUserService;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @auther: wangyang
 * @since: 2019-09-26 15:51
 */

public class ColumnFillHandler implements MetaObjectHandler {

    @Autowired
    private LuooUserService luooUserService;

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("createPerson",luooUserService.getUserId(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
        this.setFieldValByName("updatePerson",luooUserService.getUserId(),metaObject);
    }
}
