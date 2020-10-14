package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @DESCRIPTION
 * @Author: liu hang
 * @Date: 2020/7/14 11:57 上午
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Receiver {
    private String userId;
    private String userName;
    private String phone;
    /**
     * 微信的openId
     */
    private String openId;
    /**
     * 钉钉id
     */
    private String staffId;
}
