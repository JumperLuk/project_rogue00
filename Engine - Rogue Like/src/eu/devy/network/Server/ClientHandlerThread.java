package eu.devy.network.Server;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;

import eu.devy.network.Client.DataPackage;
import eu.devy.network.Client.DataPackageAction;

public class ClientHandlerThread extends Thread
{
    private Server server;

    private Connection con;
    private Object lockStop = new Object();
    private boolean stop = false;

    public ClientHandlerThread(Socket socket, Server server)
    {
        if(socket == null)
        {
            throw new RuntimeException("Socket is null (ClientHandlerThread)");
        }
        
        con = new Connection(socket);
        con.getReader();
        
        this.server = server;
    }

    @Override
    public void run()
    {
        if(con.isInactive())
        {
        	throw new RuntimeException("No connection to socket/stream(Client)");
        }

        ConnectionReader reader = con.getReader();
        String str;

        while(true)
        {
            synchronized (lockStop)
            {
                if(stop)
                {
                    con.close();
                    return;
                }
            }

            while((str = reader.readMessage()) == null)
            {
                synchronized (lockStop)
                {
                    if (stop)
                    {
                        con.close();
                        return;
                    }
                }
            }

            try
            {
                Thread.sleep(50);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }

            synchronized (lockStop)
            {
                if (stop)
                {
                    con.close();
                    return;
                }
            }
            processMessage(str);
        }
    }

    public void close()
    {
        synchronized (lockStop)
        {
            stop = true;
        }
    }

    public void processMessage(String str)
    {
        server.processMessage(str);
    }

    public void writeToClient(String str) throws IOException
    {
        con.write(str);
    }
    
    public String keygen()
	{
		String keygen = String.format("%0" + Integer.toString(4).length() + "d", new Random().nextInt(4));
		
		for(DataPackage dataPackage : server.getAllConnectedPlayer())
		{
			if(dataPackage.id().equalsIgnoreCase(keygen))
			{
				keygen = keygen();
				break;
			}
		}
		
		return keygen;
	}
}
