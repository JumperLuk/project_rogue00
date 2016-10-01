package Networking.Server;

import Networking.DataPackage;

import java.io.*;
import java.net.Socket;

/**
 * Created by schueler on 01.10.2016.
 */
public class ClientHandlerThread extends Thread {

    private Server server;

    private Connection con;
    private ConnectionReader reader;

    private Object lockStop = new Object();
    private boolean stop = false;

    public ClientHandlerThread(Socket socket, Server server)
    {
        if(socket == null)
            throw new RuntimeException("Socket is null (ClientHandlerThread)");

        con = new Connection(socket);
        reader = con.getReader();
        this.server = server;
    }

    @Override
    public void run()
    {
        if(con.isInactive())
            throw new RuntimeException("No connection to socket/stream(Client)");


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

}
