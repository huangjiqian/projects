package org.tinygame.herostory.mq;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息队列生产者
 */
public final class MQProducer {

    /**
     * 生产者
     */
    private static DefaultMQProducer producer = null;

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MQProducer.class);

    /**
     * 私有化构造器
     */
    private MQProducer(){}

    /**
     * 初始化
     */
    public static void init(){
        try {
            producer = new DefaultMQProducer("heroStory");
            producer.setNamesrvAddr("192.168.163.100:9876");
            producer.start();
            producer.setRetryTimesWhenSendFailed(3);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
        }
    }

    /**
     * 发送消息
     * @param topic 主题
     * @param msg 消息对象
     */
    public static void sendMsg(String topic, Object msg){

        if (null == topic || null == msg) {
            return;
        }

        if (null == producer) {
            throw new RuntimeException("producer 尚未初始化");
        }

        Message mqMsg = new Message();
        mqMsg.setTopic(topic);
        mqMsg.setBody(JSONObject.toJSONBytes(msg));

        try {
            producer.send(mqMsg);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
        }
    }
}
