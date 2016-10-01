package Networking.Client;

import Networking.Server.Connection;
import Networking.Server.ConnectionReader;

/**
 * Created by schueler on 01.10.2016.
 */
public class Client extends Thread{

    private Object lockStop = new Object();
    private Connection con;
    private boolean stop;

    public Client(Connection con)
    {
        if(con ==  null)
            throw new RuntimeException("No connection(Client)");

        this.con = con;
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

    public void processMessage(String str)
    {

    }

    public void send(String str)
    {
        con.write(str);
    }

    public void close()
    {
        synchronized (lockStop)
        {
            stop = true;
        }
    }

}
