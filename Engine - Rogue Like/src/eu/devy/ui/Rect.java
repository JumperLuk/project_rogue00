package eu.devy.ui;

public class Rect 
{
	private int x;
	private int y;
	private int w;
	private int h;
	
	public Rect(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public void setX(int x)
	{
		this.x = x;
	}
	
	public int getX()
	{
		return x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getY()
	{
		return y;
	}

	public void setWidth(int w)
	{
		this.w = w;
	}
	
	public int getWidth()
	{
		return w;
	}

	public void setHeight(int h)
	{
		this.h = h;
	}
	
	public int getHeight()
	{
		return h;
	}
	
	public void setRect(Rect rect)
	{
		this.x = rect.getX();
		this.y = rect.getY();
		this.w = rect.getWidth();
		this.h = rect.getHeight();
	}
	
	public Rect getRect()
	{
		return this;
	}
}
