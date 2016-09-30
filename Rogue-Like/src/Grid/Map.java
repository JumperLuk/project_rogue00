package Grid;

import java.util.ArrayList;

public class Map {
	
	public int Width;
	public int Heigth;
	
	public ArrayList<Structure> Structures = new ArrayList<>();
	
	public Map(int pWidth, int pHeigth,int Seed){
		Width=pWidth;
		Heigth=pHeigth;
		generate(Seed);
	}
	
	private void generate(int Seed){
		
	}

	public boolean canWalk(Location location) {
		if(Structures.size()>0)for(Structure Structure:Structures){
			if(Structure.Location.equals(location))return false;
		}
		return false;
	}

}
