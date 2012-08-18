package com.jcc.framework2d.game;

import com.jcc.framework2d.math.Rectangle;
import com.jcc.framework2d.math.Vector2;

public class GameObject {
	public final Vector2 position;
	public final Rectangle bounds;

	public GameObject(float x, float y, float width, float height) {
		this.position = new Vector2(x, y);
		this.bounds = new Rectangle(x - width / 2, y - height / 2, width,
				height);
	}

}
