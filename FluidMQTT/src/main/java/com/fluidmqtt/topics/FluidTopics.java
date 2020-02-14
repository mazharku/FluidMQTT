/**
 * 
 */
package com.fluidmqtt.topics;

import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;

import com.fluidmqtt.util.Messages;

/**
 * @author mazhar
 *
 * Feb 1, 2020
 */
public class FluidTopics extends MqttTopic {
    
	public static final String TV_TOPICS = Messages.getString("FluidTopics.0"); //$NON-NLS-1$
	public static final String FAN_TOPICS = Messages.getString("FluidTopics.1"); //$NON-NLS-1$
	/**
	 * @param name
	 * @param comms
	 */
	public FluidTopics(String name, ClientComms comms) {
		super(name, comms);
		// TODO Auto-generated constructor stub
	}

}
