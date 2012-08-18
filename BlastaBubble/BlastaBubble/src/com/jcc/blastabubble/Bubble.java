package com.jcc.blastabubble;

import java.util.Random;

import com.jcc.framework2d.game.DynamicGameObject;

public class Bubble extends DynamicGameObject {
	public static final int MAX_COLOR_VALUE = 4;
	public static final int BUBBLE_STATE_IDLE = 0;
	public static final int BUBBLE_STATE_DROPPING = 1;
	public static final int BUBBLE_STATE_POPPING = 2;

	public static final float BUBBLE_WIDTH = 1.0f;
	public static final float BUBBLE_HEIGHT = 1.0f;
	public static final float BUBBLE_DROP_VELOCITY = 0.5f;

	// float posX,posY;
	int state;
	float stateTime;
	int color;

	public Bubble() {
		this(new Random().nextInt(320), 480, new Random()
				.nextInt(MAX_COLOR_VALUE));
	}

	public Bubble(float x, float y, int color) {
		super(x, y, BUBBLE_WIDTH, BUBBLE_HEIGHT);
		if (color > MAX_COLOR_VALUE || color < 0)
			throw new RuntimeException("Bubble color not valid");
		this.color = color;
		state = BUBBLE_STATE_IDLE;
		stateTime = 0;

	}

	public void update(float deltaTime) {

		switch (state) {
		case BUBBLE_STATE_IDLE:
			break;
		case BUBBLE_STATE_DROPPING:
			position.add(0, -BUBBLE_DROP_VELOCITY);
			stateTime = 0;
			break;
		case BUBBLE_STATE_POPPING:
			break;
		}

		stateTime += deltaTime;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		if (color > MAX_COLOR_VALUE || color < 0)
			throw new RuntimeException("Bubble color not valid");
		this.color = color;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
