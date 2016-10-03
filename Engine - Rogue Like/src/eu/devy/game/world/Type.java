package eu.devy.game.world;

import java.awt.image.BufferedImage;

import eu.devy.engine.utils.Utils;

public enum Type 
{
	GROUND("Ground", false),
	WALL("Wall", true),
	STONE("Stone", true),
	GOAL("Goal", false),
	SPAWN("Spawn", false),
	ITEM_DROP("Itemdrop", false);
	
	private static final String DEFAULT_PATH = "/textures/structures/";
	private static final String FILE_EXTENSION = ".png";
	
	private BufferedImage image;
	private boolean isSolid;
	
	private Type(String name, boolean isSolid)
	{
		this.image = Utils.Images.load(DEFAULT_PATH + name.toLowerCase() + FILE_EXTENSION);
		this.isSolid = isSolid;
	}
	
	public boolean isSolid()
	{
		return isSolid;
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
}
