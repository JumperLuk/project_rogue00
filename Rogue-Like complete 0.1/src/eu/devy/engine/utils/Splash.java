package eu.devy.engine.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import eu.devy.engine.defaults.Display;

public class Splash
{
	private static double lastTime = System.currentTimeMillis();
	private static double time = 0.0d;
	
	private static float alpha = 1.0f;
	
	private static boolean fadeIn = true;
	private static boolean fadeOut = false;
	private static boolean wait = false;

	private static int fadeTime = 50;
	private static int waitTime = 3000;
	
	private static boolean introduction = true;
	
	public static boolean draw(Graphics2D graphics)
	{
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(introduction)
		{
			graphics.setColor(Color.BLACK);
			graphics.fillRect(0, 0, Display.get().getWidth(), Display.get().getHeight());
			graphics.drawImage(Resources.TITLE, (Display.get().getWidth() - Resources.TITLE.getWidth()) / 2, (Display.get().getHeight() - Resources.TITLE.getHeight()) / 2, null);
			
			if(!wait)
			{	
				if(time > fadeTime)
				{
					if(fadeIn)
					{
						alpha -= 0.1f;
						
						if(alpha < 0.0f)
						{
							alpha = 0.0f;
						}
						
						time = 0.0d;
					}
					
					if(fadeOut)
					{
						alpha += 0.1f;
						
						if(alpha > 1.0f)
						{
							alpha = 1.0f;
						}
						
						time = 0.0d;
					}
					
					if(alpha == 0.0f)
					{
						if(fadeIn)
						{
							fadeIn = false;
							fadeOut = true;
							wait = true;
						}
					}
					
					if(alpha == 1.0f)
					{
						if(fadeOut)
						{
							fadeIn = true;
							fadeOut = false;
							introduction = false;
						}
					}
				}
			}
			else
			{
				if(time > waitTime)
				{
					wait = false;
				}
			}
		}
		else
		{
			if(time > fadeTime)
			{
				alpha -= 0.1f;
				
				if(alpha < 0.0f)
				{
					alpha = 0.0f;
				}
				
				time = 0.0d;
			}
			
			if(alpha == 0.0f)
			{
				introduction = true;
				alpha = 1.0f;
				
				return true;
			}
		}
		
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Display.get().getWidth(), Display.get().getHeight());
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
	
		return false;
	}
}
