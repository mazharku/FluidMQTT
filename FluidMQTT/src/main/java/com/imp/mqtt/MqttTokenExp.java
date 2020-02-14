/*
*  MqttToken , IMqttActionListner and AyncMqttClient
 * 
 */
package com.imp.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @author mazhar
 *
 * Jan 28, 2020
 */
public class MqttTokenExp implements IMqttActionListener{

	/**
	 * @param args
	 * @throws MqttException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws MqttException, InterruptedException {
		new MqttTokenExp().extracted();

	}

	private  void extracted() throws MqttException, InterruptedException {
		String broker = "tcp://localhost:1883";
		String clientID = "779";
		String topics ="topic";
		String content ="mazhar";
		MemoryPersistence persistence = new MemoryPersistence();
		MqttToken token = new MqttToken();
		MqttAsyncClient client = new MqttAsyncClient(broker, clientID, persistence);
		client.connect(null, this);
		Thread.sleep(1000);
		client.subscribe(topics, 0);
		client.checkPing(null, this);
		MqttMessage message = new MqttMessage(content.getBytes());
		client.publish(topics, message);
		Thread.sleep(5000);
		client.publish(topics, message);
		client.disconnect();
	}

	public void onSuccess(IMqttToken asyncActionToken) {
		System.out.println("success : "+ asyncActionToken.getResponse());
		
	}

	public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
		System.out.println("sucee");
	}

}
