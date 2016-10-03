package eu.devy.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import eu.devy.network.Client.Client;
import eu.devy.network.Server.Connection;
import eu.devy.network.Server.Server;

public class Network
{
	public static final int DEFAULT_PORT = 4040;
	public static final String DEFAULT_ADDRESS = "127.0.0.1";
	
	private static NetworkType type;
	
	private static Server server;
	private static Client client;
	
	private static String address;
	
	public static void setNetworkType(NetworkType type)
	{
		Network.type = type;
	}
	
	public static NetworkType getNetworkType()
	{
		return Network.type;
	}
	
	public static void createNewServer()
	{
		if(server != null)
		{
			server.close();
		}
		server = new Server(DEFAULT_PORT);
		server.start();
	}
	
	public static Server getServer()
	{
		return Network.server;
	}
	
	public static void createNewClient(String ip)
	{
		try
		{
			//client = new Client(new Connection(new Socket(InetAddress.getByName(ip), DEFAULT_PORT)));
			client = new Client(new Connection(new Socket(InetAddress.getByName("127.0.0.1"), DEFAULT_PORT)));
		}
		catch (IOException e) 
		{
			System.exit(-1);
		}
	}
	
	public static Client getClient()
	{
		return client;
	}
	
	public static String getAddress()
	{
		return address;
	}
	
	public static void setAddress(String address)
	{
		Network.address = address;
	}
}
