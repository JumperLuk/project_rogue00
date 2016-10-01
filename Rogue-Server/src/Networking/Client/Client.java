package Networking.Client;

import Networking.DataPackage;

import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by schueler on 01.10.2016.
 */
public class Client extends Thread{

    private Socket socket;

    private BufferedReader inputStream = null;
    private PrintWriter outputStream = null;

    private InetAddress inetAddress;
    private int port;

    private boolean keepRunning;

    private boolean initiated;

    public Client(InetAddress inetAddress, int port)
    {
        this.inetAddress = inetAddress;
        this.port = port;
        this.initiated = false;
    }

    @Override
    public void run()
    {
        try {
            socket = new Socket(inetAddress, port);
            outputStream = new PrintWriter(socket.getOutputStream(), true);
            inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            initiated = true;
        }
        catch (Exception e)
        {
            System.err.println("Failed at creating Socket or Stream(Client): " + e);
            return;
        }

        keepRunning = true;

        while(keepRunning) {

            try {
                int temp = inputStream.read();
                System.out.println(temp);
                DataPackage dataPackage = DataPackage.toDatapackage("" + temp);
                processPackage(dataPackage);
                Thread.sleep(10);

            } catch (Exception e) {
                System.err.println("Sleep or availability failed(Client): " + e);
            }
        }
    }

    public void processPackage(DataPackage dataPackage)
    {
        //Do stuff!
        System.out.print("Process pack: " + dataPackage.toString());
    }

    public void send(DataPackage dataPackage)
    {
        if(!initiated)
            return;

        try {
            outputStream.println(dataPackage.toString());
        }
        catch (Exception e)
        {
            System.err.println("Failed to send package(Client): " + e);
        }
    }

    public void close()
    {
        keepRunning = false;

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
            System.err.println("Failed at closing Client: " + e);
        }
    }

}
