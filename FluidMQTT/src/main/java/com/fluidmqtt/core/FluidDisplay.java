/**
 * 
 */
package com.fluidmqtt.core;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author mazhar
 *
 *         Feb 1, 2020
 */
public class FluidDisplay implements MqttCallback {

	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub

	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		new Thread(() -> {
			System.out.println(topic +" message " + message);
		}).start();

	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub

	}

}
