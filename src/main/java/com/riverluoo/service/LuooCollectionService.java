package com.riverluoo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.riverluoo.entity.LuooCollection;

/**
 * <p>
 * 收藏歌曲和期刊 服务类
 * </p>
 *
 * @author wangyangyang
 * @since 2019-10-09
 */
public interface LuooCollectionService extends IService<LuooCollection> {

    /**
     * 用户无收藏音乐则新增；有收藏则删除
     *
     * @param songId 音乐id
     * @return
     */
    boolean insertOrDeleteSong(int songId);

    /**
     * 用户无收藏期刊则新增；有收藏则删除
     *
     * @param volumeId 期刊id
     * @return
     */
    boolean insertOrDeleteVolume(int volumeId);



}
