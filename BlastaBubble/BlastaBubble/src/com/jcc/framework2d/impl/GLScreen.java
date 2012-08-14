package com.jcc.framework2d.impl;

import com.jcc.framework2d.Game;
import com.jcc.framework2d.Screen;

public abstract class GLScreen extends Screen {
	protected final GLGraphics glGraphics;
	protected final GLGame glGame;

	public GLScreen(Game game) {
		super(game);
		glGame = (GLGame) game;
		glGraphics = ((GLGame) game).getGLGraphics();
	}
}