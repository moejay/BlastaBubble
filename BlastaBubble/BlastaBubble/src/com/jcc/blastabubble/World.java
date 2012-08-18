package com.jcc.blastabubble;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.util.Log;

import com.jcc.framework2d.math.Vector2;

public class World {
	public interface WorldListerner {
		public void pop_bubble();

	}

	public static final float WORLD_WIDTH = 10;
	public static final float WORLD_HEIGHT = 15;
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;
	public static final Vector2 gravity = new Vector2(0, -12);

	public Random rand;

	List<Bubble> bubbles;
	public float bubblesLeft;
	public int score;
	public int state;

	public World() {
		Log.d("World", "Creating");
		bubbles = new ArrayList<Bubble>();
		rand = new Random();
		generateLevel();
		score = 0;
		state = WORLD_STATE_RUNNING;
		Log.d("World", "Done");
	}

	private void generateLevel() {
		for (int i = 0; i < Settings.NUM_OF_COLUMNS; i++) {
			for (int j = 0; j < Settings.NUM_OF_ROWS; j++) {
				Bubble b = new Bubble(rand.nextInt(10), 15,
						rand.nextInt(Bubble.MAX_COLOR_VALUE));
				bubbles.add(b);
			}
		}

	}

	public void update(float deltaTime) {
		int len = bubbles.size();
		for (int i = 0; i < len; i++) {
			bubbles.get(i).update(deltaTime);
		}
	}

}
