import Networking.Client.Client;
import Networking.DataPackage;
import Networking.Server.Connection;
import Networking.Server.Server;

import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by schueler on 01.10.2016.
 */
public class TestClient {

    public static void main(String[] args)
    {
        try {
            Socket socket = new Socket(InetAddress.getByName("localhost"), 9090);
            Connection connection = new Connection(socket);
            Client client = new Client(connection);
            client.start();
            client.send("Hi");
            Thread.sleep(100);
            client.close();
        }
        catch (Exception e)
        {
            System.err.print("TestServer(main): " + e);
        }


    }

}
