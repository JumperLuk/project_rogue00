package eu.devy.game.world;

public class DungeonGenerator 
{
	private static final int DEFAULT_DUNGEON_LAYER_BEGIN = 1;
	
	private static String keygen;
	
	private static Dungeon instance;
	private static int dungeon_level = DEFAULT_DUNGEON_LAYER_BEGIN;
	
	public static void recive(String keygen)
	{
		generate(DungeonGenerator.keygen = keygen);
	}
	
	private static void generate(String keygen)
	{
    	for(int i = 0; i < 11; i++)
    	{
    		int newNumber = Integer.parseInt("" + keygen.charAt(i)) + dungeon_level;
    		
    		String before = keygen.substring(0, i);
    		
    		char at = String.valueOf(newNumber).charAt(0);
    		
    		if(String.valueOf(newNumber).length() > 1)
    		{
    			at = String.valueOf(newNumber).charAt(String.valueOf(newNumber).length() - 1);
    		}
    
    		keygen = before + at + keygen.substring(i + 1, keygen.length() - 1) + String.valueOf(dungeon_level);
    	}
    	
    	DungeonGenerator.keygen = keygen;
    	
		set(new Dungeon(keygen));
	}
	
	public static boolean exists()
	{
		return instance != null;
	}
	
	public static void set(Dungeon dungeon)
	{
		DungeonGenerator.instance = dungeon;
	}
	
	public static Dungeon get()
	{
		return instance;
	}
	
	public static int getDungeonLevel()
	{
		return dungeon_level;
	}
	
	public static void next()
	{
		generate(keygen);
	}
}
