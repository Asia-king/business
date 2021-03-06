package org.zbus.pubsub;

import org.zbus.broker.Broker;
import org.zbus.broker.BrokerConfig;
import org.zbus.broker.SingleBroker;
import org.zbus.mq.Producer;
import org.zbus.mq.Protocol.MqMode;
import org.zbus.net.http.Message;

public class PubSync {
	public static void main(String[] args) throws Exception{   
		BrokerConfig config = new BrokerConfig();
		config.setServerAddress("192.168.1.33:15555");
		final Broker broker = new SingleBroker(config);
		 
		Producer producer = new Producer(broker, "MyPubSub", MqMode.PubSub);
		producer.createMQ();  
		for(int i=0;i<10000;i++){
			Message msg = new Message();
			msg.setTopic("sse"); 
			msg.setBody("hello world");
			
			producer.sendSync(msg);
		}
		
		
		broker.close();
	} 
}
