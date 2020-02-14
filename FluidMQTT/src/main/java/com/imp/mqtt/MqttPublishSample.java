/**
 * 
 */
package com.imp.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @author mazhar
 *
 * Jan 24, 2020
 */
public class MqttPublishSample implements MqttCallback{

	
	public static void main(String[] args) {
		new MqttPublishSample().doTest();
	}

	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		
	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("hhd"+ topic + "fj"+ message);
		
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		
	}

	public void doTest() {
		String topic = "light/signal";
		String content = "Mazhar Ibna Zahur testing";
		int qos = 0;
		String broker = "tcp://localhost:1883";
		String clientId = "UUID78";
		MemoryPersistence persistence = new MemoryPersistence();
		
		 try {
//			 server, unique id for the client, persistence in local as volatile system
             MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            // MqttConnectOptions connOpts = new MqttConnectOptions();
             //connOpts.setCleanSession(true);
             System.out.println("Connecting to broker: "+broker);
            // sampleClient.connect(connOpts);
             sampleClient.connect();
             sampleClient.setCallback(this);
             sampleClient.subscribe(topic);
             System.out.println("Connected");
            // System.out.println("Publishing message: "+content);
             MqttMessage message = new MqttMessage(content.getBytes());
             //Qos quality of service , 0 non persistence, 1 persistence but duplicate,
             //2 persistence and non duplicate
             message.setQos(qos);
             sampleClient.publish(topic, message);
             System.out.println("Message published");
             //sampleClient.disconnect();
             //System.out.println("Disconnected");
            // System.exit(0);
         } catch(MqttException me) {
             System.out.println("reason "+me.getReasonCode());
             System.out.println("msg "+me.getMessage());
             System.out.println("loc "+me.getLocalizedMessage());
             System.out.println("cause "+me.getCause());
             System.out.println("excep "+me);
             me.printStackTrace();
         }

	}


}
