package hu.bme.ftsrg.modes4.mqtt2sql;

import org.eclipse.paho.client.mqttv3.MqttException;

public class Main {
	static AccData2SQL mukodj=null;

	public static void main(String[] args) {
		mukodj=new AccData2SQL();
		try {
			System.out.println("initalising broker connection...");
			mukodj.connect2Broker();
			System.out.println("broker connection established :)");
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println("initalising database connection...");
			mukodj.connect2Database();
			System.out.println("database connection established :)");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
