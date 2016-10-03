package eu.devy.game.item;

import java.awt.Graphics;

import eu.devy.engine.defaults.Display;
import eu.devy.engine.utils.Resources;

public class Inventory 
{
	private Item[] inventory = new Item[Type.values().length];
	
	public Inventory()
	{
		
	}
	
	public void tick()
	{
		
	}
	
	public void draw(Graphics graphics)
	{
		for(int i = 0; i < inventory.length; i++)
		{
			if(inventory[i] != null)
			{
				graphics.drawImage(inventory[i].getType().getImage(), (Display.get().getWidth() - inventory.length * 64) / 2 + i * 64, Display.get().getHeight() - 80, 64, 64, null);
			}
			else
			{
				graphics.drawImage(Resources.INVENTORY_SLOT, (Display.get().getWidth() - inventory.length * 64) / 2 + i * 64, Display.get().getHeight() - 80, 64, 64, null);
			}
		}
	}
	
	public void addItem(Item item)
	{
		if(inventory[item.getType().id()] == null)
		{
			inventory[item.getType().id()] = item;
		}
	}
	
	public Item getItem(int index)
	{
		if(index > -1 && index < inventory.length - 1)
		{
			return inventory[index];
		}
		return null;
	}
}
