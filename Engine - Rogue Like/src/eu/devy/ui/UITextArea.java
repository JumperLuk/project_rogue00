package eu.devy.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import eu.devy.engine.defaults.Keyboard;
import eu.devy.engine.defaults.Keyboard.KeyCode;
import eu.devy.engine.defaults.Keyboard.KeyCodeType;
import eu.devy.engine.utils.Resources;

public class UITextArea extends UI
{	
	private String prefix;
	private String text;
	
	private double lastTime = System.currentTimeMillis();
	private double time = 0.0d;
	private boolean toggle = false;
	
	public UITextArea(String prefix, Rect rect)
	{
		super(rect);	
		this.prefix = prefix;
		this.text = "";
	}
	
	@Override
	public void tick()
	{
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(time > 800)
		{
			toggle = !toggle;
			time = 0.0d;
		}
		
		KeyCode keyCode = Keyboard.getLastKeyCode();

		if(keyCode != null)
		{
			if(keyCode.equals(KeyCode.BACKSLASH))
			{
				if(text.length() > 0)
				{
					text = text.substring(0, text.length() - 1);
				}
			}
			
			if(keyCode.getType().equals(KeyCodeType.NUMERIC) || keyCode.getType().equals(KeyCodeType.SPECIAL_SELECT))
			{
				text += keyCode.text();
			}
		}
	}

	@Override
	public void draw(Graphics graphics) 
	{
		graphics.setColor(Color.WHITE);
		graphics.setFont(Resources.DEFAULT_FONT.deriveFont(49.0f));
		graphics.drawString(prefix + text, getRect().getX(), getRect().getY());
	
		if(toggle)
		{
			graphics.drawString("I", getRect().getX(), getRect().getY());
		}
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public String getText()
	{
		return text;
	}
}
