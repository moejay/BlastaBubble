package com.jcc.blastabubble;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.jcc.framework2d.Screen;
import com.jcc.framework2d.impl.GLGame;

public class BlastaBubbleMain extends GLGame {
	boolean firstTimeCreate = true;

	@Override
	public Screen getStartScreen() {
		if (firstTimeCreate) {
			Assets.load(this);
			firstTimeCreate = false;
		} else {
			// Assets.reload();
		}
		return new MainMenuScreen(this);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);
		if (firstTimeCreate) {
			Settings.load(getFileIO());
			Assets.load(this);
			firstTimeCreate = false;
		} else {
			Assets.reload();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		// if (Settings.soundEnabled)
		// Assets.music.pause();
	}
}
