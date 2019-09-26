package com.riverluoo.common.event.business;

import lombok.Data;

/**
 * @auther: wangyang
 * @since: 2019-09-26 15:05
 */
@Data
public class UserEvent {

   private String phone;

    public UserEvent(String phone) {
        this.phone = phone;
    }

    public UserEvent() {
    }
}
