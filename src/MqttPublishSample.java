package hu.bme.ftsrg.modes4.mqtt.test.first;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttPublishSample {
	public static void main(String[] args) {
		Hivo h=new Hivo();
		h.hivdmeg();
	}
		
public class Hivo implements MqttCallback {
public void hivdmeg() {

    String topic        = "MQTT Examples";
    String content      = "Message from MqttPublishSample";
    int qos             = 2;
    String broker       = "tcp://193.168.1.230:1883";
    String clientId     = "Simon";
    MemoryPersistence persistence = new MemoryPersistence();
    MqttClient client=null;
    
    try {
    	
        client=new MqttClient("tcp://192.168.1.230:1883", "en_a_robot");
        MqttConnectOptions options=new MqttConnectOptions();
        options.setCleanSession(true);
        client.connect(options);
        client.setCallback(this);
        System.out.println("MQTT connected------------------------------------------------");
        client.publish("test", new MqttMessage("hello from kura".getBytes()));
        System.out.println("data was sent-------------------------------------------------");
        client.subscribe("hello");
        System.out.println("subscribed successfully---------------------------------------");
        //while(true);
        //client.close();
    }catch(Exception e){
    	 System.out.println(e.toString());
    }
    
    
    /*
    try {
        MqttClient sampleClient = new MqttClient(broker, clientId);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        //connOpts.setCleanSession(true);
        //connOpts.setUserName("mqtt-test");

       //connOpts.setPassword("mqtt-test".toCharArray());
        System.out.println("Connecting to broker: "+broker);
        sampleClient.connect(connOpts);
        System.out.println("Connected");
        System.out.println("Publishing message: "+content);
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(qos);
        sampleClient.publish(topic, message);
        System.out.println("Message published");
        sampleClient.disconnect();
        System.out.println("Disconnected");
        System.exit(0);
    } catch(MqttException me) {
        System.out.println("reason "+me.getReasonCode());
        System.out.println("msg "+me.getMessage());
        System.out.println("loc "+me.getLocalizedMessage());
        System.out.println("cause "+me.getCause());
        System.out.println("excep "+me);
        me.printStackTrace();
    }*/
}

@Override
public void connectionLost(Throwable arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void deliveryComplete(IMqttDeliveryToken arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void messageArrived(String arg0, MqttMessage arg1) throws Exception {

    System.out.println("massage arrived-------------------------------------------------");
    System.out.println(arg1);
}

}
}