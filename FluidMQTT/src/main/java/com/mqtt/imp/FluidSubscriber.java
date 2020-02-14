/**
 * 
 */
package com.mqtt.imp;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

/**
 * @author mazhar
 *
 *         Jan 25, 2020
 */
public class FluidSubscriber implements MqttCallback {

	private final String topics = "IOImp";

	public void sampleSubscriber() {
		try {
			MqttClient client =MQTTConnectionManager.FluidClient("IOI21");
			client.connect();
			client.subscribe(topics);
			client.setCallback(this);
		} catch (MqttSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub

	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println(message);

	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub

	}

}
