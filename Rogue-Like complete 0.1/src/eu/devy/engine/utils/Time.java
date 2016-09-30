package eu.devy.engine.utils;

public class Time 
{
	private static final int MAX_FPS = 60;
	private static final double TIME_PER_TICK = 1000000000 / MAX_FPS;
	
	private static double lastTime;
	private static double deltaTime;
	private static double time;
	
	private static double timer;
	
	private static int tps;
	private static int fps;
	
	public static void init()
	{
		lastTime = System.nanoTime();
	
		timer = System.currentTimeMillis();
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
			tps++;
			deltaTime--;
			return true;
		}
		return false;
	}
	
	public static boolean draw()
	{
		fps++;
		return true;
	}
	
	public static int getFPS()
	{
		if(System.currentTimeMillis() - timer > 1000)
		{
			timer += 1000;
			System.out.println("FPS: " + fps + ", TPS: " + tps);
			fps = 0;
			tps = 0;
		}
		return fps;
	}
	
	public static int getTPS()
	{
		return tps;
	}
}