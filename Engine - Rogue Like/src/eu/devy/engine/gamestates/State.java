package eu.devy.engine.gamestates;

import java.awt.Graphics;

public abstract class State
{
	public enum Id
	{
		MENU(0),
		GAME(1);
		
		private final int value;
		
		private Id(final int value)
		{
			this.value = value;
		}
		
		public int value()
		{
			return value;
		}
	};
	
	private static State[] all = new State[Id.values().length];
	private static State instance;
	
	private Id id;
	
	public State(Id id)
	{
		if(!exists(id))
		{
			this.id = id;
			all[id.value()] = this;
		}
	}
	
	public Id getId()
	{
		return id;
	}
	
	public static boolean isEmpty()
	{
		if(instance != null)
		{
			return false;
		}
		return true;
	}
	
	public static boolean exists(Id id)
	{
		return (find(id) != null);
	}
	
	public static State find(Id id)
	{
		for(int i = 0; i < all.length; i++)
		{
			if(all[i] != null)
			{
				if(all[i].getId().equals(id))
				{
					return all[i];
				}
			}
		}
		return null;
	}
	
	public static State get()
	{
		return instance;
	}
	
	public static void set(Id id)
	{
		instance = find(id);
	}
	
	public abstract void tick();
	public abstract void draw(Graphics graphics);
}