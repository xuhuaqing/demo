package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @DESCRIPTION
 * @Author: liu hang
 * @Date: 2020/7/10 10:02 上午
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendMessage {
    /**
     * 租户Id
     */
    private String tenantId;
    /**
     * 业务id
     */
    private String bizId;
    /**
     * 消息类型（如：taskSuccess）
     */
    private String messageType;
    /**
     * 消息子类型（如：工单的分类）
     */
    private String messageSonType;
    /**
     * 模板所需参数的map
     */
    private Map<String, String> messageBody;
    /**
     * 接受人
     */
    private List<Receiver> receiver;
    /**
     * 1.发送钉钉消息 并发送应用内通知
     * 2.只发送钉钉内消息 不发送应用内通知
     */
    private Integer saveRecord;
}
