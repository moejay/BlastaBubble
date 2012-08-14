package com.jcc.framework2d.impl;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.jcc.framework2d.Audio;
import com.jcc.framework2d.Music;
import com.jcc.framework2d.Sound;

public class AndroidAudio implements Audio {
	AssetManager assets;
	SoundPool soundPool;

	public AndroidAudio(Activity activity) {
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets = activity.getAssets();
		this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
	}

	@Override
	public Music newMusic(String filename) {
		try {
			AssetFileDescriptor afd = assets.openFd(filename);
			return new AndroidMusic(afd);
		} catch (IOException e) {
			throw new RuntimeException("Couldn't load music file: '" + filename
					+ "'");
		}

	}

	@Override
	public Sound newSound(String filename) {
		try {
			AssetFileDescriptor afd = assets.openFd(filename);
			int SoundId = soundPool.load(afd, 0);
			return new AndroidSound(soundPool, SoundId);
		} catch (IOException e) {
			throw new RuntimeException("Couldn't load sound file: '" + filename
					+ "'");

		}
	}

}
