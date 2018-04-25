package DataClasses;

import org.apache.log4j.Logger;

public class PreProcessor {
    final static Logger logger = Logger.getLogger(PreProcessor.class);

    public PreProcessor() {
    }

    public int StringToInt(int min, int max, String input) throws Exception{
        int in=-1;
        try {
            in = Integer.valueOf(input.substring(1, input.length() - 1));
        }catch(Exception e ){
            logger.warn("Sensor Error, parse failed " + e.getMessage());
            return -1;
//            throw new Exception("Sensor error");
        }
        try {
        if (in < min) {
            logger.warn("DataClasses.PreProcessor warning input too low: " + in + " min is: " + min);
            return -1;
        }
        }catch(Exception e ){

//                throw new Exception("Sensor error low input");
            }
        try {
            if (in > max) {
                logger.warn("DataClasses.PreProcessor warning input too high: " + in + " max is: " + max);
                return -1;
            }
        }
        catch(Exception e ){

 //               throw new Exception("Sensor error high input");
            }

        return in;
    }
}


