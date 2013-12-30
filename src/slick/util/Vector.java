package slick.util;

public class Vector {
	
	int dx,dy;
	int speed = 20;
	
	public Vector(){
		int dx = 0;
		int dy = 0;
	}
	
	public int getX(){
		return dx;
	}
	
	public int getY(){
		return dy;
	}
	
	public void flipX(){
		dx = 0-dx;
	}
	
	public void flipY(){
		dy = 0-dy;
	}
	
	public void addX(double x){
		
		
		
		if(Math.pow(dx+x, 2)+Math.pow(dy,2)<Math.pow(speed,2)){
			dx = (int) (dx+x);
		}
	
	}
	
	public void addY(double y){
		
		if(Math.pow(dy+y, 2)+Math.pow(dx,2)<Math.pow(speed,2)){
			dy = (int) (dy+y);
		}
	}

}
