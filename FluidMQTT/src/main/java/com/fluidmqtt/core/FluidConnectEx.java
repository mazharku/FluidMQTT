/**
 * 
 */
package com.fluidmqtt.core;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * @author mazhar
 *
 * Feb 11, 2020
 */
public class FluidConnectEx extends MqttClient{

	/**
	 * @param serverURI
	 * @param clientId
	 * @throws MqttException
	 */
	public FluidConnectEx(String serverURI, String clientId) throws MqttException {
		super(serverURI, clientId);
		// TODO Auto-generated constructor stub
	}

}
