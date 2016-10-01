import Networking.Client.Client;
import Networking.DataPackage;
import Networking.Server.Server;

import java.lang.reflect.Executable;
import java.net.InetAddress;

/**
 * Created by schueler on 01.10.2016.
 */
public class Test {

    public static void main(String[] args)
    {
        try {
            Server echoServer = new Server(6789);
            echoServer.start();
            Client client = new Client(InetAddress.getByName("localhost"), 6789);
            client.start();

             client.send(new DataPackage("Hi"));
            echoServer.halt();
            client.halt();
        }
        catch (Exception e)
        {
            System.err.print("Test(main): " + e);
        }


    }

}
