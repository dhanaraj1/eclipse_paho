package com.ddesk.paho;
 import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttPublishSample {

        public static void main(String[] args) {

            String topic        = "game/#";
            String content      = "game/1.160089909";
            int qos             = 0;
            String broker       = "wss://mqtt.dollarexch99.com:443";
            String clientId     = "14919_11963221512314";
            MemoryPersistence persistence = new MemoryPersistence();


            try {
                MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
                MqttConnectOptions connOpts = new MqttConnectOptions();
                connOpts.setCleanSession(true);
                connOpts.setUserName("mqttusersub");
                connOpts.setPassword("M1iVhkb1Y0v".toCharArray());
                System.out.println("Connecting to broker: "+broker);
                sampleClient.connect(connOpts);
                System.out.println("Connected");
                System.out.println("Publishing message: "+content);
                MqttMessage message = new MqttMessage(content.getBytes());
                message.setQos(qos);
                sampleClient.publish(topic, message);
                System.out.println("Message published");
                sampleClient.subscribe(topic, new IMqttMessageListener() {
					
					public void messageArrived(String topic, MqttMessage message) throws Exception {
						System.out.println(message.toString());
						
					}
				});
             //   sampleClient.disconnect();
             //   System.out.println("Disconnected");
            //    System.exit(0);
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