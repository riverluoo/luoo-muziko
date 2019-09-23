package com.riverluoo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 落网歌曲
 * </p>
 *
 * @author wangyangyang
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LuooSong extends Model<LuooSong> {

private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 期刊id
     */
    private Integer volumeId;

    /**
     * 歌曲顺序
     */
    private Integer sequence;

    /**
     * 专辑
     */
    private String album;

    /**
     * 歌曲
     */
    private String song;

    /**
     * 艺人
     */
    private String artist;

    /**
     * 歌词
     */
    private String lyric;

    /**
     * 时长
     */
    private Double time;

    /**
     * 音乐地址
     */
    private String music;

    /**
     * 封面地址
     */
    private String url;


    public static final String ID = "id";

    public static final String VOLUME_ID = "volume_id";

    public static final String SEQUENCE = "sequence";

    public static final String ALBUM = "album";

    public static final String SONG = "song";

    public static final String ARTIST = "artist";

    public static final String LYRIC = "lyric";

    public static final String TIME = "time";

    public static final String MUSIC = "music";

    public static final String URL = "url";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
