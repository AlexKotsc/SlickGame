package slick.tutorial;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;

import slick.util.Vector;

public class TutorialGame extends BasicGame{

	TutorialGame game;
	static AppGameContainer container;
	int x,y;

	Vector movement;

	int updateTicker;

	Image playerImg, up, down, left, right;

	TextField txtField;

	public static void main(String[] args){
		TutorialGame game = new TutorialGame("Slick Tutorial");

		try {
			container = new AppGameContainer(game);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	public TutorialGame(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {

		g.setAntiAlias(true);

		g.setBackground(Color.gray);

		g.setColor(new Color(0xff00ff)); // Luminous pink!
		g.drawString("Hello World!",200,200);

		g.setColor(Color.blue);
		g.drawRect(180, 170, 150, 80);

		g.setColor(Color.red);
		g.drawLine(250, 190, 300, 230);
		g.drawLine(250, 230, 300, 190);

		g.setColor(new Color(0f,0.6f,0.8f,0.5f)); // transparent bluish, 50% transparent
		g.fillOval(190, 180, 60, 60);

		g.setColor(Color.white);
		txtField.render(container, g);

		g.drawImage(playerImg, x, y);
	}

	@Override
	public void init(GameContainer container) throws SlickException {

		container.setTargetFrameRate(60);
		txtField = new TextField(container, container.getDefaultFont(), 8, 30, 200, 200);
		txtField.setBorderColor(Color.transparent);
		txtField.setTextColor(Color.white);
		txtField.setText("Test");

		playerImg = new Image("res/img/up.fw.png");

		x = y = 100;		

		movement = new Vector();

		updateTicker = 0;


	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {

		String MouseLoc = "";

		Input input = container.getInput();

		if(input.getMouseY()>container.getHeight()/2){
			MouseLoc += "SOUTH";
		} else if(input.getMouseY()<=container.getHeight()/2) {
			MouseLoc += "NORTH";
		}

		if(input.getMouseX()>container.getWidth()/2){
			MouseLoc += "EAST";
		} else if(input.getMouseX()<=container.getWidth()/2) {
			MouseLoc += "WEST";
		}

		txtField.setText("MouseX: " + input.getMouseX() +
				"\nMouseY: " + input.getMouseY() +
				"\nX:     " + x +
				"\nY:   " + y +
				"\nVectorX:     " + movement.getX() +
				"\nVectorY:   " + movement.getY() +
				"\nMouseLoc:" + MouseLoc);

		if(input.isKeyDown(Input.KEY_UP)){
			movement.addY(-2);
		}
		if(input.isKeyDown(Input.KEY_DOWN)){
			movement.addY(2);
		}
		if(input.isKeyDown(Input.KEY_LEFT)){
			movement.addX(-2);
		}
		if(input.isKeyDown(Input.KEY_RIGHT)){			
			movement.addX(2);

		}
		
		playerImg.rotate(-playerImg.getRotation());
		playerImg.rotate((float) (0-Math.toDegrees(Math.atan2(x-input.getMouseX(), y-input.getMouseY()))));
		

		addDrag();
		calculateLocation();



	}

	private void calculateLocation() {
		x += movement.getX()/5;
		y += movement.getY()/5;

		if(x<0){
			x = 0;
			movement.flipX();
		}
		if(x>640){
			x=640;
			movement.flipX();
		}
		if(y<0){
			y=0;
			movement.flipY();
		}
		if(y>480){
			y=480;
			movement.flipY();
		}
	}

	private void addDrag() {
		if(updateTicker < 10){
			updateTicker++;
		} else {
			if(movement.getX()>0) movement.addX(-movement.getX()/2);
			if(movement.getX()<0) movement.addX(movement.getX()/2);
			if(movement.getY()>0) movement.addY(-movement.getY()/2);
			if(movement.getY()<0) movement.addY(movement.getY()/2);
			
			updateTicker = 0;
		}

	}

}
