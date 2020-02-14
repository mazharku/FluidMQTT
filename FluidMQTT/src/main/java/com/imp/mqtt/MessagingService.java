/**
 * 
 */
package com.imp.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @author mazhar
 *
 * Jan 29, 2020
 */
public class MessagingService implements IMqttMessageListener {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws MqttException 
	 */
	public static void main(String[] args) throws MqttException, InterruptedException {
		new MessagingService().extracted();

	}
     
	public  void extracted() throws MqttException{
		String broker = "tcp://localhost:1883";
		String clientID = "779";
		String topics ="topic";
		String content ="mazhar";
		MemoryPersistence persistence = new MemoryPersistence();
		//MqttToken token = new MqttToken();
		MqttClient client = new MqttClient(broker, clientID, persistence);
		client.connect();
		//client .subscribe topics, IMqttMessageListener implemented class
		client.subscribe(topics, this);
		
		MqttMessage message = new MqttMessage(content.getBytes());
		message.setQos(0);
		client.publish(topics, message);
		
	}
	
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		
		System.out.println("message" + message);
	}

}
