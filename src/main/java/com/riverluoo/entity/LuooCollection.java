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
 * 收藏歌曲和期刊
 * </p>
 *
 * @author wangyangyang
 * @since 2019-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LuooCollection extends Model<LuooCollection> {

private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 歌曲id
     */
    private Integer songId;

    /**
     * 期刊id
     */
    private Integer volumeId;

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

    public static final String USER_ID = "user_id";

    public static final String SONG_ID = "song_id";

    public static final String VOLUME_ID = "volume_id";

    public static final String CREATE_TIME = "create_time";

    public static final String CREATE_PERSON = "create_person";

    public static final String UPDATE_TIME = "update_time";

    public static final String UPDATE_PERSON = "update_person";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
