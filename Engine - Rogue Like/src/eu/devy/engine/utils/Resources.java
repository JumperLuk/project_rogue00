package eu.devy.engine.utils;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Resources 
{
	public static final BufferedImage TITLE = Utils.Images.load("/textures/splash.jpg");

	public static final BufferedImage SKULL_TOP = Utils.Images.load("/textures/graphical/skull-top.png");
	public static final BufferedImage SKULL_BOT = Utils.Images.load("/textures/graphical/skull-bot.png");
	
	public static final BufferedImage INVENTORY_SLOT = Utils.Images.load("/textures/graphical/graphical-inventory.png");
	
	private static final BufferedImage HEART_SET = Utils.Images.load("/textures/graphical/graphical-statistics.png");
	public static final BufferedImage HEART_A = HEART_SET.getSubimage(0, 0, 16, 16);
	public static final BufferedImage HEART_B = HEART_SET.getSubimage(16, 0, 16, 16);
	
	private static final BufferedImage CHARACTER_GR = Utils.Images.load("/textures/character-anim-01.png");
	public static final BufferedImage[] CHARCTER_GR_IDL_FRONT = new BufferedImage[] { CHARACTER_GR.getSubimage(0, 0, 64, 96), CHARACTER_GR.getSubimage(64, 0, 64, 96) }; 
	public static final BufferedImage[] CHARCTER_GR_IDL_BACK = new BufferedImage[] { CHARACTER_GR.getSubimage(128, 0, 64, 96), CHARACTER_GR.getSubimage(192, 0, 64, 96) }; 
	public static final BufferedImage[] CHARCTER_GR_IDL_RIGHT = new BufferedImage[] { CHARACTER_GR.getSubimage(0, 96, 64, 96), CHARACTER_GR.getSubimage(64, 96, 64, 96) }; 
	public static final BufferedImage[] CHARCTER_GR_IDL_LEFT = new BufferedImage[] { CHARACTER_GR.getSubimage(128, 96, 64, 96), CHARACTER_GR.getSubimage(192, 96, 64, 96) }; 
	
	public static final Font DEFAULT_FONT = Utils.Fonts.load("res/font.ttf");
}
