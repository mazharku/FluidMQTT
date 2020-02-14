
/**
 * singleton instanace of the connection 
 */
package com.fluidmqtt.core;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @author mazhar
 *
 * Feb 1, 2020
 */
public class FluidConnectionOld implements MqttCallbackExtended{
   
	//private static final String broker = "tcp://localhost:1883";
    private String content = "Mazhar Ibna Zahur testing";
    private static String clientId = "UUID78";
    private static FluidConnectionOld connection = new FluidConnectionOld();
    private static MqttClient sampleClient= null;
    public static FluidConnectionOld getInstance() {
    	return connection;
    }
    private  FluidConnectionOld() {
//    	
//    	MemoryPersistence persistence = new MemoryPersistence();
//    	 try {
//			// MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
//			//.connect();
//		} catch (MqttException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
    
    public static MqttClient getSyncConnection(String broker) {
    	 MemoryPersistence persistence = new MemoryPersistence();
    	 if(sampleClient == null) {
    		 try {
				sampleClient = new MqttClient(broker, clientId, persistence);
				sampleClient.connect();
				sampleClient.setCallback(new FluidConnectionOld());
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	 }
    	 return sampleClient;
    }
	@Override
	public void connectionLost(Throwable cause) {
		System.out.println("loststtst  "+cause);
		
	}
	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("message  "+message);
		
	}
	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("token  "+token);
		
	}
	@Override
	public void connectComplete(boolean reconnect, String serverURI) {
		System.out.println("success  "+serverURI);
		
	}
}
