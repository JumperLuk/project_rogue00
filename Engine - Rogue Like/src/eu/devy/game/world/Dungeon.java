package eu.devy.game.world;

import java.awt.Color;
import java.awt.Graphics;

import eu.devy.engine.defaults.Display;
import eu.devy.game.monster.EntityManager;
import eu.devy.game.player.Player;

public class Dungeon 
{
	private static final Color BACKGROUND_COLOR = new Color(20, 20, 20);
	
	private Block[][] data;
	private Location location = new Location(0, 0);
	
	private float offset_x;
	private float offset_y;
	
	public Location exit;
	public Location spawn;
	
	private Location spawn_location = new Location(5, 5);
	
	public Dungeon()
	{
		
	}
	
	public Dungeon(String keygen)
	{
		generate(keygen);
	}
	
	private void generate(String keygen)
	{
		
		double up = 5 + ((Integer.parseInt("" + (keygen.charAt(0))) + 1) / 2);
		
		double factor = 15 * (up / 10);
		
		double width = 7 + factor + Integer.parseInt("" + (keygen.charAt(0)));		
		
		up = 5 + ((Integer.parseInt("" + (keygen.charAt(1))) + 1) / 2);
		
		factor = 15 * (up / 10);		
		
		double height = 7 + factor + Integer.parseInt("" + (keygen.charAt(1)));		
		
		data = new Block[(int)width][(int)height];

		for(int x = 0; x < (int)width; x++) 
		{
			for(int y = 0; y < (int)height; y++)
			{
				data[x][y] = new Block(new Location(x, y), Type.GROUND);
				
				if(x == 0 || y == 0 || x == (int)width - 1 || y == (int)height - 1)
				{
					data[x][y] = new Block(new Location(x, y), Type.WALL);
				}
			}
		}
		
		up = (Integer.parseInt(keygen.substring(4,6)) + 1);
		
		factor = up / 100;
		
		double lExitX = (width - 2) * factor;
		
		up = (Integer.parseInt(keygen.substring(2, 4)) + 1);
		
		factor = up / 100;
		
		double lExitY = (height - 2) * factor;
		
		exit = new Location((int)lExitX,(int)lExitY);
		
		data[exit.getX()][exit.getY()] = (new Block(exit, Type.GOAL));
		

		int spawn_x = exit.getX() + exit.getX() * ((Integer.parseInt("" + (keygen.charAt(3))) + 1) * 2) / 3;
		
		if(spawn_x > width - 2)
		{
			spawn_x = ((int)width) - ((Integer.parseInt("" + (keygen.charAt(3))) + 1) * 2) / 3 + 1;
		}
		
		int spawn_y = exit.getY() + exit.getY() * ((Integer.parseInt("" + (keygen.charAt(3))) + 1) * 2) / 3;
		
		if(spawn_y > height - 2)
		{
			spawn_y = ((int)height) - ((Integer.parseInt("" + (keygen.charAt(3))) + 1) * 2) / 3 + 1;
		}
		
		spawn = new Location(spawn_x, spawn_y);
		
		data[spawn.getX()][spawn.getY()] = new Block(spawn, Type.SPAWN);
		
		up = (30 + Integer.parseInt(keygen.substring(6, 8))) * 2;
		
		factor = (up / 5) + 8;
		
		int amount_structures = (int)((double)width * height * (factor / 100));
		
		for(;(int)amount_structures > 0;)
		{
			for(int i = 0; i < keygen.length();i++)
			{
				if(amount_structures <= 0)
				{
					break;
				}
				
				int a= i;
				
				if(a + 5 > keygen.length() - 1)
				{
					a = a - keygen.length() + 5;
				}
				
				double tmp_x_01 = Integer.parseInt(keygen.substring(a, a + 2)) + 1;
				double tmp_x_02 = tmp_x_01 / 100;
				double x = (width - 2) * tmp_x_02;
				
				double tmp_y_01 = Integer.parseInt(keygen.substring(a + 2,a + 4)) + 1;
				double tmp_y_02 = tmp_y_01 / 100;
				double y = (height - 2) * tmp_y_02;
				
				if(x > width)
				{
					x = width - 1;
				}
				
				if(y > height)
				{
					y = height - 1;
				}
				
				Location location = new Location((int)x,(int)y);

				int groupsize = (Integer.parseInt("" + keygen.charAt(a + 4)) + 1);
				
				if(groupsize > amount_structures)
				{
					groupsize = (int) amount_structures;
				}
				
				int grouptype = Integer.parseInt("" + keygen.charAt(a + 5));
				
				amount_structures -= generateGroupStructure(location, groupsize, grouptype);
			}
			
			if(amount_structures > 0)
			{
				for(int a = 1;a <= 2; a++)
				{
					for(int i = 0; i < keygen.length() / 2; i += a)
					{
						char c = keygen.charAt(i);
						
						keygen.toCharArray()[i] = keygen.charAt(keygen.length() - i - 1);
						keygen.toCharArray()[keygen.length() - i - 1] = c;
					}
				}
			}
		}

		data[1][1] = new Block(new Location(1, 1), Type.ITEM_DROP);
		spawn_location = spawn;
	}
	
