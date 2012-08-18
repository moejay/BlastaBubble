package com.jcc.blastabubble;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

import com.jcc.framework2d.Game;
import com.jcc.framework2d.gl.Camera2D;
import com.jcc.framework2d.gl.SpriteBatcher;
import com.jcc.framework2d.impl.GLScreen;
import com.jcc.framework2d.math.Rectangle;
import com.jcc.framework2d.math.Vector2;

public class GameScreen extends GLScreen {
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_LEVEL_END = 3;
	static final int GAME_OVER = 4;
	int state;
	Camera2D guiCam;
	Vector2 touchPoint;
	SpriteBatcher batcher;
	World world;
	// WorldListener worldListener;
	WorldRenderer renderer;
	Rectangle pauseBounds;
	Rectangle resumeBounds;
	Rectangle quitBounds;
	int lastScore;
	String scoreString;

	public GameScreen(Game game) {
		super(game);
		Log.d("GameScreen", "Creating");
		state = GAME_RUNNING;
		guiCam = new Camera2D(glGraphics, 320, 480);
		touchPoint = new Vector2();
		batcher = new SpriteBatcher(glGraphics, 1000);
		world = new World();
		renderer = new WorldRenderer(glGraphics, batcher, world);
		Log.d("GameScreen", "Done");
	}

	@Override
	public void update(float deltaTime) {
		if (deltaTime > 0.1f)
			deltaTime = 0.1f;
		switch (state) {
		case GAME_READY:
			updateReady();
			break;
		case GAME_RUNNING:
			updateRunning(deltaTime);
			break;
		case GAME_PAUSED:
			updatePaused();
			break;
		case GAME_LEVEL_END:
			updateLevelEnd();
			break;
		case GAME_OVER:
			updateGameOver();
			break;
		}
	}

	private void updateGameOver() {
		// TODO Auto-generated method stub

	}

	private void updateLevelEnd() {
		// TODO Auto-generated method stub

	}

	private void updatePaused() {
		// TODO Auto-generated method stub

	}

	private void updateRunning(float deltaTime) {
		world.update(deltaTime);

	}

	private void updateReady() {
		// TODO Auto-generated method stub

	}

	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		renderer.render();
		guiCam.setViewportAndMatrices();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		batcher.beginBatch(Assets.atlas);
		switch (state) {
		case GAME_READY:
			presentReady();
			break;
		case GAME_RUNNING:
			presentRunning();
			break;
		case GAME_PAUSED:
			presentPaused();
			break;
		case GAME_LEVEL_END:
			presentLevelEnd();
			break;
		case GAME_OVER:
			presentGameOver();
			break;
		}
		batcher.endBatch();
		gl.glDisable(GL10.GL_BLEND);
	}

	private void presentRunning() {
		batcher.drawSprite(0, 0, 64, 64, Assets.pause);
	}

	private void presentGameOver() {
		// TODO Auto-generated method stub

	}

	private void presentLevelEnd() {
		// TODO Auto-generated method stub

	}

	private void presentPaused() {
		// TODO Auto-generated method stub

	}

	private void presentReady() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
