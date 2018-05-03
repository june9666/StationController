package DataClasses;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class LightData extends Data  {
    final static Logger logger = Logger.getLogger(LightData.class);
    public int light;

    public LightData(String sensorID, String type, String microTimeStamp, int light) {
        super(sensorID, type, microTimeStamp);
        this.light = light;
    }
    public LightData(JsonObject json, ArrayList<String> errorList){
        super(json);
        PreProcessor p = new PreProcessor();
        try {
            light = p.StringToInt(0,1024, json.get("Light").toString(), json, errorList);
        } catch (Exception e) {
            logger.error("parse failed" + json.get("Light").toString());
            e.printStackTrace();
        }
    }

    public LightData() {

    }



    @Override
    public String toClassID() {
        return "light";
    }

    @Override
    public int getLight() {
        return light;
    }

    @Override
    public String toSparkformat() {

        Calendar cal = Calendar.getInstance();

        //s 	Second in minute
        //S 	Millisecond
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY.MM.dd.HH.mm.ss.S");

        return getType().substring(1,getType().length()-1)+"," + getSensorID().substring(1,getSensorID().length()-1) +
                 "," +getMicroTimeStamp().substring(1, getMicroTimeStamp().length()-1)+","+
                getLight() ;
    }
}
