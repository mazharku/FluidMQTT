/**
 * 
 */
package com.mqtt.imp;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @author mazhar 
 *
 *         Jan 25, 2020
 */
public class MQTTConnectionManager {
	
	
	private final String topic = "fluids";
	private String message = "sample message ";
	private final int qos = 0;
	private static final String broker = "tcp://localhost:1883";
	private final String clientID = "fluid01"; // should be string and unique String id
  
//	public void createFirstMQtt() {
//		MemoryPersistence persistence = new MemoryPersistence();
//		try {
//			MqttClient client = new  MqttClient(broker, clientID, persistence);
//			client.connect();
//			client.subscribe(topic);
//			
//			MqttMessage mqttMessage = new MqttMessage(message.getBytes());
//			mqttMessage.setQos(qos);
//			client.publish(topic, mqttMessage);
//		} catch (MqttException e) {
//			
//			e.printStackTrace();
//		}
//
//	}
	
	public static MqttClient  FluidClient(String clientID) {
		MqttClient fluidClient = null;
		MemoryPersistence persistence = new MemoryPersistence();
		try {
			fluidClient = new MqttClient(broker, clientID, persistence);
		} catch (MqttException e) {
			
			e.printStackTrace();
		}
		
		return fluidClient;
	}

}
