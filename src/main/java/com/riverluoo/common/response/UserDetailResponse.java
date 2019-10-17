package com.riverluoo.common.response;

import com.riverluoo.entity.LuooUser;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @auther: wangyang
 * @since: 2019-10-17 10:04
 */
@Data
@AllArgsConstructor
public class UserDetailResponse {

    private LuooUser luooUser;

    private Integer registerDay;

    public UserDetailResponse() {
    }
}
