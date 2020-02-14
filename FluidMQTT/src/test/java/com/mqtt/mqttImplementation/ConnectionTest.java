/**
 * 
 */
package com.mqtt.mqttImplementation;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.fluidmqtt.core.FluidConnectionOld;

/**
 * @author mazhar
 *
 * Feb 9, 2020
 */
public class ConnectionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MqttClient client = FluidConnectionOld.getSyncConnection("");
		try {
			client.connect();
			//client.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(client.isConnected()) {
			System.out.println("System is connected");
		}
		else {
			System.out.println("System is not connected");
		}

	}

}
