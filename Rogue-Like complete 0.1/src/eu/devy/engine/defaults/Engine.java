package eu.devy.engine.defaults;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import eu.devy.engine.gamestates.State;
import eu.devy.engine.utils.Time;


public class Engine implements Runnable
{
	private static final int WIDTH = 1280;
	private static final int HEIGHT = WIDTH / 9 * 16;
	
	private static final String TITLE = "Rogue Like";
	
	private static final int MAX_BUFFER_STRATEGY = 2;

	private static boolean isRunning;
	private static Thread thread;
	
	public Engine()
	{
		new Display(TITLE, WIDTH, HEIGHT);
		new Keyboard();
	}
	
	private void tick()
	{
		if(!State.isEmpty())
		{
			State.get().tick();
		}
	}
	
	private void draw()
	{
		BufferStrategy strategy = Display.get().getCanvas().getBufferStrategy();
		
		if(strategy == null)
		{
			Display.get().getCanvas().createBufferStrategy(MAX_BUFFER_STRATEGY);
			return;
		}
		
		Graphics graphics = strategy.getDrawGraphics();
		
		graphics.clearRect(0, 0, Display.get().getWidth(), Display.get().getHeight());
	
		if(!State.isEmpty())
		{
			State.get().draw(graphics);
		}
		
		strategy.show();
		
		graphics.dispose();
	}

	@Override
	public void run() 
	{
		while(isRunning)
		{	
			Time.calculate();

			if(Time.tick())
			{
				tick();
			}
			
			if(Time.draw())
			{
				draw();
			}
			
			Time.getFPS();
		}
		
		stop();
	}
	
	public synchronized void start()
	{
		if(!isRunning)
		{
			isRunning = true;
			
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public synchronized void stop()
	{
		if(isRunning)
		{
			isRunning = false;
			
			try
			{
				thread.join();
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		
		destroy();
	}
	
	public static void destroy()
	{
		System.exit(-1);
	}
	
	public static boolean isRunning()
	{
		return Engine.isRunning;
	}
}