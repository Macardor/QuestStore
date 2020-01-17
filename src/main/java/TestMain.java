import com.sun.net.httpserver.HttpServer;
import httpHandlers.TestHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class TestMain {

    static void runServer(){
        try{
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(8000), 0);
            //set routes
            httpServer.createContext("/test", new TestHandler());
            httpServer.setExecutor(null);
            httpServer.start();
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args) {
        runServer();
    }
}
