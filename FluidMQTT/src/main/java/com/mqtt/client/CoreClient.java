/**
 * 
 */
package com.mqtt.client;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import com.fluidmqtt.core.FluidConnectionOld;
import com.fluidmqtt.topics.FluidTopics;

/**
 * @author mazhar
 *
 *         Feb 2, 2020
 */
public class CoreClient implements MqttCallback {

	public CoreClient(String topics) {
		MqttClient client = FluidConnectionOld.getSyncConnection("");
		try {
			if (!client.isConnected()) {
				client.connect();
			}

			client.subscribe(FluidTopics.FAN_TOPICS);
			client.subscribe(FluidTopics.TV_TOPICS);
		} catch (MqttException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		client.setCallback(this);

	}

	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub

	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {

		System.out.println(topic + " message " + message);
		if (message.toString().equalsIgnoreCase("tv")) {
			MqttMessage mmessage = new MqttMessage("tv is on".getBytes());
			doNotify(mmessage, FluidTopics.TV_TOPICS);
			System.out.println("notify tv");
		}
		else if(message.toString().equalsIgnoreCase("fan")){
			MqttMessage mmessage = new MqttMessage("fan is on".getBytes());
			doNotify(mmessage, FluidTopics.FAN_TOPICS);
			System.out.println("notify fan");
		}

	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub

	}

	public void doNotify(MqttMessage message, String topics) {
		MqttClient client = FluidConnectionOld.getSyncConnection("");
		new Thread(() -> {
			try {
				if (!client.isConnected()) {
					client.connect();
				}
				client.setCallback(this);
				client.publish(topics, message);
			} catch (MqttPersistenceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
		client.setCallback(this);
	}

}
