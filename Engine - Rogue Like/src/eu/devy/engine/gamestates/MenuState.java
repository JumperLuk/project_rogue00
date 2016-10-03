package eu.devy.engine.gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.net.InetAddress;
import java.net.UnknownHostException;

import eu.devy.engine.defaults.Display;
import eu.devy.engine.defaults.Engine;
import eu.devy.engine.utils.AudioSource;
import eu.devy.engine.utils.Resources;
import eu.devy.engine.utils.Scene;
import eu.devy.engine.utils.Splash;
import eu.devy.network.Network;
import eu.devy.network.NetworkType;
import eu.devy.ui.GraphicsCalculator;
import eu.devy.ui.Rect;
import eu.devy.ui.UIList;
import eu.devy.ui.UITextArea;

public class MenuState extends State
{
	private static final float ABSOLUTE_OFFSET = 25;
	private static final float OFFSET_UNTI = 0.75f;

	private UITextArea textArea = new UITextArea("IP   ", new Rect(15, Display.get().getHeight() - 20, 100, 100));
	
	private UIList list = new UIList(
			new Rect(Display.get().getWidth() / 2, Display.get().getHeight() - Display.get().getHeight() / 3 , 0, 10), 
			new String[] {
					"Enter   Dungeon", 
					"Exit"
			});
	private boolean init = false;
	
	private AudioSource audiosource;
	private boolean audio_init = false;
	
	private boolean next = false;

	private double lastTime = System.currentTimeMillis();
	private double time = 0.0d;
	
	private float offset = 0.0f;
	
	private boolean invert = false;
	
	public MenuState() 
	{
		super(Id.MENU);
		
		audiosource = new AudioSource("/audio/theme" + AudioSource.FILE_EXTENSION);
		audiosource.setVolume(0.05f);
		
		textArea.setText(Network.DEFAULT_ADDRESS);
	}

	@Override
	public void tick() 
	{
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(!init || next)
		{
			return;
		}
		
		if(!audio_init)
		{
			audiosource.loop();
			audio_init = true;
		}
		
		if(list.isSelected("Enter   Dungeon"))
		{
			if(textArea.getText().equals(Network.DEFAULT_ADDRESS))
			{
				Network.setNetworkType(NetworkType.HOST);
			}
			else
			{
				Network.setNetworkType(NetworkType.GUEST);
			}
			Network.setAddress(textArea.getText());
			next = true;
		}
		
		if(list.isSelected("Exit"))
		{
			Engine.destroy();
		}
		
		list.tick();
		textArea.tick();
		
		if(time > 10)
		{
			if(invert)
			{
				offset-= OFFSET_UNTI;
			}
			else
			{
				offset += OFFSET_UNTI;
			}
			
			if(Math.abs(offset) > ABSOLUTE_OFFSET)
			{
				invert = !invert;
			}
			time = 0.0d;
		}
	}

	@Override
	public void draw(Graphics graphics) 
	{
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Display.get().getWidth(), Display.get().getHeight());

		graphics.drawImage(Resources.SKULL_TOP, (Display.get().getWidth() - 512) / 2, - 100 + (int)offset, 512, 512, null);
		graphics.drawImage(Resources.SKULL_BOT, (Display.get().getWidth() - 512) / 2, Display.get().getHeight() - 300 - (int)offset, 512, 512, null);
		
		list.draw(graphics);
		textArea.draw(graphics);
		
		try
		{
			graphics.drawString(InetAddress.getLocalHost().getHostAddress(), Display.get().getWidth() - GraphicsCalculator.getTextWidth(graphics, InetAddress.getLocalHost().getHostAddress()) - 15, Display.get().getHeight() - 20);
		}
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
		}
		
		if(!init)
		{
			init = Splash.draw((Graphics2D)graphics);
		}
		
		if(next)
		{
			if(!Scene.fade((Graphics2D)graphics, true))
			{
				next = false;
				State.set(Id.GAME);
			}
		}
	}
}
