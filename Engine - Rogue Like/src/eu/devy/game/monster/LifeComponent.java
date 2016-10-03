package eu.devy.game.monster;

import java.awt.Graphics;

import eu.devy.engine.defaults.Display;
import eu.devy.engine.utils.Resources;

public class LifeComponent extends Component
{
	private static final int DEFAULT_START_LV = 1;
	
	private static final int DEFAULT_MAXIMUM_HEART_CONTAINER = 8;
	
	private double atk;
	private double def;
	private double wlk_spd;
	private double atk_spd;

	private double max;
	private double hp;
	
	private int level;

	private boolean isGlobalViewVisible = false;
	private boolean isLocalViewVisible = true;
	
	public LifeComponent(Entity instance)
	{
		super("LifeComponent", instance);
		
		this.level = DEFAULT_START_LV;
		
		calculate();
	}
	
	@Override
	public void tick() 
	{
		
	}

	@Override
	public void draw(Graphics graphics)
	{
		if(isGlobalViewVisible)
		{
			float health_per_heart = (float) (max / DEFAULT_MAXIMUM_HEART_CONTAINER);
			
			for(int i = 0; i < DEFAULT_MAXIMUM_HEART_CONTAINER; i++)
			{
				if(hp >= i * health_per_heart)
				{
					graphics.drawImage(Resources.HEART_A, (Display.get().getWidth() - DEFAULT_MAXIMUM_HEART_CONTAINER * 64) / 2 + i * 64, 32, 64, 64, null);
				}
				else
				{
					graphics.drawImage(Resources.HEART_B, (Display.get().getWidth() - DEFAULT_MAXIMUM_HEART_CONTAINER * 64) / 2 + i * 64, 32, 64, 64, null);
				}
			}
		}
		
		if(isLocalViewVisible)
		{
			
		}
	}
	
	private void calculate()
	{
		atk = ((double)5 * ((double)(Math.expm1(((double)(Math.log(298 / 5) / 255)) * level)))) + level;
		def = ((double)5 * ((double)(Math.expm1(((double)(Math.log(298 / 5) / 255)) * level)))) + level;
		wlk_spd = 8;
		atk_spd= -10 * level + 1010;
	
		max = (int)((double)((10  /Math.expm1(0.0175)) * (double)(Math.expm1(0.0175 * level))) + level - 1);
		hp = max;
	}
	
	public void damage(double damage)
	{
		hp -= damage;
	}
	
	public void setGlobalViewVisible(boolean isGlobalViewVisible)
	{
		this.isGlobalViewVisible = isGlobalViewVisible;
	}
	
	public void setLocalViewVisible(boolean isLocalViewVisible)
	{
		this.isLocalViewVisible = isLocalViewVisible;
	}
	
	public double getAtk()
	{
		return atk;
	}
	
	public double getDef()
	{
		return def;
	}
	
	public double getWalkSpeed()
	{
		return wlk_spd;
	}
	
	public double getAttackSpeed()
	{
		return atk_spd;
	}
	

	public double getDefenceEfficency(){
		return (int)(-Math.expm1(-0.0021*level+Math.log(84.25))+0.02*level+84.25);
	}

	public void setHealtPoints(double d) {
		hp = d;		
	}
	
	public double getHealtPoints()
	{
		return hp;
	}
}