	private int generateGroupStructure(Location location, int groupsize, int grouptype)
	{
		if(grouptype == 0)
		{
			int x = 2; 
			
			for(int i = 0; i < 6; i++)
			{
				if(groupsize == 0)
				{
					break;
				}
				
				groupsize--;
				
				x++;
				
				if(x == 4)
				{
					x = -4;
				}
				
				Location new_location = new Location(location);
				
				new_location.setX(location.getX() - x);
				
				if((new_location.getX() > getWidth() - 1 || new_location.getY() > getHeight() - 1|| new_location.getX() < 0 || new_location.getY() < 0) || !data[new_location.getX()][new_location.getY()].getType().equals(Type.GROUND))
				{
					groupsize++;
				}
				else
				{
					data[new_location.getX()][new_location.getY()] = new Block(new_location, Type.STONE);				
				}
			}
		}
		else 
		if(grouptype == 1)
		{
			int x = 1; 
			int y = 0;
			
			for(int i = 0; i < 9; i++)
			{
				if(groupsize == 0)
				{
					break;
				}
				
				groupsize--;
				
				x++;
				
				if(x == 3)
				{
					x = 0;
					y++;
				}
				
				Location new_location = new Location(location.getX() + x, location.getY() + y);
	
				if((new_location.getX() > getWidth() - 1 || new_location.getY() > getHeight() - 1 || new_location.getX() < 0 || new_location.getY() < 0) || !data[new_location.getX()][new_location.getY()].getType().equals(Type.GROUND))
				{
					groupsize++;
				}
				else
				{
					data[new_location.getX()][new_location.getY()] = new Block(new_location, Type.STONE);	
				}
				
			}
		}
		else 
		if(grouptype == 2)
		{
			
		}
		else 
		if(grouptype == 3)
		{
			for(int i = 0; i < 3; i++)
			{
				if(groupsize == 0)
				{
					break;
				}
				
				groupsize--;
				
				Location new_location = new Location(location.getX() + i, location.getY() + i);
				
				if((new_location.getX() > getWidth() - 1 || new_location.getY() > getHeight() - 1 || new_location.getX() < 0 || new_location.getY() < 0) || !data[new_location.getX()][new_location.getY()].getType().equals(Type.GROUND))
				{
					groupsize++;
				}
				else
				{
					data[new_location.getX()][new_location.getY()] = new Block(new_location, Type.STONE);	
				}
			}
		}
		else
		if(grouptype == 4)
		{			
			int x = 0;
			int y = 0;
			
			for(int i = 0; i < 4; i++)
			{
				if(groupsize == 0)
				{
					break;
				}
				
				groupsize--;
				
				x++;
				
				if(x == 2)
				{
					x = 0;
					y++;
				}
				
				Location new_location = new Location(location.getX() + x, location.getY() + y);
				
				if((new_location.getX() > getWidth() - 1 || new_location.getY() > getHeight() - 1 || new_location.getX() < 0|| new_location.getY() < 0) || !data[new_location.getX()][new_location.getY()].getType().equals(Type.GROUND))
				{
					groupsize++;
				}
				else
				{
					data[new_location.getX()][new_location.getY()] = new Block(new_location, Type.STONE);	
				}
			}
		}
		else
		if(grouptype == 5)
		{
			int x = 0;
			int y = 0;
			
			for(int i = 0; i < 9; i++)
			{
				if(groupsize == 0)
				{
					break;
				}
				
				groupsize--;
				
				
				if(i == 3)
				{
					x = -1;
				    y = -4;
				}
				else
				if(i == 5)
				{
					y -= 2;
				}
				else 
				if(i == 6)
				{
					y += 2;
				}
				else
				if(i >= 6)
				{
					x =- 2;
					y -= 2;
				}
				
				Location new_location = new Location(location.getX() + x +i, location.getY() + y + i);				
				
				if((new_location.getX() > getWidth() - 1 || new_location.getY() > getHeight() - 1 || new_location.getX() < 0 || new_location.getY() < 0) || !data[new_location.getX()][new_location.getY()].getType().equals(Type.GROUND))
				{
					groupsize++;
				}
				else
				{
					data[new_location.getX()][new_location.getY()] = new Block(new_location, Type.STONE);	
				}
			}
		}
		else 
		if(grouptype == 6)
		{
			int x = 0;
			int y = -1;
			
			for(int i = 0; i < 6; i++)
			{
				if(groupsize == 0)
				{
					break;
				}
				
				groupsize--;
				
				y--;
				
				if(i == 3)
				{
					x = -1;
				}
				else
				if(i >= 3)
				{
					y = 0;
					x--;
				}
				
				Location new_location = new Location(location.getX() + x, location.getY() + y);
				
				if((new_location.getX() > getWidth() - 1 || new_location.getY() > getHeight() - 1 || new_location.getX() < 0 || new_location.getY() < 0) || !data[new_location.getX()][new_location.getY()].getType().equals(Type.GROUND))
				{
					groupsize++;
				}
				else
				{
					data[new_location.getX()][new_location.getY()] = new Block(new_location, Type.STONE);	
				}
			}
		}
		else
		if(grouptype == 7)
		{
			int x = 0;
			int y = -1;
			
			for(int i = 0; i < 6; i++)
			{
				if(groupsize == 0)
				{
					break;
				}
				
				groupsize--;
				
				y--;
				
				if(i == 3)
				{
					x = 1;
				}
				else 
				if(i >= 3)
				{
					y= 0;
					x++;
				}
				
				Location new_location = new Location(location.getX() + x, location.getY() + y);
				
				if((new_location.getX() > getWidth() - 1 || new_location.getY() > getHeight() - 1 || new_location.getX() < 0 || new_location.getY() < 0) || !data[new_location.getX()][new_location.getY()].getType().equals(Type.GROUND))
				{
					groupsize++;
				}
				else
				{
					data[new_location.getX()][new_location.getY()] = new Block(new_location, Type.STONE);	
				}
			}
		}
		else 
		if(grouptype == 8)
		{
			int y = 2;
			for(int i = 0; i < 6; i++)
			{
				if(groupsize == 0)
				{
					break;
				}
				
				groupsize--;
				
				y++;
				
				if(y == 4)
				{
					y = -4;
				}
				
				Location new_location = new Location(location.getY() - y, location.getY());
				
				if((new_location.getX() > getWidth() - 1 || new_location.getY() > getHeight() - 1 || new_location.getX() < 0 || new_location.getY() < 0) || !data[new_location.getX()][new_location.getY()].getType().equals(Type.GROUND))
				{
					groupsize++;
				}
				else
				{
					data[new_location.getX()][new_location.getY()] = new Block(new_location, Type.STONE);			
				}
			}
		}
		else
		if(grouptype == 9)
		{
			
			int x = -2;
			int y = 0;
			
			for(int i = 0; i < 6; i++)
			{
				if(groupsize == 0)
				{
					break;
				}
				
				groupsize--;
				
				y--;
				
				if((double)((double)(y / 2) - (int)(y / 2)) == 0)
				{
					x += 2;
					y++;
				}
				
				Location new_location = new Location(location.getX() - x, location.getY());

				if((new_location.getX() > getWidth() - 1 || new_location.getY() > getHeight() - 1 || new_location.getX() < 0 || new_location.getY() < 0) || !data[new_location.getX()][new_location.getY()].getType().equals(Type.GROUND))
				{
					groupsize++;
				}
				else
				{
					data[new_location.getX()][new_location.getY()] = new Block(new_location, Type.STONE);				
				}
			}
		}
		return groupsize;
	}

	
	public void tick()
	{
		EntityManager.tickDummy();
	}
	
