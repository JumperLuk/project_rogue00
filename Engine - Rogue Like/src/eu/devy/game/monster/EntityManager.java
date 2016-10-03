package eu.devy.game.monster;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import eu.devy.game.player.Dummy;
import eu.devy.game.world.Location;
import eu.devy.network.Client.DataPackage;

public class EntityManager
{
	private static final int ELEMENTS_LIMIT = 99;
	
	public static ArrayList<Entity> entity_collection = new ArrayList<>();

	private static Dummy[] dummy_list = new Dummy[3];
	
	public static void add(Entity e)
	{
		entity_collection.add(e);
	}
	
	public static void remove(Entity e)
	{
		entity_collection.remove(e);
	}
	
	public static Entity find(String id)
	{
		for(int i = 0;i<entity_collection.size();i++){
			if(entity_collection.get(i).id().equals(id))return entity_collection.get(i);
		}
		return null;
	}
	
	public static Entity find(Location Location)
	{
		for(int i = 0;i<entity_collection.size();i++){
			if(entity_collection.get(i).getLocation().equals(Location))return entity_collection.get(i);
		}
		return null;
	}

	public static String keygen()
	{
		String keygen = String.format("%0" + Integer.toString(ELEMENTS_LIMIT).length() + "d", new Random().nextInt(ELEMENTS_LIMIT));
		
		for(Entity e : entity_collection)
		{
			if(e.id().equalsIgnoreCase(keygen))
			{
				keygen = keygen();
				break;
			}
		}
		
		return keygen;
	}
	
	public static String register(Entity e)
	{
		String id = keygen();
		add(e);
		return id;
	}
	
	public static void registerNewDummy(Dummy dummy)
	{
		int lastEmptyIndex = -1;
		
		for(int i = 0; i < dummy_list.length; i++)
		{
			if(dummy_list[i].equals(dummy))
			{
				return;
			}
			
			if(dummy_list[i] == null)
			{
				if(lastEmptyIndex < 0)
				{
					lastEmptyIndex = i;
				}
			}
		}
		
		dummy_list[lastEmptyIndex] = dummy;
		
		System.out.println("register new dummy");
	}
	
	public static void updateDummy(DataPackage dataPackage)
	{
		for(Dummy dummy : dummy_list)
		{
			if(dummy.id().equals(dataPackage.id()))
			{
				dummy.getLocation().setLocation(Integer.parseInt(dataPackage.getData(0)), Integer.parseInt(dataPackage.getData(1)));
				break;
			}
		}
	}
	
	public static void tickDummy()
	{
		for(Dummy dummy : dummy_list)
		{
			if(dummy != null)
			{
				dummy.tick();
			}
		}
	}
	
	public static void drawDummy(Graphics graphics)
	{
		for(Dummy dummy : dummy_list)
		{
			if(dummy != null)
			{
				dummy.draw(graphics);
			}
		}
	}
}
