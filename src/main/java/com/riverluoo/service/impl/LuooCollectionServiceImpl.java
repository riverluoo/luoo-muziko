package com.riverluoo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.riverluoo.entity.LuooCollection;
import com.riverluoo.mapper.LuooCollectionMapper;
import com.riverluoo.service.LuooCollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 收藏歌曲和期刊 服务实现类
 * </p>
 *
 * @author wangyangyang
 * @since 2019-10-09
 */
@Service
public class LuooCollectionServiceImpl extends ServiceImpl<LuooCollectionMapper, LuooCollection> implements LuooCollectionService {

    @Autowired
    private LuooCollectionMapper luooCollectionMapper;

    private static final String USER_ID = "fed8ffe27346457c79c7377b82c9fdb4";

    @Override
    public boolean insertOrDeleteSong(int songId) {
        LuooCollection luooCollection = luooCollectionMapper.selectOne(new QueryWrapper<LuooCollection>().eq(LuooCollection.SONG_ID, songId).eq(LuooCollection.USER_ID, USER_ID));
        if (Objects.nonNull(luooCollection)) {
            return luooCollectionMapper.deleteById(luooCollection.getId()) > 0;
        }

        luooCollection = new LuooCollection();
        luooCollection.setSongId(songId);
        luooCollection.setUserId(USER_ID);
        return luooCollectionMapper.insert(luooCollection) > 0;
    }

    @Override
    public boolean insertOrDeleteVolume(int volumeId) {
        LuooCollection luooCollection = luooCollectionMapper.selectOne(new QueryWrapper<LuooCollection>().eq(LuooCollection.VOLUME_ID, volumeId).eq(LuooCollection.USER_ID, USER_ID));
        if (Objects.nonNull(luooCollection)) {
            return luooCollectionMapper.deleteById(luooCollection.getId()) > 0;
        }

        luooCollection = new LuooCollection();
        luooCollection.setVolumeId(volumeId);
        luooCollection.setUserId(USER_ID);
        return luooCollectionMapper.insert(luooCollection) > 0;
    }


}
