package com.jesus.voicerecognitionsample;

import java.util.HashMap;
import java.util.Locale;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;

public class TTSUtils implements TextToSpeech.OnInitListener {

	private TextToSpeech mTts;
	private static TTSUtils mInstance;
	private Context mContext;

	public static TTSUtils getInstance(Context c) {
		if (mInstance == null) {
			mInstance = new TTSUtils(c);
		}
		return mInstance;
	}

	private TTSUtils(Context c) {
		mContext = c;
		mTts = new TextToSpeech(mContext, this);
		mInstance = this;
	}

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub

	}

	public void destroyTTS() {
		if (mTts != null) {
			mTts.stop();
			mTts.shutdown();
		}
	}

	public int setLanguage(Locale l) {
		return mTts.setLanguage(l);
	}

	public int speak(String s, int i, HashMap<String, String> h) {
		return mTts.speak(s, i, h);
	}

}
