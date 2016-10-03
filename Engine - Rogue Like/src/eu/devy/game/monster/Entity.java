package eu.devy.game.monster;

import java.awt.Graphics;
import java.util.ArrayList;

import eu.devy.game.world.Location;

public abstract class Entity
{
	public ArrayList<Component> components = new ArrayList<Component>();

	private Transform transform;
	private Location location;
	
	private String id;
	
	public Entity()
	{
		this.id = EntityManager.register(this);
		
		this.location = new Location(0, 0);
		
		addComponent(transform = new Transform(this));
	}
	
	public void addComponent(Component component)
	{
		components.add(component);
	}
	
	public Component findComponent(String name)
	{
		for(Component component : components)
		{
			if(component.getName().equalsIgnoreCase(name.toLowerCase()))
			{
				return component;
			}
		}
		return null;
	}
	
	public void tick()
	{
		for(int i = 0; i < components.size(); i++)
		{
			if(components.get(i).isActive())
			{
				components.get(i).tick();
				
			}
		}
	}
	
	public void draw(Graphics graphics)
	{
		for(int i = 0; i < components.size(); i++)
		{
			if(components.get(i).isActive())
			{
				components.get(i).draw(graphics);
			}
		}
	}
	
	public Transform getTransform()
	{
		return transform;
	}
	
	public Location getLocation()
	{
		return location;
	}
	
	public String id()
	{
		return id;
	}
}
