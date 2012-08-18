package com.jcc.blastabubble;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

import com.jcc.framework2d.gl.Animation;
import com.jcc.framework2d.gl.Camera2D;
import com.jcc.framework2d.gl.SpriteBatcher;
import com.jcc.framework2d.gl.TextureRegion;
import com.jcc.framework2d.impl.GLGraphics;

public class WorldRenderer {
	static final float FRUSTUM_WIDTH = 10;
	static final float FRUSTUM_HEIGHT = 15;
	GLGraphics glGraphics;
	World world;
	Camera2D cam;
	SpriteBatcher batcher;

	public WorldRenderer(GLGraphics glGraphics, SpriteBatcher batcher,
			World world) {
		Log.d("WorldRenderer", "Creating");
		this.glGraphics = glGraphics;
		this.world = world;
		this.cam = new Camera2D(glGraphics, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		this.batcher = batcher;
		Log.d("WorldRenderer", "Done.");
	}

	public void render() {
		cam.setViewportAndMatrices();
		renderBackground();
		renderBubbles();
	}

	private void renderBubbles() {
		GL10 gl = glGraphics.getGL();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		batcher.beginBatch(Assets.atlas);
		int len = world.bubbles.size();
		for (int i = 0; i < len; i++) {
			Bubble b = world.bubbles.get(i);
			TextureRegion keyFrame = Assets.bubbleIdle[b.getColor()]
					.getKeyFrame(b.stateTime, Animation.ANIMATION_LOOPING);
			batcher.drawSprite(b.position.x, b.position.y, 1f, 1f, keyFrame);
		}
		batcher.endBatch();
		gl.glDisable(GL10.GL_BLEND);
	}

	private void renderBackground() {
		batcher.beginBatch(Assets.background);
		Log.d("WorldRenderer:renderBackground", "cam.position.x:"
				+ cam.position.x + " , y:" + cam.position.y);
		batcher.drawSprite(cam.position.x, cam.position.y, FRUSTUM_WIDTH,
				FRUSTUM_HEIGHT, Assets.backgroundRegion);
		// batcher.drawSprite(160, 240, 320, 480, Assets.backgroundRegion);
		batcher.endBatch();
	}
}