import DataClasses.*;
import com.google.gson.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * Main class for controlling incoming sockets
 */
public class Main {
    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String argv[]) throws IOException {

        PropertyConfigurator.configure("log4j.properties");
        ArrayList<Data> dataArrayList = new ArrayList();
        String lamphost = "192.168.43.240";
        int lport = 8887;
        logger.info("Server started");
        ServerSocket serverSocket;
        ServerSocket serverSocket2;
        Socket serviceSocket;
        Socket serviceSocket2;
        try {
            serverSocket = new ServerSocket(5502);
            serverSocket2 = new ServerSocket(5503);





            while (true) {

                serviceSocket = serverSocket.accept();

                //Listen and accept connections from clients



                //Reading received line
                InputStreamReader inFromClient =
                        new InputStreamReader(serviceSocket.getInputStream());

                BufferedReader bufferedInputStream =
                        new BufferedReader(inFromClient);

                String line = bufferedInputStream.readLine();

                if (line != null) {
                    //Received string
                    logger.debug("Received string: "+line);

                    //To List
                    JsonParser parser = new JsonParser();
                    JsonObject json = (JsonObject) parser.parse(line);

                    //add received obj to ArrayList
                    initClass(dataArrayList, json);
                    // logger.debug("Created class: " + dataArrayList.get(0)); //check parser

                    for (Data l : dataArrayList) {
                        if (l.getLight() < 150 && l.getLight() > 0) {
                            OutputHandler op = new OutputHandler();
                            op.clientLampSend(lamphost, lport,"ON");


                            //bufferedWriter.write("hello");
                            //  dataArrayList.remove(l);
                        } else if (l.getLight() < 1024 && l.getLight() > 150) {
                            OutputHandler op = new OutputHandler();
                            op.clientLampSend(lamphost, lport,"OFF");
                          //  bufferedWriter.write("hello");
                           // op.clientSparkSend(lamphost,lport, l);
                            // dataArrayList.remove(l);
                        }
                    }

                }
                serviceSocket.close();
                dataArrayList.removeAll(dataArrayList);

            }
        } catch (IOException e)

        {
            logger.error("ServerError" + e.getMessage()) ;
        }


    }
   // }

    /**
     * Add received class to ArrayList by type
     * @param dataArrayList
     * @param json
     */
    private static void initClass(ArrayList<Data> dataArrayList, JsonObject json){

        String otype =   json.get("Type").toString();
        otype = otype.substring(1, otype.length()-1); //format without ""

        switch (otype) {
            case "dht":
                DHTData dhtData = new DHTData(json);
                dataArrayList.add(dhtData);
                break;
            case "acc":
                AccData accData = new AccData(json);
                dataArrayList.add(accData);
                break;
            case "camera":
                CameraData cameraData = new CameraData(json);
                dataArrayList.add(cameraData);
                break;
            case "light":
               // LightData lightData = new LightData(json);
              //  dataArrayList.add(lightData);
                break;
        }
    }
}


