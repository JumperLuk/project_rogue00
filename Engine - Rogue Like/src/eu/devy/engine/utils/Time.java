package eu.devy.engine.utils;

public class Time 
{
	private static final int MAX_FPS = 60;
	private static final double TIME_PER_TICK = 1000000000 / MAX_FPS;
	
	private static double lastTime;
	private static double deltaTime;
	private static double time;
	
	public static void init()
	{
		lastTime = System.nanoTime();
	}
	
	public static void calculate()
	{
		time = System.nanoTime();
		deltaTime += (time - lastTime) / TIME_PER_TICK;
		lastTime = time;
	}
	
	public static boolean tick()
	{
		if(deltaTime >= 1)
		{
			deltaTime--;
			return true;
		}
		return false;
	}
	
	public static boolean draw()
	{
		return true;
	}
}