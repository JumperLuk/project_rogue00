package eu.devy.game.world;

public class Map {
	
//	public int Width;
//	public int Heigth;
//	
//	public Structure[][] Structures;
//	public Structure Exit;
//	public Structure Spawn;
//	
//	public Map(String seed){
//		generate(seed);
//	}
//	
//	//Seed Size = 13 (Beispiel: 1234567890123);
//	private void generate(String Seed){
//		//Size generation
//		Width = 9+15*((5+((Integer.valueOf(Seed.charAt(0))+1)/2))/10)+Integer.valueOf(Seed.charAt(0));
//		Heigth = 9+15*((5+((Integer.valueOf(Seed.charAt(1))+1)/2))/10)+Integer.valueOf(Seed.charAt(1));
//		Structures = new Structure[Width][Heigth];
//		//Wall Generation
//		for(int i = 0;i<Width;i++)Structures[i][0]=new Structure(new Location(i,0,false), "Wall");
//		for(int i = 1;i<Heigth;i++)Structures[0][i]=new Structure(new Location(0,i,false), "Wall");
//		for(int i = 1;i<Heigth;i++)Structures[Width-1][i]=new Structure(new Location(Width-1,i,false), "Wall");
//		for(int i = 1;i<Width-1;i++)Structures[i][Heigth-1]=new Structure(new Location(i,Heigth-1,false), "Wall");
//		//Exit geneartion
//		Location lExit = new Location((Width-2)*((Integer.valueOf(Seed.substring(4,5))+1)/100),(Heigth-2)*((Integer.valueOf(Seed.substring(2,3))+1)/100),false);
//		Exit = new Structure(lExit, "Exit");
//		Structures[lExit.getSquareX()][lExit.getSquareY()]=(Exit);
//		//Spawngeneration
//		int SpawnX = lExit.getSquareX()+(Integer.valueOf(3)+1)*2/3;
//		if(SpawnX>Width-2)SpawnX=(Integer.valueOf(3)+1)*2/3+1;
//		int SpawnY = lExit.getSquareY()+(Integer.valueOf(3)+1)*2/3;
//		if(SpawnY>Heigth-2)SpawnY=(Integer.valueOf(3)+1)*2/3+1;
//		Location lSpawn = new Location(SpawnX,SpawnY,false);
//		Spawn = new Structure(lSpawn,"Spawn");
//		Structures[lSpawn.getSquareX()][lSpawn.getSquareY()]=(Exit);
//		//Structure generation
//		int AnzahlStructures = (Width*Heigth)*(((30+Integer.valueOf(Seed.substring(6,7))*2/5)+8)/100);
//		for(;AnzahlStructures>0;){
//			for(int i = 0;i<Seed.length();i++){
//				if(AnzahlStructures<=0)break;
//				int a= i;
//				if(a+5>Seed.length())a=a-Seed.length()+5;
//				Location Location = new Location((Width-2)*((Integer.valueOf(Seed.substring(a,a+1))+1)/100),(Heigth-2)*((Integer.valueOf(Seed.substring(a+2,a+3))+1)/100),false);
//				int Groupsize = Integer.valueOf(Seed.charAt(a+4)+1);
//				if(Groupsize>AnzahlStructures)AnzahlStructures=0;
//				int GroupType = Integer.valueOf(Seed.charAt(a+5));
//				AnzahlStructures-=Groupsize;
//				AnzahlStructures-=generateGroupStructure(Location,Groupsize,GroupType);
//			}
//			if(AnzahlStructures>0){
//				for(int a = 1;a<=2;a++)for(int i = 0;i<Seed.length()/2;i+=a){
//					char c = Seed.charAt(i);
//					Seed.toCharArray()[i]=Seed.charAt(Seed.length()-i-1);
//					Seed.toCharArray()[Seed.length()-i-1]=c;
//				}
//			}
//		}
//		Pathmaker();
//	}
//
//	/*private void Pathmaker(){
//		HashMap<Location,Integer> openWays = new HashMap<>();
//		Location Location = Spawn.Location;
//		Location EndLocation = Exit.Location;
//		openWays.put(Location,);
//		int Direction;
//		while(!Location.equals(EndLocation)&&openWays.size()>0){
//			Direction = Location.getPrimeDirection(EndLocation);
//			boolean moved = false;
//			if(canWalkDirection(Location, Direction)){
//				for(int i = 0;i<=270+Direction;i+=90){
//					int a= i;
//					if(a>360)a-=360;
//					if(canWalkDirection(Location, a)){
//						openWays.add(Location);
//						break;
//					}
//				}
//				Location.setDirection(Direction);	
//				moved = true;
//			}else{
//				for(int i= 0;i<=270+Direction;i+=90){
//					int a= i;
//					if(a>360)a-=360;
//					if(canWalkDirection(Location, a)){
//						Location.setDirection(Direction);
//						moved = true;
//						break;
//					}
//				}
//			}
//			if(!moved)Location = 
//		}	
//	}*/
//	
//	
//
//	private int generateGroupStructure(Location location, int groupsize, int groupType) {
//		if(groupType == 0){
//			//horizontal Door Wall
//			for(int i = 0;i<6;i++){
//				if(groupsize == 0)break;
//				groupsize--;
//				int X = 1;X++;
//				if(X==4)X=-4;
//				Location nLocation = new Location(location);
//				nLocation.setSquareX(location.getSquareX()-X);
//				if(Structures[nLocation.getSquareX()][nLocation.getSquareY()]==null)groupsize++;
//				else Structures[nLocation.getSquareX()][nLocation.getSquareY()]= new Structure(nLocation,"Wall");				
//			}
//		}else if(groupType == 1){
//			//Block
//			for(int i = 0; i<9;i++){
//				if(groupsize == 0)break;
//				groupsize--;
//				int X = 0;X++;
//				int Y = 0;
//				if(X==3){
//					X=0;
//					Y++;
//				}
//				Location nLocation = new Location(location.getSquareX()+X,location.getSquareY()+Y,false);
//				if(Structures[nLocation.getSquareX()][nLocation.getSquareY()]==null)groupsize++;
//				else Structures[nLocation.getSquareX()][nLocation.getSquareY()]= new Structure(nLocation,"Wall");	
//			}
//		}else if(groupType == 2){
//			//Random
//		}else if(groupType == 3){
//			//Slash
//			for(int i = 0; i<3;i++){
//				if(groupsize == 0)break;
//				groupsize--;
//				Location nLocation = new Location(location.getSquareX()+i,location.getSquareY()+i,false);
//				if(Structures[nLocation.getSquareX()][nLocation.getSquareY()]==null)groupsize++;
//				else Structures[nLocation.getSquareX()][nLocation.getSquareY()]= new Structure(nLocation,"Wall");	
//			}
//		}else if(groupType == 4){
//			//Corner
//			for(int i = 0; i<4;i++){
//				if(groupsize == 0)break;
//				groupsize--;
//				int X = 0;X++;
//				int Y = 0;
//				if(X==2){
//					X=0;
//					Y++;
//				}
//				Location nLocation = new Location(location.getSquareX()+X,location.getSquareY()+Y,false);
//				if(Structures[nLocation.getSquareX()][nLocation.getSquareY()]==null)groupsize++;
//				else Structures[nLocation.getSquareX()][nLocation.getSquareY()]= new Structure(nLocation,"Wall");	
//			}
//		}else if(groupType == 5){
//			//V Gang
//			for(int i = 0; i<9;i++){
//				if(groupsize == 0)break;
//				groupsize--;
//				int X = 0;
//				int Y = 0;
//				if(i==3){
//					X=-1;
//				    Y=-4;
//				}else if(i==5)Y-=2;
//				else if (i == 6)Y+=2;
//				else if(i>=6){
//					X=-2;
//					Y-=2;
//				}
//				Location nLocation = new Location(location.getSquareX()+X+i,location.getSquareY()+Y+i,false);
//				if(Structures[nLocation.getSquareX()][nLocation.getSquareY()]==null)groupsize++;
//				else Structures[nLocation.getSquareX()][nLocation.getSquareY()]= new Structure(nLocation,"Wall");	
//			}
//		}else if(groupType == 6){
//			//LeftTurn
//			for(int i = 0; i<6;i++){
//				if(groupsize == 0)break;
//				groupsize--;
//				int X = 0;
//				int Y = -1;Y--;
//				if(i==3)X=-1;
//				else if(i >= 3){
//					Y= 0;
//					X--;
//				}
//				Location nLocation = new Location(location.getSquareX()+X,location.getSquareY()+Y,false);
//				if(Structures[nLocation.getSquareX()][nLocation.getSquareY()]==null)groupsize++;
//				else Structures[nLocation.getSquareX()][nLocation.getSquareY()]= new Structure(nLocation,"Wall");	
//			}
//		}else if(groupType == 7){
//			//RigthTurn
//			for(int i = 0; i<6;i++){
//				if(groupsize == 0)break;
//				groupsize--;
//				int X = 0;
//				int Y = -1;Y--;
//				if(i==3)X=1;
//				else if(i >= 3){
//					Y= 0;
//					X++;
//				}
//				Location nLocation = new Location(location.getSquareX()+X,location.getSquareY()+Y,false);
//				if(Structures[nLocation.getSquareX()][nLocation.getSquareY()]==null)groupsize++;
//				else Structures[nLocation.getSquareX()][nLocation.getSquareY()]= new Structure(nLocation,"Wall");	
//			}
//		}else if(groupType == 8){
//			//vertikal door wall
//			for(int i = 0;i<6;i++){
//				if(groupsize == 0)break;
//				groupsize--;
//				int Y = 1;Y++;
//				if(Y==4)Y=-4;
//				Location nLocation = new Location(location);
//				nLocation.setSquareX(location.getSquareY()-Y);
//				if(Structures[nLocation.getSquareX()][nLocation.getSquareY()]==null)groupsize++;
//				else Structures[nLocation.getSquareX()][nLocation.getSquareY()]= new Structure(nLocation,"Wall");			
//			}
//		}else if(groupType == 9){
//			//Up Down Up
//			for(int i = 0;i<6;i++){
//				if(groupsize == 0)break;
//				groupsize--;
//				int X = -2;
//				int Y = 0;Y--;
//				if((double)((double)(Y/2)-(int)(Y/2))==0){
//					X+=2;
//					Y++;
//				}
//				Location nLocation = new Location(location);
//				nLocation.setSquareX(location.getSquareX()-X);
//				if(Structures[nLocation.getSquareX()][nLocation.getSquareY()]==null)groupsize++;
//				else Structures[nLocation.getSquareX()][nLocation.getSquareY()]= new Structure(nLocation,"Wall");				
//			}
//		}
//		return groupsize;
//	}
//
//	public boolean canWalk(Location location) {
//		if(Structures[location.getSquareX()][location.getSquareY()]==null)return true;
//		else if(!Structures[location.getSquareX()][location.getSquareY()].Type.equals("Wall"))return true;
//		return false;
//	}
//	
//	public boolean canWalkUP(Location location) {
//		if(Structures[location.getSquareX()][location.getSquareY()-1]==null)return true;
//		else if(!Structures[location.getSquareX()][location.getSquareY()-1].Type.equals("Wall"))return true;
//		return false;
//	}
//	
//	public boolean canWalkDown(Location location) {
//		if(Structures[location.getSquareX()][location.getSquareY()+1]==null)return true;
//		else if(!Structures[location.getSquareX()][location.getSquareY()+1].Type.equals("Wall"))return true;
//		return false;
//	}
//	
//	public boolean canWalkLeft(Location location) {
//		if(Structures[location.getSquareX()-1][location.getSquareY()]==null)return true;
//		else if(!Structures[location.getSquareX()-1][location.getSquareY()].Type.equals("Wall"))return true;
//		return false;
//	}
//	
//	public boolean canWalkRigth(Location location) {
//		if(Structures[location.getSquareX()+1][location.getSquareY()]==null)return true;
//		else if(!Structures[location.getSquareX()+1][location.getSquareY()].Type.equals("Wall"))return true;
//		return false;
//	}
//
//	public boolean canWalkDirection(Location Location,int Direction){
//		if(Direction == 0||Direction == 270)return canWalkUP(Location);
//		if(Direction == 90)return canWalkRigth(Location);
//		if(Direction == 180)return canWalkDown(Location);
//		if(Direction == 270)return canWalkLeft(Location);
//		return false;
//	}
}
