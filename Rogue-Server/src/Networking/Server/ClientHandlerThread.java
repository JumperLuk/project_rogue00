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

    PrintWriter outputStream = null;
    BufferedReader inputStream = null;

    private String messageToSend;
    private boolean sendMessage;
    private boolean keepRunning;

    public ClientHandlerThread(Socket socket, Server server)
    {
        this.socket = socket;
        this.server = server;
        this.keepRunning = true;
    }

    @Override
    public void run()
    {
        keepRunning = true;

        try {
            outputStream = new PrintWriter(socket.getOutputStream(), true);
            inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (keepRunning)
            {
                String temp = inputStream.readLine();
                System.out.println(temp);
                DataPackage dataPackage = DataPackage.toDatapackage(temp);
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

    public void close()
    {
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
            System.err.println("Failed at closing one Serverclient: "+ e);
        }
    }

    public void writeToClient(DataPackage dataPackage) throws IOException
    {
        outputStream.println(dataPackage.toString());
    }

}
