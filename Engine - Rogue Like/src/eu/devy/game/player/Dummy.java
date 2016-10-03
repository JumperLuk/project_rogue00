package eu.devy.game.player;

import java.awt.Color;
import java.awt.Graphics;

import eu.devy.game.monster.Entity;
import eu.devy.game.monster.EntityManager;

public class Dummy extends Entity
{
	public Dummy()
	{
		super();
		
		EntityManager.registerNewDummy(this);
	}
	
	public void tick()
	{
		super.tick();
	}
	
	public void draw(Graphics graphics)
	{
		super.draw(graphics);

		graphics.setColor(Color.RED);
		graphics.fillRect((int)getTransform().getX(), (int)getTransform().getY(), (int)getTransform().getWidth(), (int)getTransform().getHeight());
	}
}
