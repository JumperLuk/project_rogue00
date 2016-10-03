package eu.devy.game.monster;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Transform extends Component
{
	private float x;
	private float y;
	private int w; 
	private int h;
	
	public Transform()
	{
		
	}
	
	public Transform(Entity instance)
	{
		super("Transform", instance);
	}

	@Override
	public void tick()
	{
		
	}

	@Override
	public void draw(Graphics graphics) 
	{
		
	}
	public float getX() 
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(int y) 
	{
		this.y = y;
	}

	public int getWidth()
	{
		return w;
	}

	public void setWidth(int width) 
	{
		this.w = width;
	}

	public int getHeight()
	{
		return h;
	}

	public void setHeight(int height)
	{
		this.h = height;
	}
	
	public void setPosition(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setResolution(int width, int height)
	{
		this.w = width;
		this.h = height;
	}
	
	public void setTransform(float x, float y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.w = width;
		this.h = height;
	}
	
	public void setTransform(Transform transform)
	{
		this.x = transform.getX();
		this.y = transform.getY();
		this.w = transform.getWidth();
		this.h = transform.getHeight();
	}
	
	public Rectangle rectangle()
	{
		return new Rectangle((int)x, (int)y, w, h);
	}
	
	public boolean intersects(Transform transform)
	{
		if(transform.rectangle().intersects(rectangle()))
		{
			return true;
		}
		return false;
	}
}
