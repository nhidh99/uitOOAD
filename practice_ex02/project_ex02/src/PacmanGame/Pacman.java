package PacmanGame;

import java.util.List;

public class Pacman extends DynamicObject {

	public static Pacman instance;

	private Pacman() {
	}
	
	public static Pacman getInstance() {
		if (instance == null) {
			return new Pacman();
		}
		return instance;
	}

	@Override
	void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

	void handleCollide(List<GameObject> colliableObjects) {

	}

	void handleKeyboard(int keyCode, boolean press) {

	}

	@Override
	void draw() {
		// TODO Auto-generated method stub

	}
}
