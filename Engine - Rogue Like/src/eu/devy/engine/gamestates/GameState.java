package eu.devy.engine.gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import eu.devy.engine.defaults.Display;
import eu.devy.engine.defaults.Keyboard;
import eu.devy.engine.defaults.Keyboard.KeyCode;
import eu.devy.engine.utils.Scene;
import eu.devy.game.item.ItemCollection;
import eu.devy.game.player.Player;
import eu.devy.game.world.DungeonGenerator;
import eu.devy.network.Network;
import eu.devy.network.NetworkType;;

public class GameState extends State
{
	private boolean init = false;
	private boolean init_level = false;
	
	public GameState() 
	{
		super(Id.GAME);
		new Player();
		
		ItemCollection.init();
	}

	@Override
	public void tick() 
	{
		 
		if(!init)
		{
			return;
		}
		
		if(!init_level)
		{
			DungeonGenerator.recive("91347563472567");
			DungeonGenerator.get().setRelativeOffset(DungeonGenerator.get().getSpawnLocation().getX(), DungeonGenerator.get().getSpawnLocation().getY());
			Player.get().getLocation().setLocation(DungeonGenerator.get().getSpawnLocation());

			if(Network.getNetworkType().equals(NetworkType.HOST))
			{
				Network.createNewServer();
				Network.createNewClient(Network.getAddress());
			}
			
			if(Network.getNetworkType().equals(NetworkType.GUEST))
			{
				Network.createNewClient(Network.getAddress());
			}
			
			init_level = true;
		}
		
		if(DungeonGenerator.exists())
		{
			DungeonGenerator.get().tick();
			Player.get().tick();
		}
	}
	@Override
	public void draw(Graphics graphics) 
	{	
		if(DungeonGenerator.exists())
		{
			DungeonGenerator.get().draw(graphics);	
			Player.get().draw(graphics);
		}
		
		if(!init)
		{
			init = !Scene.fade((Graphics2D)graphics, false);
		}
	}
}