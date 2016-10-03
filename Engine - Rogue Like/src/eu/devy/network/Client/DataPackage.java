package eu.devy.network.Client;

import java.util.Arrays;

public class DataPackage 
{
	public static final String SEPERATOR = ";";
	
	private String id;
	private DataPackageAction action;
	
	private String[] args;
	
	public DataPackage(String id, DataPackageAction action, String[] args)
	{
		this.id = id;
		this.action = action;
		this.args = args;
	}
	
	public String id()
	{
		return id;
	}
	
	public DataPackageAction getDataPackageAction()
	{
		return action;
	}
	
	public String getData(int index)
	{
		if(index > -1 && index < args.length - 1)
		{
			return args[index];
		}
		return null;
	}
	
	public String[] getDataSet()
	{
		return args;
	}
	
	public static DataPackage convert(String text)
	{
		String[] args = text.split(SEPERATOR);
		return new DataPackage(args[0], DataPackageAction.valueOf(args[1].toUpperCase()), Arrays.copyOfRange(args, 2, args.length - 1));
	}
	
	public static String toString(DataPackage dataPackage)
	{
		String appn = dataPackage.id() + SEPERATOR + dataPackage.getDataPackageAction().toString().toLowerCase();
		String prpn = "";
		
		for(int i = 2; i < dataPackage.getDataSet().length; i++)
		{
			prpn = prpn + SEPERATOR + dataPackage.getData(i);
		}
		return appn + prpn;
	}
}
