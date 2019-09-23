package com.riverluoo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 落网期刊
 * </p>
 *
 * @author wangyangyang
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LuooVolume extends Model<LuooVolume> {

private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 配文
     */
    private String caption;

    /**
     * 链接
     */
    private String url;

    /**
     * 发布时间
     */
    private String releaseTime;

    /**
     * 作者
     */
    private String author;

    /**
     * 点赞
     */
    private Integer star;

    /**
     * 评论
     */
    private Integer remark;

    /**
     * 感谢
     */
    private Integer thank;

    /**
     * 歌曲数
     */
    private Integer number;


    public static final String ID = "id";

    public static final String TITLE = "title";

    public static final String CAPTION = "caption";

    public static final String URL = "url";

    public static final String RELEASE_TIME = "releaseTime";

    public static final String AUTHOR = "author";

    public static final String STAR = "star";

    public static final String REMARK = "remark";

    public static final String THANK = "thank";

    public static final String NUMBER = "number";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
