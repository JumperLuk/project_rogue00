package eu.devy.network.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionReader extends Thread 
{

    private BufferedReader in;

    private Object lockMessages = new Object();
    private List<String> messages; 
    private Object lockStop = new Object();
    private boolean stop = false;

    public ConnectionReader(BufferedReader in)
    {
        this.in = in;
        this.messages = new ArrayList<String>();
    }

    @Override
    public void run()
    {
        while(true)
        {
            synchronized (lockStop)
            {
                if(stop)
                    return;
            }

            try {
                addMessage(in.readLine());
            }
            catch (IOException e)
            {
                if(!stop)
                    throw new RuntimeException(e);
            }

            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    private void addMessage(String str) 
    {
        if (str != null)
        {
            synchronized (lockMessages)
            {
                messages.add(str);
            }
        }
    }

    public String readMessage()
    {
        String str = null;

        synchronized (lockMessages)
        {
            if(!messages.isEmpty())
            {
                str = messages.remove(0);
            }
        }
        return str;
    }

    public void close()
    {
        synchronized (lockStop)
        {
            stop = true;
        }
    }
}
