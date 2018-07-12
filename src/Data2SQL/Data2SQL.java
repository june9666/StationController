package hu.bme.ftsrg.modes4.mqtt2sql;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;





public abstract class Data2SQL implements MqttCallback {
	
	private static final Boolean info=true;
	
	//MQTT objects
	private final String topic;
	private final String mqttBrokerURL="tcp://192.168.1.230:1883";
	private final String clientID="MQTT2SQL";
	
	MqttClient client;
	
	//JDBC and SQL objects
	// old driver: final String jdbcDriver=  "com.mysql.jdbc.Driver"; 
	final String jdbcDriver=  "com.mysql.cj.jdbc.Driver"; 
	final String dbURL="jdbc:mysql://localhost:3306/iot_db";
	final String dbUsername="gateway3";
	final String dbPassword="new_password";
	Connection dbConnection=null;
	Statement dbStatement=null;
	
	protected abstract String getSQL(String json);
	
	public Data2SQL(String topic_) {
		topic=topic_;
	}
	
	public void connect2Broker() throws MqttException{
		client=new MqttClient(mqttBrokerURL,clientID);
		client.connect();
		client.setCallback(this);
		client.subscribe(topic);
	}
	
	
	public void connect2Database() throws Exception {
		Class.forName(jdbcDriver);
		dbConnection=DriverManager.getConnection(dbURL,dbUsername,dbPassword);
		dbStatement=dbConnection.createStatement();
		
	}
	
	
	
	@Override
	public void messageArrived(String topic, MqttMessage message)  {
    	System.out.println("mew mqtt message has arrived");
		try  {
			if(dbConnection.isClosed()) {
				dbStatement.close();
				dbConnection.close();
				return;
			}
	        int rs = dbStatement.executeUpdate(getSQL(message.toString()));
	        if(info) {
	        	System.out.println(message);
	        	System.out.println(rs);
	        }
			//rs.toString();
			//rs.close();
		}catch(Exception e)  {
			if(info)  {
				System.out.println(e.toString());
			}
		}
	}
	

}
