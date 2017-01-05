package com.he.mq;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

public class RocketmqProducer {
	public static void main(String[] args) {
		
		DefaultMQProducer producer = new DefaultMQProducer("Producer");
		producer.setNamesrvAddr("192.168.8.119:6789");
		
		try {
			
			producer.start();
			
			Message message = new Message("PushTopic","push","1","Just for test...".getBytes());
			SendResult result = producer.send(message);
			System.out.println(result);
			
			
			
			Message message2 = new Message("PushTopic","push","2","Just for test...".getBytes());
			SendResult result2 = producer.send(message2);
			System.out.println(result2);
			
			
			
			Message message3 = new Message("PullTopic","pull","1","Just for test...".getBytes());
			SendResult result3 = producer.send(message3);
			System.out.println(result3);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
