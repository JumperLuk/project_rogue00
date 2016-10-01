package Networking.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by schueler on 01.10.2016.
 */
public class Connection {

    private Socket socket;
    private PrintWriter out;
    private ConnectionReader reader;

    public Connection(Socket socket)
    {
        if(socket == null)
            throw new RuntimeException("Socket is null(Connection)!");
        this.socket = socket;

        try {
            out = new PrintWriter(socket.getOutputStream());
            reader = new ConnectionReader(new BufferedReader(new InputStreamReader(socket.getInputStream())));
            reader.start();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void write(String str)
    {
        if(str != null)
        {
            out.println(str);
            out.flush();
        }
    }

    public String read()
    {
        String str = null;

        do {
            str = reader.readMessage();
        } while(str == null);

        return str;
    }

    public ConnectionReader getReader()
    {
        return reader;
    }

    public void close()
    {
        try {
            reader.close();
            Thread.sleep(1000);

            out.close();

            socket.close();

        } catch (IOException | InterruptedException e)
        {
            System.err.println("Couldn't close everything! (Connection)");
            e.printStackTrace();
        }
        finally {
            socket = null;
        }
    }

    public boolean isActive() {
        return !isInactive();
    }

    public boolean isInactive() {
        return socket == null || out == null || reader == null;
    }

}
