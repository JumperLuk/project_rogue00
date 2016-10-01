import Networking.Client.Client;
import Networking.DataPackage;
import Networking.Server.Server;

import java.lang.reflect.Executable;
import java.net.InetAddress;

/**
 * Created by schueler on 01.10.2016.
 */
public class TestServer {

    public static void main(String[] args)
    {
        try {
            Server echoServer = new Server(9090);
            echoServer.start();


            Thread.sleep(100000);

            echoServer.close();
        }
        catch (Exception e)
        {
            System.err.print("TestServer(main): " + e);
        }


    }

}
