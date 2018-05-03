package DataClasses;

import com.google.gson.JsonObject;

public  class CameraData extends Data {

    private String LabelText;
    private double BoxLeft;
    private double BoxTop;
    private double BoxRight;
    private double BoxBottom;
    private double LowerBound;
    private double UpperBound;

    public CameraData(String sensorID, String type, String microTimeStamp, String labelText, double boxLeft, double boxTop, double boxRight, double boxBottom, double lowerBound, double upperBound) {
        super(sensorID, type, microTimeStamp);
        LabelText = labelText;
        BoxLeft = boxLeft;
        BoxTop = boxTop;
        BoxRight = boxRight;
        BoxBottom = boxBottom;
        LowerBound = lowerBound;
        UpperBound = upperBound;
    }

    public CameraData(JsonObject json) {

        super(json);

        LabelText  =                  json.get("LabelText").toString();
        BoxLeft    =    Double.valueOf(json.get("BoxLeft").toString());
        BoxTop     =     Double.valueOf(json.get("BoxTop").toString());
        BoxRight   =   Double.valueOf(json.get("BoxRight").toString());
        BoxBottom  =  Double.valueOf(json.get("BoxBottom").toString());
        LowerBound = Double.valueOf(json.get("LowerBound").toString());
        UpperBound = Double.valueOf(json.get("UpperBound").toString());

    }

    public CameraData() { }

    public String getLabelText() {
        return LabelText;
    }

    public void setLabelText(String labelText) {
        LabelText = labelText;
    }

    public double getBoxLeft() {
        return BoxLeft;
    }

    public void setBoxLeft(double boxLeft) {
        BoxLeft = boxLeft;
    }

    public double getBoxTop() {
        return BoxTop;
    }

    public void setBoxTop(double boxTop) {
        BoxTop = boxTop;
    }

    public double getBoxRight() {
        return BoxRight;
    }

    public void setBoxRight(double boxRight) {
        BoxRight = boxRight;
    }

    public double getBoxBottom() {
        return BoxBottom;
    }

    public void setBoxBottom(double boxBottom) {
        BoxBottom = boxBottom;
    }

    public double getLowerBound() {
        return LowerBound;
    }

    public void setLowerBound(double lowerBound) {
        LowerBound = lowerBound;
    }

    public double getUpperBound() {
        return UpperBound;
    }

    public void setUpperBound(double upperBound) {
        UpperBound = upperBound;
    }

    @Override
    public String toClassID() {
        return "camera";
    }

    @Override
    public int getLight() {
        return -1;
    }

    @Override
    public String toSparkformat() {
        return null;
    }
}