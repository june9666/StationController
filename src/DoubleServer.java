import DataClasses.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class DoubleServer {
    final static Logger logger = Logger.getLogger(DoubleServer.class);

    private static int collectorPort = 5502;

    private static int forwarderPort = 5503;

    static ArrayList<Data> dataArrayList = new ArrayList();
    static DataArrayList dataArrayList2 = new DataArrayList();
    static ArrayList<String> errorList = new ArrayList();
    static boolean voltTune;

    static String lamphost = "192.168.43.240";
    static String tunehost = "192.168.43.250";
    static int lport = 8887;

    private static class DataForwarder extends Thread {

        ServerSocket forwarderServer;
        Socket listenerService;


        @Override

        public void run(){
            try {
                forwarderServer = new ServerSocket(forwarderPort);

                logger.info("DataForwarder started");

                while (true) {

                    listenerService = forwarderServer.accept();

                    DataOutputStream outToServer = new DataOutputStream(listenerService.getOutputStream());

                    if(dataArrayList2.getDataArrayList2().size() !=0) {

                        outToServer.writeBytes(dataArrayList2.getDataArrayList2().get(dataArrayList2.getDataArrayList2().size() - 1).toSparkformat());
                        dataArrayList2.clear();
                        logger.debug(dataArrayList2.getDataArrayList2().get(dataArrayList2.getDataArrayList2().size() - 1).toSparkformat());

                    }
                        if(errorList.size() != 0) {
                            outToServer.writeBytes(errorList.get(errorList.size() - 1));
                            logger.debug(errorList.get(errorList.size() -1));
                            errorList.clear();
                        }

                    listenerService.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class DataCollector extends Thread {

        ServerSocket collectorServer;
        Socket collectorService;
        @Override

        public void run(){

            try {
                collectorServer = new ServerSocket(collectorPort);
                logger.info("DataCollector started");

                while (true){
                    collectorService = collectorServer.accept();

                    String in = new BufferedReader(new InputStreamReader(collectorService.getInputStream())).readLine();

                    if(in != null){
                        logger.debug("Received string: "+in);

                        JsonParser parser = new JsonParser();
                        JsonObject json = (JsonObject) parser.parse(in);

                        //add received obj to ArrayList
                        initClass(dataArrayList, json);
                        initClass(dataArrayList2.getDataArrayList2(), json);

                        for (Data l : dataArrayList) {

                            logger.info(voltTune);


                            if (l.getLight() < 150 && l.getLight() >= 0) {
                                OutputHandler op = new OutputHandler();
                                op.clientLampSend(lamphost, lport,"ON");
                                voltTune = false;


                            } else if (l.getLight() <= 1024 && l.getLight() >= 150) {
                                OutputHandler op = new OutputHandler();
                                op.clientLampSend(lamphost, lport,"OFF");


                            }
                             if (l.getLight() <= 1024 && l.getLight() >= 150 && !voltTune) {
                                OutputHandler op = new OutputHandler();
                                logger.info("in");
                                op.clientTuneSend(tunehost, lport,"ON");
                                voltTune=true;
                            }
                        }


                    }

                    dataArrayList.removeAll(dataArrayList);

                    collectorService.close();

                }
            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }
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
                LightData lightData = new LightData(json, errorList);
                dataArrayList.add(lightData);
                break;
        }
    }

    public static void main(String[] args){

        PropertyConfigurator.configure("log4j.properties");

        Thread t = new DataCollector();
        t.start();

        Thread k = new DataForwarder();
        k.start();
    }
}