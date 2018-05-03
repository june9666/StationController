import DataClasses.Data;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import sun.rmi.transport.proxy.HttpReceiveSocket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class OutputHandler {

    final static Logger logger = Logger.getLogger(OutputHandler.class);

    public OutputHandler() {

    }

    public void clientTuneSend(String host, int port, String action) {
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(host, port);


            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            JsonObject json2 = new JsonObject();
            json2.addProperty("GatewayID", "IndulASoroSAludnI");
            json2.addProperty("LampState", action);
            json2.addProperty("TuneState", "ON");
            //logger.info(json2.get("GatewayID").toString());
            logger.info(clientSocket.getRemoteSocketAddress());


            // if (voltma) {
            //   voltma = false;
            out.write(json2.toString().getBytes());
            out.write('$');
            //  logger.info("Sent control: " + json2.toString().getBytes() + "$");
            clientSocket.close();
        } catch (IOException e) {
            logger.error("no lamp found");
            return;
        }
    }

    public void clientLampSend(String host, int port, String action) {
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(host, port);


            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            JsonObject json2 = new JsonObject();
            json2.addProperty("GatewayID", "IndulASoroSAludnI");
            json2.addProperty("LampState", action);
            //json2.addProperty("TuneState", "ON");
            //logger.info(json2.get("GatewayID").toString());
            logger.info(clientSocket.getRemoteSocketAddress());


            // if (voltma) {
            //   voltma = false;
            out.write(json2.toString().getBytes());
            out.write('$');
            //  logger.info("Sent control: " + json2.toString().getBytes() + "$");
            clientSocket.close();
        } catch (IOException e) {
            logger.error("no lamp found");
            return;
        }
    }

    public void clientSparkSend(String host, int port, Data input) {
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(host, port);


            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            logger.info(input.toSparkformat());

            out.write(input.toString().getBytes());



        } catch (IOException e) {
            logger.error("no Spark found");
            return;
        }

    }
}
