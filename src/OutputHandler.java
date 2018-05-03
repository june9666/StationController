import DataClasses.Data;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import sun.rmi.transport.proxy.HttpReceiveSocket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.sql.*;


public class OutputHandler {

    final static Logger logger = Logger.getLogger(OutputHandler.class);
    
   //database variables 
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://192.168.43.7/iot_db";

   //  Database credentials
   static final String USER = "gateway";
   static final String PASS = "new_password";


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
    
      public void clientSparkSend(AccData input) {
        Connection conn = null;
        Statement stmt = null;

        try {
            
          Class.forName("com.mysql.jdbc.Driver");
          conn = DriverManager.getConnection(DB_URL,USER,PASS);
          
          stmt = conn.createStatement();
          String sql;
          sql = "insert into trainsensordata(AccX,AccY,AccZ,MicroTimestamp,SensorName) values("+String(input.AccX)+","+String(input.AccY)+","+String(input.AccZ)+","+String(input.MicroTimestamp)+",'train_test_1')";
         
          ResultSet rs = stmt.executeQuery(sql);

          
          rs.close();
          stmt.close();
          conn.close();



        }catch(SQLException se){
            //Handle errors for JDBC
            logger.error("there is an error with sql");
        } catch (Exception e) {
            logger.error("there is an error with accdata");
            return;
        }finally{
        //finally block used to close resources
        try{
            if(stmt!=null)
            stmt.close();
        }catch(SQLException se2){
        }// nothing we can do
        try{
            if(conn!=null)
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
   }

    }
    
    
    
      public void clientNotificationSend(String desc,int priority) {
        Connection conn = null;
        Statement stmt = null;

        try {
            
          Class.forName("com.mysql.jdbc.Driver");
          conn = DriverManager.getConnection(DB_URL,USER,PASS);
          
          stmt = conn.createStatement();
          String sql;
          sql = "insert into alerts(priority,description) values("+String(priority)+","+desc+")";
         
          ResultSet rs = stmt.executeQuery(sql);

          
          rs.close();
          stmt.close();
          conn.close();



        }catch(SQLException se){
            //Handle errors for JDBC
            logger.error("there is an error with sql");
        } catch (Exception e) {
            logger.error("there is an error with accdata");
            return;
        }finally{
        //finally block used to close resources
        try{
            if(stmt!=null)
            stmt.close();
        }catch(SQLException se2){
        }// nothing we can do
        try{
            if(conn!=null)
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
   }
    
    
    
}
