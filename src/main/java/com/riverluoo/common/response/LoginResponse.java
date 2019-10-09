package com.riverluoo.common.response;

import com.riverluoo.entity.LuooUser;
import lombok.Data;

/**
 * @auther: wangyang
 * @since: 2019-09-26 16:18
 */
@Data
public class LoginResponse {

    private LuooUser luooUser;

    private String luooToken;

    public LoginResponse() {
    }

    public LoginResponse(LuooUser luooUser, String luooToken) {
        this.luooUser = luooUser;
        this.luooToken = luooToken;
    }
}
