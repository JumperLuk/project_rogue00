package eu.devy.ui;

import java.awt.Color;
import java.awt.Graphics;

import eu.devy.engine.defaults.Keyboard;
import eu.devy.engine.defaults.Keyboard.KeyCode;
import eu.devy.engine.utils.Resources;

public class UIList extends UI
{
	private static final Color FOCUSED = new Color(255, 255, 255);
	private static final Color UNFOCUSED = new Color(200, 200, 200);
	
	private String[] list;
	private int index = 0;
	private int selec = -1;
	
	public UIList(Rect rect, String[] list)
	{
		super(rect);
		
		this.list = list;
	}

	@Override
	public void tick() 
	{
		if(Keyboard.getKeyDown(KeyCode.ARROW_UP))
		{
			index--;	
			
			if(index < 0)
			{
				index = list.length - 1;
			}
		}
		
		if(Keyboard.getKeyDown(KeyCode.ARROW_DOWN))
		{
			index++;
			
			if(index > list.length - 1)
			{
				index = 0;
			}
		}
		
		if(Keyboard.getKeyDown(KeyCode.SPACE) || Keyboard.getKeyDown(KeyCode.ENTER))
		{
			selec = index;
		}
	}

	@Override
	public void draw(Graphics graphics) 
	{
		for(int i = 0; i < list.length; i++)
		{
			float addition = 0.0f;
			int height = GraphicsCalculator.getTextHeight(graphics);
					
			graphics.setColor(UNFOCUSED);
			
			if(i == index)
			{
				addition = 10.0f;
				graphics.setColor(FOCUSED);
			}
			graphics.setFont(Resources.DEFAULT_FONT.deriveFont(38.0f + addition));
			
			graphics.drawString(list[i], getRect().getX() - GraphicsCalculator.getTextWidth(graphics, list[i]) / 2, getRect().getY() + i * (getRect().getHeight() + height));
		
			graphics.setFont(Resources.DEFAULT_FONT.deriveFont(38.0f));
		}
	}
	
	public boolean isSelected(String text)
	{
		if(selec > -1)
		{
			if(text.equalsIgnoreCase(list[selec]))
			{
				selec = -1;
				return true;
			}
		}
		return false;
	}
}