	public void draw(Graphics graphics)
	{
		graphics.setColor(BACKGROUND_COLOR);
		graphics.fillRect(0, 0, Display.get().getWidth(), Display.get().getHeight());
		
		for(int x = 0; x < data.length; x++)
		{
			for(int y = 0; y < data[x].length; y++)
			{
				graphics.drawImage(data[x][y].getType().getImage(), (Display.get().getWidth() - Player.get().getTransform().getWidth()) / 2 - x * Block.DEFAULT_SQR_SIZE + location.getX() * Block.DEFAULT_SQR_SIZE + (int)offset_x,  (Display.get().getHeight() - Player.get().getTransform().getHeight()) / 2 - y * Block.DEFAULT_SQR_SIZE + location.getY() * Block.DEFAULT_SQR_SIZE + (int)offset_y, Block.DEFAULT_SQR_SIZE, Block.DEFAULT_SQR_SIZE, null);
			}
		}
		EntityManager.drawDummy(graphics);
	}
	
	public void setBlockAt(Block block, int x, int y)
	{
		if(x > -1 && y > -1)
		{
			if(x < data.length - 1)
			{
				if( y < data[x].length - 1)
				{
					data[x][y] = block;
				}
			}
		}
	}
	
	public Block getBlockAt(int x, int y)
	{
		if(x > -1 && y > -1)
		{
			if(x < data.length - 1)
			{
				if( y < data[x].length - 1)
				{
					return data[x][y];
				}
			}
		}
		return null;
	}
	
	public Location getRelativeOffset()
	{
		return location;
	}
	
	public void setRelativeOffset(int x, int y)
	{
		location.setLocation(x, y);
	}
	
	public void setAbsoluteOffset(float x, float y)
	{
		this.offset_x = x;
		this.offset_y = y;
	}
	
	public Location getSpawnLocation()
	{
		return spawn_location;
	}
	
	public int getWidth()
	{
		if(data != null)
		{
			return data.length;
		}
		return -1;
	}
	
	public int getHeight()
	{
		if(data[0] != null)
		{
			return data[0].length;
		}
		return -1;
	}
}
