package DataClasses;

import com.google.gson.JsonObject;

/**
 * Accelerometer
 */
public class AccData extends Data{
    private double AccX;
    private double AccY;
    private double AccZ;

    public AccData(String sensorID, String type, String microTimeStamp, double accX, double accY, double accZ) {
        super(sensorID, type, microTimeStamp);
        AccX = accX;
        AccY = accY;
        AccZ = accZ;
    }
    public AccData(JsonObject json){
        super(json);
        AccX = Double.valueOf(json.get("AccX").toString());
        AccY = Double.valueOf(json.get("AccY").toString());
        AccZ = Double.valueOf(json.get("AccZ").toString());
    }

    public AccData() { }

    @Override
    public int getLight() {
        return -1;
    }

    public double getAccX() {
        return AccX;
    }

    public void setAccX(double accX) {
        AccX = accX;
    }

    public double getAccY() {
        return AccY;
    }

    public void setAccY(double accY) {
        AccY = accY;
    }

    public double getAccZ() {
        return AccZ;
    }

    public void setAccZ(double accZ) {
        AccZ = accZ;
    }

    @Override
    public String toClassID() {
        return "acc";
    }
}
