package Items;

public class Weapon extends Item{
	
	public String Type;
	
	public Weapon(String pName, int pWert,String pType){
		super(pName,pWert);
		Type = pType;
	}

}
