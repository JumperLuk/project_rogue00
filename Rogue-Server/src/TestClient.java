import Networking.Client.Client;
import Networking.DataPackage;
import Networking.Server.Server;

import java.net.InetAddress;

/**
 * Created by schueler on 01.10.2016.
 */
public class TestClient {

    public static void main(String[] args)
    {
        try {
            Client client = new Client(InetAddress.getByName("localhost"), 9090);
            client.start();
            client.send(new DataPackage(61232354));
            client.close();
        }
        catch (Exception e)
        {
            System.err.print("TestServer(main): " + e);
        }


    }

}
