package com.he.mq;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

public class RocketmqConsumer {
	
	public static void main(String[] args) {
		
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("PushConsumer");
		consumer.setNamesrvAddr("192.168.8.119:6789");
		
		try {
			
			consumer.subscribe("PushTopic", "push");
			consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
			
			consumer.registerMessageListener(new MessageListenerConcurrently() {
				@Override
				public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
					MessageExt messageExt = msgs.get(0);
					System.out.println(messageExt.toString());
					return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
				}
			});
			
			consumer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
