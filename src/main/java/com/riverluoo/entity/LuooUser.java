package com.riverluoo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author wangyangyang
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LuooUser extends Model<LuooUser> {

private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * 头像
     */
    private String photo;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createPerson;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updatePerson;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String PHONE = "phone";

    public static final String PHOTO = "photo";

    public static final String CREATE_TIME = "create_time";

    public static final String CREATE_PERSON = "create_person";

    public static final String UPDATE_TIME = "update_time";

    public static final String UPDATE_PERSON = "update_person";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
