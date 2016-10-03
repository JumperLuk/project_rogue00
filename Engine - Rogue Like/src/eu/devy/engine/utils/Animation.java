package eu.devy.engine.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import eu.devy.ui.Rect;

public class Animation 
{
	private BufferedImage[] images;
	private int velocity;
	
	private double lastTime = System.currentTimeMillis();
	private double time = 0.0d;
	
	private Rect rect;
	
	private int index = 0;
	
	public Animation(BufferedImage[] images, Rect rect, int velocity)
	{
		this.images = images;
		this.rect = rect;
		this.velocity = velocity;
	}
	
	public void tick()
	{
		if(images.length > 1)
		{
			time += System.currentTimeMillis() - lastTime;
			lastTime = System.currentTimeMillis();
			
			if(time > velocity)
			{
				time = 0.0d;
				
				index++;
				
				if(index > images.length - 1)
				{
					index = 0;
				}
			}
		}
	}
	
	public void draw(Graphics graphics)
	{
		if(images.length > 0)
		{
			graphics.drawImage(images[index], rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), null);
		}
	}
	
	public void setIndex(int index)
	{
		this.index = index;
	}
	
	public void setImages(BufferedImage[] images)
	{
		this.index = 0;
		this.images = images;
	}
	
	public void setVelocity(int velocity)
	{
		this.velocity = velocity;
	}
	
	public void setRect(Rect rect)
	{
		this.rect = rect;
	}
}
