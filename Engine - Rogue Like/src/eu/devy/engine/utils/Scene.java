package eu.devy.engine.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import eu.devy.engine.defaults.Display;

public class Scene 
{
	private static final float DEFAULT_MAXIMUM_ALPHA = 1.0f;
	private static final float DEFAULT_MINIMUM_ALPHA = 0.0f;
	
	private static final float DEFAULT_ALPHA_UNIT = 0.05f;
	
	private static double lastTime;
	private static double time;
	
	private static float alpha = 0.0f;
	
	private static boolean init = false;
	
	public static boolean fade(Graphics2D graphics, boolean out)
	{
		if(!init)
		{
			lastTime = System.currentTimeMillis();
			time = 0.0d;
			init = true;
			
			if(out)
			{
				alpha = DEFAULT_MINIMUM_ALPHA;
			}
			else
			{
				alpha = DEFAULT_MAXIMUM_ALPHA;
			}
		}
		
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(time > 30)
		{
			if(!out)
			{
				alpha -= DEFAULT_ALPHA_UNIT;
				
				if(alpha < DEFAULT_MINIMUM_ALPHA)
				{
					alpha = DEFAULT_MINIMUM_ALPHA;
					return (init = false);
				}
			}
			else
			{
				alpha += DEFAULT_ALPHA_UNIT;
				
				if(alpha > DEFAULT_MAXIMUM_ALPHA)
				{
					alpha = DEFAULT_MAXIMUM_ALPHA;
					return (init = false);
				}
			}
			time = 0.0d;
		}

		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Display.get().getWidth(), Display.get().getHeight());
		
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		
		return true;
	}
}
