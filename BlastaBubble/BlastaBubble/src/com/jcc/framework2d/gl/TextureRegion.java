package com.jcc.framework2d.gl;

import com.jcc.framework2d.impl.Texture;

public class TextureRegion {
	public final float u1, v1;
	public final float u2, v2;
	public final Texture texture;

	public final float originalWidth, originalHeight;

	public TextureRegion(Texture texture, float x, float y, float width,
			float height) {
		this.u1 = x / texture.width;
		this.v1 = y / texture.height;
		this.originalWidth = width;
		this.originalHeight = height;
		this.u2 = this.u1 + width / texture.width;
		this.v2 = this.v1 + height / texture.height;
		this.texture = texture;
	}
}
