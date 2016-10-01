package Networking.Server; /**
 * Created by schueler on 30.09.2016.
 */

import Networking.DataPackage;

import java.net.*;
import java.util.ArrayList;

public class Server extends Thread{

    private int listeningPort; //Port to listen for connections

    private boolean keepListening;

    private final int  CONNECTION_LIMIT = 4;

    private ServerSocket serverSock; //Networking.Server.Server.Networking.Server.Server Socket to listen
    private ArrayList<ClientHandlerThread> clients; //List of connected clients

    public Server(int port)
    {
        listeningPort = port;

        try {
            serverSock = new ServerSocket(port);
            clients = new ArrayList<ClientHandlerThread>();

        }
        catch (Exception e)
        {
            System.err.print("Failed at creating ServerSocket: " + e);
        }
    }

    //Look for Connections
    @Override
    public void run()
    {
        keepListening = true;

        while (keepListening || clients.size() > CONNECTION_LIMIT)
        {
            try {
                ClientHandlerThread temp = new ClientHandlerThread(serverSock.accept(), this);
                clients.add(temp);
                temp.start();
            }
            catch (Exception e)
            {
                System.err.println("Client couldn't be accepted: " + e);
            }

            try
            {
                Thread.sleep(10);
            }
            catch (Exception e)
            {
                System.err.println("Sleep failed(Server): " + e);
            }
        }
    }

    public void close()
    {
        keepListening = false;
        try {
            if (serverSock != null)
                serverSock.close();

            for (int i = 0; i < clients.size(); i++)
            {
                clients.get(i).close();
            }
        }
        catch (Exception e)
        {
            System.err.println("Closing failed(Server): " + e);
        }
    }

    public void stopListening()
    {
        keepListening = false;
    }


    public void processDataPackage(DataPackage dataPackage)
    {
        //Do stuff...
        broadcast(dataPackage);
    }

    public synchronized void broadcast(DataPackage dataPackage)
    {
        for(int i = 0; i < clients.size(); i++)
        {
            try {
                clients.get(i).writeToClient(dataPackage);
            }
            catch (Exception e)
            {
                System.err.print("DataPackage couldn't be sent (broadcast): " + e);
            }
        }
    }
}
