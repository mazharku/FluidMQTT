/*
*this class will use MqttAsyncClient instead of MqttClient
 * 
 */
package com.imp.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @author mazhar
 *
 * Jan 27, 2020
 */
public class MqttAsycClient implements MqttCallback {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MqttAsycClient().doOn();
	}
	
	private void doOn() {
		String broker = "tcp://localhost:1883";
		String clientID = "779";
		String topics ="topic";
		MemoryPersistence persistence = new MemoryPersistence();
		try {
			MqttAsyncClient client = new MqttAsyncClient(broker, clientID, persistence);
			
			client.connect();
			client.setCallback(this);
			System.out.println("Connecting to broker: " + broker);
            
            System.out.println("Connected");
            Thread.sleep(1000);
            client.subscribe(topics, 0);
            System.out.println("Subscribed");
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void connectionLost(Throwable cause) {
		System.out.println("1 "+cause);
	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("2 "+message);
		
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("3 "+token);
		
	}

	

}
