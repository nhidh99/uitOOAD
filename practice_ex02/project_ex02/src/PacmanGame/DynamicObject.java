package PacmanGame;

import java.util.HashMap;

public abstract class DynamicObject extends GameObject {
	String curImg;
	HashMap<IState, String[]> imgSource;
	float speedX;
	float speedY;
	IState state;

	public void changeState(IState newState) {
	}
}
