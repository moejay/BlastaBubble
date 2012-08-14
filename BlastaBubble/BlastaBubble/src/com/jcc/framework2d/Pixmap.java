package com.jcc.framework2d;

import com.jcc.framework2d.Graphics.PixmapFormat;

public interface Pixmap {
public int getWidth();
public int getHeight();
public PixmapFormat getFormat();
public void dispose();
}