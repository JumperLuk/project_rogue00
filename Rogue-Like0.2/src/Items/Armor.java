package Items;

public class Armor extends Item{
	
	public String Type;
	
	public Armor(String pName, int pWert,String pType){
		super(pName,pWert);
		Type = pType;
	}

}
