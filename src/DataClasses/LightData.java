package DataClasses;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;

public class LightData extends Data  {
    final static Logger logger = Logger.getLogger(LightData.class);
    public int light;

    public LightData(String sensorID, String type, String microTimeStamp, int light) {
        super(sensorID, type, microTimeStamp);
        this.light = light;
    }
    public LightData(JsonObject json){
        super(json);
        PreProcessor p = new PreProcessor();
        try {
            light = p.StringToInt(0,1024, json.get("Light").toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
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
}
