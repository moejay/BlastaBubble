package com.jcc.blastabubble;

import com.jcc.framework2d.Screen;
import com.jcc.framework2d.impl.GLGame;

public class BlastaBubbleMain extends GLGame {
	@Override
	public Screen getStartScreen() {
		return new MainMenuScreen(this);
	}
}
