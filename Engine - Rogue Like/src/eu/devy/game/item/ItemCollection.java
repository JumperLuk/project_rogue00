package eu.devy.game.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemCollection
{
	private static List<Item> collection = new ArrayList<Item> ();
	
	public static void init()
	{
		add(new Item(Type.POTION, 0, 0, 10.0f, 0.0f));
		add(new Item(Type.HELMET, 0, 10, 10.0f, 0.0f));
		add(new Item(Type.CHESTPLATE, 0, 20, 10.0f, 0.0f));
		add(new Item(Type.LEGGINGS, 0, 15, 10.0f, 0.0f));
	}
	
	public static void add(Item item)
	{
		collection.add(item);
	}
	
	public static void remove(Item item)
	{
		collection.remove(item);
	}
	
	public static Item getRandomItem()
	{
		return collection.get(new Random().nextInt(collection.size()));
	}
	
	public static List<Item> getCollection()
	{
		return collection;
	}
}
