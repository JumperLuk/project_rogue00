package Networking.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by schueler on 01.10.2016.
 */
public class ConnectionReader extends Thread {

    private BufferedReader in;

    private Object lockMessages = new Object(); //Lock input as long as there is no Input
    private List<String> messages; //Store messages here
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

    //Add a message to messages
    private void addMessage(String str) {
        if (str != null)
        {
            synchronized (lockMessages) {
                messages.add(str);
            }
        }
    }

    //Give a message to owner
    public String readMessage()
    {
        String str = null;

        synchronized (lockMessages)
        {
            if(!messages.isEmpty())
                str = messages.remove(0);
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
