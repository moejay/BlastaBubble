package com.jcc.framework2d.impl;

import java.util.List;

import android.view.View.OnTouchListener;

import com.jcc.framework2d.Input.TouchEvent;

public interface TouchHandler extends OnTouchListener {

	public boolean isTouchDown(int pointer);

	public int getTouchX(int pointer);

	public int getTouchY(int pointer);

	public List<TouchEvent> getTouchEvents();
}
