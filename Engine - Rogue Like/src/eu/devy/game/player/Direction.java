package eu.devy.game.player;

public enum Direction
{
	UP(0, 1),
	DOWN(0, -1),
	LEFT(1, 0),
	RIGHT(-1, 0);
	
	private int x;
	private int y;
	
	private Direction(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}

	public void rotateRigth() {
		if(x==0&&y==1){
			y=0;
			x=-1;
		}else if(x==-1&&y==0){
			x=0;
			y=-1;
		}else if(x==0&&y==-1){
			x=1;
			y=0;
		}else if(x==1&&y==0){
			x=0;
			y=1;
		}
	}
}
