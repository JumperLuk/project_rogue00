package eu.devy.network.Server; /**
 * Created by schueler on 30.09.2016.
 */


import java.net.ServerSocket;
import java.util.ArrayList;

import eu.devy.network.Client.DataPackage;
import eu.devy.network.Client.DataPackageAction;

public class Server extends Thread
{
    private boolean keepListening;

    private final int  CONNECTION_LIMIT = 4;

    private ServerSocket serverSock;
    protected ArrayList<ClientHandlerThread> clients;

    public Server(int port)
    {
        try 
        {
            serverSock = new ServerSocket(port);
            clients = new ArrayList<ClientHandlerThread>();
        }
        catch (Exception e)
        {
            System.err.print("Failed at creating ServerSocket: " + e);
        }
    }

    @Override
    public void run()
    {
        keepListening = true;

        while (keepListening && clients.size() < CONNECTION_LIMIT)
        {
            try {
                ClientHandlerThread temp = new ClientHandlerThread(serverSock.accept(), this);
                clients.add(temp);
                temp.start();
                System.out.println(clients.size());
                broadcast("null" + DataPackage.SEPERATOR + DataPackageAction.JOIN + DataPackage.SEPERATOR + "null" + DataPackage.SEPERATOR);
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
      
        try 
        {
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

    public void processMessage(String str)
    {
    	DataPackage dataPackage = DataPackage.convert(str);
    	
    	switch(dataPackage.getDataPackageAction())
    	{
		case ATTACK:
			break;
		case JOIN:
			break;
		case POSITION_PLAYER:
			broadcast(str);
			updatePlayer(str);
			break;
		case POSITION_MONSTER:
			break;
		default:
			break;
    	}
    }

    public synchronized void broadcast(String str)
    {
        for(int i = 0; i < clients.size(); i++)
        {
            try
            {
                clients.get(i).writeToClient(str);
            }
            catch (Exception e)
            {
                System.err.print("DataPackage couldn't be sent (broadcast): " + e);
            }
        }
    }
	
    private DataPackage[] all_connected_player = new DataPackage[4];
	
    public void updatePlayer(String text)
    {
    	DataPackage dataPackage = DataPackage.convert(text);
    	
    	for(int i = 0; i < all_connected_player.length; i++)
    	{
    		if(all_connected_player[i] != null)
    		{
    			if(all_connected_player[i].id().equals(dataPackage.id()))
    			{
    				all_connected_player[i] = dataPackage;
    				return;
    			}
    		}
    	}
    	
    	for(int j = 0; j < all_connected_player.length; j++)
    	{
    		if(all_connected_player[j] == null)
    		{
    			all_connected_player[j] = dataPackage;
    			break;
    		}
    	}
    }
    
    public DataPackage[] getAllConnectedPlayer()
    {
    	return all_connected_player;
    }
}
