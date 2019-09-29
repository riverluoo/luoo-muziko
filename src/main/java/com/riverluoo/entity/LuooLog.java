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
 * 操作日志
 * </p>
 *
 * @author wangyangyang
 * @since 2019-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LuooLog extends Model<LuooLog> {

private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 操作描述
     */
    private String description;

    private String username;

    /**
     * 操作时间
     */
    private Date startTime;

    /**
     * 消耗时间
     */
    private Integer spendTime;

    /**
     * 根路径
     */
    private String basePath;

    /**
     * URI
     */
    private String uri;

    /**
     * URL
     */
    private String url;

    /**
     * 请求类型
     */
    private String method;

    private String parameter;

    /**
     * 用户标识
     */
    private String userAgent;

    /**
     * IP地址
     */
    private String ip;

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
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updatePerson;


    public static final String ID = "id";

    public static final String DESCRIPTION = "description";

    public static final String USERNAME = "username";

    public static final String START_TIME = "start_time";

    public static final String SPEND_TIME = "spend_time";

    public static final String BASE_PATH = "base_path";

    public static final String URI = "uri";

    public static final String URL = "url";

    public static final String METHOD = "method";

    public static final String PARAMETER = "parameter";

    public static final String USER_AGENT = "user_agent";

    public static final String IP = "ip";

    public static final String CREATE_TIME = "create_time";

    public static final String CREATE_PERSON = "create_person";

    public static final String UPDATE_TIME = "update_time";

    public static final String UPDATE_PERSON = "update_person";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
