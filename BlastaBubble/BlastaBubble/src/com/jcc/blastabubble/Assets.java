package com.jcc.blastabubble;

import com.jcc.framework2d.Sound;
import com.jcc.framework2d.gl.Animation;
import com.jcc.framework2d.gl.TextureRegion;
import com.jcc.framework2d.impl.GLGame;
import com.jcc.framework2d.impl.Texture;

public class Assets {
	public static Texture background;
	public static TextureRegion backgroundRegion;
	public static Texture atlas;
	public static TextureRegion mainMenu;
	public static TextureRegion pauseMenu;
	public static TextureRegion gameOver;
	public static TextureRegion highScoresRegion;
	public static TextureRegion logo;
	public static TextureRegion soundOn;
	public static TextureRegion soundOff;
	public static TextureRegion arrow;
	public static TextureRegion pause;

	public static Animation[] bubbleIdle = new Animation[5];
	public static Animation[] bubbleFalling;

	public static Sound clickSound;

	public static final int NumberOfBubbles = 5;

	public static void load(GLGame game) {
		background = new Texture(game, "images/background.png");
		backgroundRegion = new TextureRegion(background, 0, 0, 320, 480);

		atlas = new Texture(game, "images/atlas.png");
		mainMenu = new TextureRegion(atlas, 100, 140, 195, 270);
		logo = new TextureRegion(atlas, 100, 0, 236, 132);
		soundOff = new TextureRegion(atlas, 0, 0, 32, 32);
		soundOn = new TextureRegion(atlas, 32, 0, 32, 32);
		pause = new TextureRegion(atlas, 0, 32, 32, 32);
		arrow = new TextureRegion(atlas, 32, 32, 32, 32);

		for (int i = 0; i < NumberOfBubbles; i++) {
			bubbleIdle[i] = new Animation(0.2f, new TextureRegion(atlas,
					64 + (i * 32), 0, 32, 32), new TextureRegion(atlas,
					64 + (i * 32), 32, 32, 32), new TextureRegion(atlas,
					64 + (i * 32), 64, 32, 32));
		}

		clickSound = game.getAudio().newSound("sounds/click.wav");
	}

	public static void reload() {
		background.reload();
		atlas.reload();
	}

	public static void playSound(Sound sound) {
		if (Settings.soundEnabled)
			sound.play(1);
	}
}
