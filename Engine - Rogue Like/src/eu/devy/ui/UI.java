package eu.devy.ui;

import java.awt.Graphics;

public abstract class UI 
{
	private Rect rect;
	
	public UI(Rect rect)
	{
		this.rect = rect;
	}
	
	public void setRect(Rect rect)
	{
		this.rect = rect;
	}
	
	public Rect getRect()
	{
		return rect;
	}
	
	public abstract void tick();
	public abstract void draw(Graphics graphics);
}
