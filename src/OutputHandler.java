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

    public void clientsend(String host, int port, String action) {
        Socket  clientSocket = null;
        try {
            clientSocket = new Socket(host,port);


        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
        JsonObject json2 = new JsonObject();
        json2.addProperty("GatewayID", "IndulASorosAludni");
        json2.addProperty("LampState", action);
        //logger.info(clientSocket.getRemoteSocketAddress());


       // if (voltma) {
         //   voltma = false;
            out.write(json2.toString().getBytes());
            out.write('$');
          //  logger.info("Sent control: " + json2.toString().getBytes() + "$");
        clientSocket.close();
        } catch (IOException e) {
            logger.error("no lamp found");
        }
    }

}
