/**
 * 
 */
package com.mqtt.client;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.fluidmqtt.topics.FluidTopics;

/**
 * @author mazhar
 *
 * Feb 2, 2020
 */
public class ClientTV extends CoreClient{

	/**
	 * @param topics
	 */
	public ClientTV(String topics) {
		super(topics);
		
	}

	
}
