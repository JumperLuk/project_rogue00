package eu.devy.engine.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class SpriteSheet 
{
	private static final Color[] DEFAULT_COLORS_TO_REPLACE = new Color[]
			{
				new Color(255, 65, 190),
				new Color(255, 204, 238)
			};
	
	private BufferedImage image;
	
	public SpriteSheet(String path)
	{
		BufferedImage temp = Utils.Images.adjust(Utils.Images.load(path));
		
		for(Color color : DEFAULT_COLORS_TO_REPLACE)
		{
			temp = Utils.Images.filter(temp, color);
		}
		
		this.image = temp;
	}
	
	public BufferedImage getSubImage(int x, int y, int w, int h)
	{
		return this.image.getSubimage(x, y, w, h);
	}
}
