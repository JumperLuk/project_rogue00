package eu.devy.game.player;

import java.awt.Color;
import java.awt.Graphics;

import eu.devy.engine.defaults.Display;
import eu.devy.engine.utils.Animation;
import eu.devy.engine.utils.Resources;
import eu.devy.game.item.Inventory;
import eu.devy.game.monster.ControlsComponent;
import eu.devy.game.monster.Entity;
import eu.devy.game.monster.LifeComponent;
import eu.devy.game.world.Block;
import eu.devy.ui.Rect;

public class Player extends Entity
{
	private static Player instance;
	
	private Inventory inventory = new Inventory();
	
	private Animation animation;
	private ControlsComponent controlscomponent;
	private Direction lastDirection = Direction.DOWN;
	
	public Player()
	{
		super();
		
		set(this);
		
		addComponent(new LifeComponent(this));
		addComponent(new ControlsComponent(this));

		((LifeComponent)findComponent("LifeComponent")).setGlobalViewVisible(true);
		((LifeComponent)findComponent("LifeComponent")).setLocalViewVisible(false);
		
		getTransform().setWidth(Block.DEFAULT_SQR_SIZE);
		getTransform().setHeight(Block.DEFAULT_SQR_SIZE);
		getTransform().setX((Display.get().getWidth() - getTransform().getWidth()) / 2);
		getTransform().setY((Display.get().getHeight() - getTransform().getHeight()) / 2);
	
		this.animation = new Animation(Resources.CHARCTER_GR_IDL_FRONT, new Rect((int)getTransform().getX() + (int)(getTransform().getWidth() * 0.125f), (int)getTransform().getY(), (int)(getTransform().getWidth() * 0.75f), (int)getTransform().getHeight()), 400);
		this.controlscomponent = (ControlsComponent)findComponent("ControlsComponent");
	}
	
	public void tick()
	{
		super.tick();
		
		animation.tick();
		
		if(!lastDirection.equals(controlscomponent.getFacingDirection()))
		{
			if(controlscomponent.getFacingDirection().equals(Direction.UP))
			{
				animation.setImages(Resources.CHARCTER_GR_IDL_BACK);
				animation.setIndex(0);
			}
			
			if(controlscomponent.getFacingDirection().equals(Direction.DOWN))
			{
				animation.setImages(Resources.CHARCTER_GR_IDL_FRONT);
				animation.setIndex(0);
			}
			
			if(controlscomponent.getFacingDirection().equals(Direction.LEFT))
			{
				animation.setImages(Resources.CHARCTER_GR_IDL_LEFT);
				animation.setIndex(0);
			}
			
			if(controlscomponent.getFacingDirection().equals(Direction.RIGHT))
			{
				animation.setImages(Resources.CHARCTER_GR_IDL_RIGHT);
				animation.setIndex(0);
			}
			lastDirection = controlscomponent.getFacingDirection();
		}
		
		inventory.tick();
	}
	
	public void draw(Graphics graphics)
	{
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Display.get().getWidth(), 128);
		graphics.fillRect(0, Display.get().getHeight() - 100, Display.get().getWidth(), 128);
		
		super.draw(graphics);
		
		animation.draw(graphics);
		
		inventory.draw(graphics);
	}
	
	public Inventory getInventory()
	{
		return inventory;
	}
	
	public static Player get()
	{
		return Player.instance;
	}
	
	public static void set(Player player)
	{
		Player.instance = player;
	}
}
