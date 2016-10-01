package Networking.Client;

import Networking.DataPackage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by schueler on 01.10.2016.
 */
public class Client extends Thread{

    private Socket socket;

    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;

    private InetAddress inetAddress;
    private int port;

    private boolean keepRunning;

    public Client(InetAddress inetAddress, int port)
    {
        this.inetAddress = inetAddress;
        this.port = port;

        try {
            socket = new Socket(inetAddress, port);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        }
        catch (Exception e)
        {
                System.err.println("Failed at creating Socket or Stream(Client): " + e);
        }
    }

    @Override
    public void run()
    {
        keepRunning = true;

        while(true) {
            if (!keepRunning)
                break;

            try {

                while (inputStream.available() > 0) {
                    DataPackage dataPackage = (DataPackage) inputStream.readObject();
                    processPackage(dataPackage);
                }
                Thread.sleep(10);

            } catch (Exception e) {
                System.err.println("Sleep or availability failed(Client): " + e);
            }
        }
    }

    public void processPackage(DataPackage dataPackage)
    {
        //Do stuff!
        System.out.print(dataPackage.getTest());
    }

    public void send(DataPackage dataPackage)
    {
        try {
            outputStream.writeObject(dataPackage);
        }
        catch (Exception e)
        {
            System.err.println("Failed to send package(Client): " + e);
        }
    }

    public void halt()
    {
        keepRunning = false;
    }
}
