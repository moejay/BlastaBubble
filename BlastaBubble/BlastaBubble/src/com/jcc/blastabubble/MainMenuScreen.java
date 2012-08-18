package com.jcc.blastabubble;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

import com.jcc.framework2d.Game;
import com.jcc.framework2d.Input.TouchEvent;
import com.jcc.framework2d.gl.Camera2D;
import com.jcc.framework2d.gl.SpriteBatcher;
import com.jcc.framework2d.impl.GLScreen;
import com.jcc.framework2d.math.OverlapTester;
import com.jcc.framework2d.math.Rectangle;
import com.jcc.framework2d.math.Vector2;

public class MainMenuScreen extends GLScreen {
	Camera2D guiCam;
	SpriteBatcher batcher;
	Rectangle soundBounds;
	Rectangle playBounds;
	Rectangle highscoresBounds;
	Rectangle settingsBounds;
	Rectangle helpBounds;
	Vector2 touchPoint;
	float menuItemWidth, menuItemHeight;
	int numMenuItems;
	float menuItemsStartY;

	public MainMenuScreen(Game game) {
		super(game);
		Log.d("MainMenuScreen", "Creating");

		guiCam = new Camera2D(glGraphics, 320, 480);
		batcher = new SpriteBatcher(glGraphics, 100);
		numMenuItems = 4;
		Log.d("MainMenuScreen", "Okaaay");
		menuItemsStartY = 480 - 210;

		menuItemWidth = Assets.mainMenu.originalWidth;
		menuItemHeight = Assets.mainMenu.originalHeight / numMenuItems;
		Log.d("MainMenuScreen", "got width: '" + menuItemWidth
				+ "' and height :'" + menuItemHeight + "'");
		soundBounds = new Rectangle(0, 0, 64, 64);
		playBounds = new Rectangle(160, menuItemsStartY, menuItemWidth,
				menuItemHeight);
		highscoresBounds = new Rectangle(160, menuItemsStartY - menuItemHeight,
				menuItemWidth, menuItemHeight);
		helpBounds = new Rectangle(160, menuItemsStartY - 2 * menuItemHeight,
				menuItemWidth, menuItemHeight);
		settingsBounds = new Rectangle(160, menuItemsStartY - 3
				* menuItemHeight, menuItemWidth, menuItemHeight);

		touchPoint = new Vector2();
		Log.d("MainMenuScreen", "Done");

	}

	@Override
	public void update(float deltaTime) {

		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				touchPoint.set(event.x, event.y);
				guiCam.touchToWorld(touchPoint);
				if (OverlapTester.pointInRectangle(playBounds, touchPoint)) {
					Assets.playSound(Assets.clickSound);
					game.setScreen(new GameScreen(game));
					Log.d("MainMenuScreen", "Play touched");
					return;
				}
				if (OverlapTester
						.pointInRectangle(highscoresBounds, touchPoint)) {
					Assets.playSound(Assets.clickSound);
					// game.setScreen(new HighscoresScreen(game));
					Log.d("MainMenuScreen", "Highscores touched");
					return;
				}
				if (OverlapTester.pointInRectangle(helpBounds, touchPoint)) {
					Assets.playSound(Assets.clickSound);
					// game.setScreen(new HelpScreen2(game));
					Log.d("MainMenuScreen", "help touched");
					return;
				}
				if (OverlapTester.pointInRectangle(settingsBounds, touchPoint)) {
					Assets.playSound(Assets.clickSound);
					// game.setScreen(new SettingsScreen(game));
					Log.d("MainMenuScreen", "settings touched");
					return;
				}
				if (OverlapTester.pointInRectangle(soundBounds, touchPoint)) {
					Assets.playSound(Assets.clickSound);
					Settings.soundEnabled = !Settings.soundEnabled;
					// if (Settings.soundEnabled)
					// Assets.music.play();
					// else
					// Assets.music.pause();
				}
			}
		}

	}

	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.setViewportAndMatrices();
		gl.glEnable(GL10.GL_TEXTURE_2D);
		batcher.beginBatch(Assets.background);
		batcher.drawSprite(160, 240, 320, 480, Assets.backgroundRegion);
		batcher.endBatch();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		batcher.beginBatch(Assets.atlas);
		batcher.drawSprite(140, 420, Assets.logo.originalWidth,
				Assets.logo.originalHeight, Assets.logo);
		batcher.drawSprite(160, 210, Assets.mainMenu.originalWidth,
				Assets.mainMenu.originalHeight, Assets.mainMenu);
		batcher.drawSprite(32, 32, 64, 64,
				Settings.soundEnabled ? Assets.soundOn : Assets.soundOff);
		batcher.endBatch();
		gl.glDisable(GL10.GL_BLEND);
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
