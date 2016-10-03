package eu.devy.network.Client;

import eu.devy.game.monster.EntityManager;
import eu.devy.game.player.Dummy;
import eu.devy.network.Server.Connection;
import eu.devy.network.Server.ConnectionReader;

public class Client extends Thread
{
    private Object lockStop = new Object();
    private Connection con;
    private boolean stop;

    public Client(Connection con)
    {
        if(con ==  null)
        {
            throw new RuntimeException("No connection(Client)");
        }
        this.con = con;
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

    public void processMessage(String str)
    {
    	DataPackage dataPackage = DataPackage.convert(str);
    	
    	System.out.println(str);
    	
    	switch(dataPackage.getDataPackageAction())
		{
		case ATTACK:
			break;
		case JOIN:
			System.out.println("someone joined");
			Dummy dummy = new Dummy();
			dummy.getLocation().setLocation(Integer.parseInt(dataPackage.getData(0)), Integer.parseInt(dataPackage.getData(1)));
			break;
		case POSITION_MONSTER:
			break;
		case POSITION_PLAYER:
			EntityManager.updateDummy(dataPackage);
			break;
		default:
			break;
		}
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
