/**
 * 
 */
package com.mqtt.mqttImplementation;

import com.fluidmqtt.topics.FluidTopics;
import com.mqtt.client.ClientFan;
import com.mqtt.client.ClientTV;

/**
 * @author mazhar
 *
 * Feb 1, 2020
 */
public class MqttApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
	    ClientTV tv = new ClientTV(FluidTopics.TV_TOPICS);
	
        ClientFan fan = new ClientFan(FluidTopics.FAN_TOPICS);
	}

}
