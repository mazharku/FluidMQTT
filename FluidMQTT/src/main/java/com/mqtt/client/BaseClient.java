/**
 * 
 */
package com.mqtt.client;

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
 *         Feb 1, 2020
 */
public abstract class BaseClient implements MqttCallback {
	

	public BaseClient(String topics) {
		MqttClient client = FluidConnectionOld.getSyncConnection("");
		try {
			if(!client.isConnected()) {
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

	public void doNotify(MqttMessage message, String topics) {
		MqttClient client = FluidConnectionOld.getSyncConnection("");
		new Thread(()-> {
			try {
				if(!client.isConnected()) {
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

	public abstract void subscribe();

	public abstract void Publish();

}
