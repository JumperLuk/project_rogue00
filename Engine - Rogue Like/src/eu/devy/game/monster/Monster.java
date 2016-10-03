package eu.devy.game.monster;

import java.util.ArrayList;

import eu.devy.game.player.Direction;
import eu.devy.game.player.Player;
import eu.devy.game.world.Block;
import eu.devy.game.world.Dungeon;
import eu.devy.game.world.Location;
import eu.devy.game.world.Type;
import eu.devy.network.Network;
import eu.devy.network.Client.Client;
import eu.devy.network.Client.DataPackage;
import eu.devy.network.Server.Server;

public class Monster extends Entity{
	
//	Player focusedPlayer;
//	private boolean isMoving =false;
//	private int PixelXOutOfGrid = 0;
//	private int PixelYOutOfGrid = 0;
//	private int MoveWait= 0;
//	private int AtkWait = 0;
//	private Location LastLocation;
//	private Direction aktDirection;
//	
//	private Monster getMob(){
//		return this;
//	}
//	
//	
//	public Monster(Location pLocation){
//		super(); 
//		focusPlayer();
//	}
//
//	private void focusPlayer() {
//		int Distance = 0;
//		Player fPlayer =null;
//		String[] Players = Network.getServer().getAllConnectedPlayer();
//		
//		for(int i = 0; i < Players.length; i++)
//		{
//			Player p = (Player) EntityManager.find(Players[i].split(";")[0]);
//			
//			if(p != null)
//			{
//				int pDistanceX = (p.getLocation().getX()-this.getLocation().getX());
//				if(pDistanceX<0)pDistanceX*=-1;
//				int pDistanceY = (p.getLocation().getY()-this.getLocation().getY());
//				if(pDistanceY<0)pDistanceY*=-1;
//				if(Distance == 0|| Distance > (pDistanceX+pDistanceY)){
//					Distance = pDistanceX+pDistanceY;		
//					fPlayer = p;
//				}
//			}
//		}
//		focusedPlayer = fPlayer;
//	}
//	
//	@Override
//	public void tick(){
//		if(!isMoving){
//			AtkWait+=50;
//			if(AtkWait == ((LifeComponent)getMob().components.get(0)).getAttackSpeed()){
//				
//				
//				
//				Location p_location = 
//				if(getMob().getLocation().isNextTo(p.getLocation())){
//					Client.sen(getMob().id()+";attack;"+p.id());
//				}
//			}
//			MoveWait+=50;
//			if(MoveWait == ((LifeComponent)getMob().components.get(0)).getWalkSpeed()) isMoving = true;
//			
//		}else{
//			Player p = Server.getPlayer(focusedPlayer.id());
//			if(!getMob().getLocation().isNextTo(p.getLocation())){
//				if(PixelXOutOfGrid==0&&PixelYOutOfGrid==0){
//					Direction Direction = getMob().getLocation().getPrimeDirection(p.getLocation());
//					if(LastLocation==null)LastLocation=getMob().getLocation();
//					if(!Dungeon.getBlockAt(getMob().getLocation().getX()+Direction.getX(),getMob().getLocation().getY()+Direction.getY()).getType==Type.WALL){		
//						for(int i = 0;i<3;i++){
//							Direction.rotateRigth();
//							if(Dungeon.getBlockAt(getMob().getLocation().getX()+Direction.getX(),getMob().getLocation().getY()+Direction.getY()).getType==Type.WALL&&!LastLocation.equals(new Location(getMob().getLocation().getX()+Direction.getX(),getMob().getLocation().getY()+Direction.getY())))break;
//						}
//					}
//					if(Dungeon.getBlockAt(getMob().getLocation().getX()+Direction.getX(),getMob().getLocation().getY()+Direction.getY()).getType==Type.WALL)Direction = getMob().getLocation().getPrimeDirection(LastLocation);
//					aktDirection=Direction;
//				}
//				PixelXOutOfGrid+=aktDirection.getX();
//				PixelYOutOfGrid+=aktDirection.getY();	
//				//Graphic.setLocation(((int)(getMob().getLocation().getX()/Block.DEFAULT_SQR_SIZE))+PixelXOutOfGrid,((int)(getMob().getLocation().getY()/Block.DEFAULT_SQR_SIZE))+PixelYOutOfGrid);
//				if((double)(((double)(PixelXOutOfGrid/Block.DEFAULT_SQR_SIZE))-((int)PixelXOutOfGrid/Block.DEFAULT_SQR_SIZE))==0&&(double)(((double)(PixelYOutOfGrid/Block.DEFAULT_SQR_SIZE))-((int)PixelYOutOfGrid/Block.DEFAULT_SQR_SIZE))==0){
//					isMoving =false;
//					PixelXOutOfGrid=0;
//					PixelYOutOfGrid=0;
//				}
//			}else{
//				isMoving=false;
//			}
//		}
//	}
	
//	public Monster(int pLevel,Location pLocation,Map pMap){
//		super(pLevel,pLocation,pMap); 
//		fokusPlayer();
//	}
//
//	private void fokusPlayer() {
//		int Distance = 0;
//		Player fPlayer =null;
//		for(Player p: EntityManager.Player){
//			int pDistanceX = (p.Location.getSquareX()-this.Location.getSquareX());
//			if(pDistanceX<0)pDistanceX*=-1;
//			int pDistanceY = (p.Location.getSquareY()-this.Location.getSquareY());
//			if(pDistanceY<0)pDistanceY*=-1;
//			if(Distance == 0|| Distance > (pDistanceX+pDistanceY)){
//				Distance = pDistanceX+pDistanceY;		
//				fPlayer = p;
//			}
//		}
//		fokusedPlayer = fPlayer;
//	}
//	
//	public void walk(){
//		int DistanceX = (fokusedPlayer.Location.getSquareX()-this.Location.getSquareX());
//		if(DistanceX<0)DistanceX*=-1;
//		int DistanceY = (fokusedPlayer.Location.getSquareY()-this.Location.getSquareY());
//		if(DistanceY<0)DistanceY*=-1;
//		if(DistanceX>DistanceY){
//			if(fokusedPlayer.Location.getSquareX()-this.Location.getSquareX()>0)super.walk(90);
//			else super.walk(-90);
//		}else if(DistanceX<DistanceY){
//			if(fokusedPlayer.Location.getSquareY()-this.Location.getSquareY()>0)super.walk(180);
//			else super.walk(0);
//		}
//	}
//	
//	public void Figth(){		
//	}
//	
//	public void tick(){
//		super.tick();
//		//nach movementspeed aufrufen
//		walk();
//		if(fokusedPlayer.Location.isNextTo(getMob().Location)){
//			//nach Figthspeed 
//			Figth();
//		}
//	}

}
