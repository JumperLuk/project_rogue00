package eu.devy.game.monster;

import java.awt.Graphics;

import eu.devy.engine.defaults.Keyboard;
import eu.devy.engine.defaults.Keyboard.KeyCode;
import eu.devy.game.item.ItemCollection;
import eu.devy.game.player.Direction;
import eu.devy.game.player.Player;
import eu.devy.game.world.Block;
import eu.devy.game.world.DungeonGenerator;
import eu.devy.game.world.Location;
import eu.devy.game.world.Type;
import eu.devy.network.Network;
import eu.devy.network.Client.DataPackage;
import eu.devy.network.Client.DataPackageAction;

public class ControlsComponent extends Component
{
	private double lastTime = System.currentTimeMillis();
	private double time = 0.0d;
	
	private boolean isWalking = false;
	private Direction lastDirection = Direction.UP;
	
	private float offset_x;
	private float offset_y;
	
	private LifeComponent lifeComponent;
	
	public ControlsComponent(Entity instance)
	{
		super("ControlsComponent", instance);
	
		this.lifeComponent = (LifeComponent)getInstance().findComponent("LifeComponent");
	}

	@Override
	public void tick()
	{
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(Keyboard.getKey(KeyCode.ARROW_UP) || Keyboard.getKey(KeyCode.W))
		{
			move(Direction.UP);
		}
		
		if(Keyboard.getKey(KeyCode.ARROW_DOWN) || Keyboard.getKey(KeyCode.S))
		{
			move(Direction.DOWN);
		}
		
		if(Keyboard.getKey(KeyCode.ARROW_LEFT) || Keyboard.getKey(KeyCode.A))
		{
			move(Direction.LEFT);
		}
		
		if(Keyboard.getKey(KeyCode.ARROW_RIGHT) || Keyboard.getKey(KeyCode.R))
		{
			move(Direction.RIGHT);
		}
		
		if(isWalking)
		{
			if(time > 25)
			{
				if(offset_x == Block.DEFAULT_SQR_SIZE && offset_y == Block.DEFAULT_SQR_SIZE)
				{
					DungeonGenerator.get().setRelativeOffset(getInstance().getLocation().getX(), getInstance().getLocation().getY());
					isWalking = false;
					offset_x = 0;
					offset_y = 0;
				}
				else
				{
					if(offset_x < Block.DEFAULT_SQR_SIZE)
					{
						offset_x += lifeComponent.getWalkSpeed();
					}
					else
					{
						offset_x = Block.DEFAULT_SQR_SIZE;
					}
					
					if(offset_y < Block.DEFAULT_SQR_SIZE)
					{
						offset_y += lifeComponent.getWalkSpeed();
					}
					else
					{
						offset_y = Block.DEFAULT_SQR_SIZE;
					}
				}
				time = 0.0d;
			}
		}
		DungeonGenerator.get().setAbsoluteOffset(offset_x * lastDirection.getX(), offset_y * lastDirection.getY());
		
		if(Keyboard.getKeyDown(KeyCode.SPACE))
		{
			Player p = Player.get();
			
			Location location = new Location(p.getLocation().getX() + lastDirection.getX(), p.getLocation().getY() + lastDirection.getY());
			
			Entity Target = EntityManager.find(location);
			
			if(Target != null)
			{
				Network.getClient().send(p.id() + DataPackage.SEPERATOR + DataPackageAction.ATTACK.toString().toLowerCase() + DataPackage.SEPERATOR + Target.id());
			}
			
			System.out.println("attack!");
		}
	}

	@Override
	public void draw(Graphics graphics)
	{
		
	}
	
	private void move(Direction direction)
	{
		if(!isWalking)
		{
			lastDirection = direction;
			
			Location location = new Location(getInstance().getLocation().getX() + direction.getX(), getInstance().getLocation().getY() + direction.getY());
			Block block = DungeonGenerator.get().getBlockAt(location.getX(), location.getY());
			
			if(block != null)
			{
				if(block.getType().equals(Type.GOAL))
				{
					DungeonGenerator.next();
				}
				
				if(block.getType().equals(Type.ITEM_DROP))
				{
					DungeonGenerator.get().setBlockAt(new Block(block.getLocation(), Type.GROUND), block.getLocation().getX(), block.getLocation().getY());
					Player.get().getInventory().addItem(ItemCollection.getRandomItem());
				}
				
				if(!block.getType().isSolid())
				{
					getInstance().getLocation().setLocation(location);
		
					Network.getClient().send(getInstance().id() + DataPackage.SEPERATOR + DataPackageAction.POSITION_PLAYER.toString().toLowerCase() + DataPackage.SEPERATOR + location.getX() + DataPackage.SEPERATOR + location.getY());
					
					isWalking = true;
					
					time = 0.0d;
				}
			}
		}
	}
	
	public boolean isWalking()
	{
		return isWalking;
	}
	
	public Direction getFacingDirection()
	{
		return lastDirection;
	}
}
