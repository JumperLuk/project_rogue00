package Networking.Server;

import Networking.DataPackage;

import java.io.*;
import java.net.Socket;

/**
 * Created by schueler on 01.10.2016.
 */
public class ClientHandlerThread extends Thread {

    private Socket socket;
    private Server server;

    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    private String messageToSend;
    private boolean sendMessage;
    private boolean keepRunning;

    public ClientHandlerThread(Socket socket, Server server)
    {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run()
    {
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());



            while (true)
            {
                if(!keepRunning)
                    break;

                DataPackage dataPackage = (DataPackage) inputStream.readObject();
                server.processDataPackage(dataPackage);
            }
        }
        catch (Exception e)
        {
            System.err.println("Failed to get stream(s) " + e);
        }
        finally {

            //Always close streams to prevent heap leaks
            try {
                if (outputStream != null)
                    outputStream.close();
                if (inputStream != null)
                    inputStream.close();
                if (socket != null)
                    socket.close();
            }
            catch (Exception e)
            {
                System.err.print("Error at closing Stream/Socket " + e);
            }
        }
    }

    public void sendPackage(DataPackage dataPackage) throws IOException
    {
        outputStream.writeObject(dataPackage);
    }

}
