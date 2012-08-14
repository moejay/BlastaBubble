package com.jcc.framework2d.impl;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import com.jcc.framework2d.Audio;
import com.jcc.framework2d.FileIO;
import com.jcc.framework2d.Game;
import com.jcc.framework2d.Graphics;
import com.jcc.framework2d.Input;
import com.jcc.framework2d.Screen;

public abstract class AndroidGame extends Activity implements Game {

	AndroidFastRenderView renderView;
	Graphics graphics;
	Audio audio;
	Input input;
	FileIO fileIO;
	Screen screen;
	WakeLock wakeLock;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.v("AndroidGame", "Creating");
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
		int frameBufferWidth = isLandscape ? 480 : 320;
		int frameBufferHeight = isLandscape ? 320 : 480;
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
				frameBufferHeight, Config.RGB_565);
		Display d = getWindowManager().getDefaultDisplay();
		float scaleX = (float) frameBufferWidth / d.getWidth();
		float scaleY = (float) frameBufferHeight / d.getHeight();

		renderView = new AndroidFastRenderView(this, frameBuffer);
		graphics = new AndroidGraphics(getAssets(), frameBuffer);
		fileIO = new AndroidFileIO(getAssets());
		audio = new AndroidAudio(this);
		input = new AndroidInput(this, renderView, scaleX, scaleY);
		screen = getStartScreen();
		setContentView(renderView);
		Log.v("AndroidGame", "Modules created");
		PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,
				"GLGame");
		Log.v("AndroidGame", "Created");
	}

	public void onResume() {
		super.onResume();
		Log.v("AndroidGame", "Activity resumed, resuming rest of game");
		wakeLock.acquire();
		Log.v("AndroidGame", "WakeLock acquired");
		screen.resume();
		Log.v("AndroidGame", "Screen resumed");
		renderView.resume();
		Log.v("AndroidGame", "Resumed");
	}

	public void onPause() {
		super.onPause();
		wakeLock.release();
		renderView.pause();
		screen.pause();
		if (isFinishing())
			screen.dispose();
	}

	@Override
	public Input getInput() {
		return input;
	}

	@Override
	public FileIO getFileIO() {
		return fileIO;
	}

	@Override
	public Graphics getGraphics() {
		return graphics;
	}

	@Override
	public Audio getAudio() {
		return audio;
	}

	@Override
	public void setScreen(Screen screen) {
		if (screen == null)
			throw new IllegalArgumentException("Screen must not be null");
		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		this.screen = screen;
	}

	@Override
	public Screen getCurrentScreen() {
		return screen;
	}

	@Override
	public abstract Screen getStartScreen();

}
