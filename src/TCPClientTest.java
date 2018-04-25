import DataClasses.*;
import com.google.gson.Gson;
import java.io.*;
import java.net.*;

/**
 * Just for testing server
 */
class TCPClientTest {
    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;
        //BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost", 8888);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
       // sentence = inFromUser.readLine();

        Gson gson = new Gson();
        //String data =  gson.toJson(new DHTData("1","dht", 1, 24, 24));
        String light = gson.toJson(new LightData("2","light","1",1));
        //outToServer.writeBytes(data.toString());
        outToServer.writeBytes(light.toString());
        clientSocket.close();
    }
}