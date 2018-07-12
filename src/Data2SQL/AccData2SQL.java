package hu.bme.ftsrg.modes4.mqtt2sql;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;

import com.google.gson.Gson;

public class AccData2SQL extends Data2SQL{
	
	private final static String topic="raw/acc";
	private final static String table="trainsensordata";

	public AccData2SQL() {
		super(topic);
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
	protected String getSQL(String json) {
		Gson gson=new Gson();
		AccData data=gson.fromJson(json, AccData.class);
		String sql = "insert into "+table+"(AccX,AccY,AccZ,MicroTimestamp,SensorName) values("+Double.toString(data.getAccX())+","+Double.toString(data.getAccY())+","+Double.toString(data.getAccZ())+","+(data.getMicroTimeStamp())+",'train_test_1')";
		return sql;
	}
	

}
