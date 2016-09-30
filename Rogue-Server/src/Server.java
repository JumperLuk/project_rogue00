/**
 * Created by schueler on 30.09.2016.
 */

import java.net.*;
import java.util.ArrayList;

public class Server {

    private int listeningPort;

    private Controller controller; //Verwaltung: Kommunikation unter den Klassen(ermöglicht gleichberechtigte Klassenbeziehung)

    private ServerSocket serverSock; //Server Socket to listen
    private ArrayList<Socket> clients; //List of connected clients
    private ListenerThread listenerT; //Listens for new Connections
    private SenderThread senderT; //Sends Messages to Clients

    public Server(int port)
    {
        listeningPort = port;

        try {
            serverSock = new ServerSocket(port);

            clients = new ArrayList<Socket>();
            listenerT = new ListenerThread(serverSock);
            senderT = new SenderThread();

            controller = new Controller();

            controller.setListenerT(listenerT);
            controller.setSenderT(senderT);
            controller.setClients(clients);


        }
        catch (Exception e)
        {
            System.err.print("ServerSocket konnte nicht erstellt werden: " + e);
        }
    }

}
