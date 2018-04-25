package DataClasses;

import com.google.gson.JsonObject;

/**
 * Basic DataType
 */
public abstract  class  Data {


    private String    SensorID;
    private String        Type;
    private String MicroTimeStamp; //TODO @simon5521 comment


    public Data(String sensorID, String type, String microTimeStamp) {
        SensorID       =       sensorID;
        Type           =           type;
        MicroTimeStamp = microTimeStamp;
    }
    public Data(JsonObject json){
        SensorID       =                        json.get("SensorID").toString();
        Type           =                            json.get("Type").toString();
        MicroTimeStamp = json.get("MicroTimeStamp").toString();
    }

    public abstract String toClassID();

    public Data() {
    }

    public String getSensorID() {
        return SensorID;
    }

    public void setSensorID(String sensorID) {
        SensorID = sensorID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getMicroTimeStamp() {
        return MicroTimeStamp;
    }

    public abstract int getLight();

}
