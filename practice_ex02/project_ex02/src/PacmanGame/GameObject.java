package PacmanGame;

public abstract class GameObject {
	int width;
	int height;
	int positionX;
	int positionY;

	abstract void update(float deltaTime);

	abstract void draw();

	Rect getBound() {
		return new Rect(positionX, positionY, width, height);
	}
}