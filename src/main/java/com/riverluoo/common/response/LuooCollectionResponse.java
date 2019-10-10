package com.riverluoo.common.response;

import com.riverluoo.entity.LuooCollection;
import lombok.Data;

import java.util.List;

/**
 * @auther: wangyang
 * @since: 2019-10-10 08:50
 */
@Data
public class LuooCollectionResponse {

    private List<LuooCollection> songList;

    private List<LuooCollection> volumeList;
}
