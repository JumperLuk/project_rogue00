package eu.devy.game.item;

import java.awt.image.BufferedImage;

import eu.devy.engine.utils.Utils;

public enum Type 
{
	WEAPON(1, "Weapon"),
	HELMET(2, "Helmet"),
	CHESTPLATE(3, "Chestplate"),
	GLOVES(4, "Gloves"),
	LEGGINGS(5, "Leggings"),
	BOOTS(6, "Boots"),
	POTION(0, "Potion");

	private static final String DEFAULT_PATH = "/textures/items/";
	private static final String FILE_EXTENSION = ".png";
	
	private final int id;
	private String name;
	
	private BufferedImage image;
	
	private Type(final int id, String name)
	{
		this.id = id;
		this.name = name;
		
		this.image = Utils.Images.load(DEFAULT_PATH + name + FILE_EXTENSION);
	}
	
	public int id()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
}
