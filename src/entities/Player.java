package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;
import renderEngine.DisplayManager;

public class Player extends Entity{

	private static final float RUN_SPEED = 20;
	private static final float TURN_SPEED = 20;
	private static final float ELEVATE_SPEED = 20;
	
	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;
	private float currentElevateSpeed = 0;
	
	public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
		// TODO Auto-generated constructor stub
	}
	
	public void move() {
		checkInputs();
		super.increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		super.increaseRotation(0, 0, currentElevateSpeed * DisplayManager.getFrameTimeSeconds());
		float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
		
		float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
		float dy = (float) (distance * Math.sin(Math.toRadians(super.getRotZ())));
		float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
		
		super.increasePosition(dx, dy, dz);
		
	}
	
	private void checkInputs() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			this.currentSpeed = RUN_SPEED;
		} else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.currentSpeed = -RUN_SPEED;
		} else {
			this.currentSpeed = 0;
		}	
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.currentTurnSpeed = -TURN_SPEED;
		} else if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.currentTurnSpeed = TURN_SPEED;
		} 
		else if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			this.currentElevateSpeed = ELEVATE_SPEED;
		} else if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			this.currentElevateSpeed = -ELEVATE_SPEED;
		}
		else {
			this.currentTurnSpeed = 0;
			this.currentElevateSpeed = 0;
		}
		
	}

}
