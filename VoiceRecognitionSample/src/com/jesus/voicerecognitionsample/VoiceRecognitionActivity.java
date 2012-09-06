package com.jesus.voicerecognitionsample;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class VoiceRecognitionActivity extends Activity implements
		TextToSpeech.OnInitListener {

	private static final int REQUEST_CODE = 1234;
	private Button mTalkButton;
	private ListView wordsList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_voice_recognition);
		mTalkButton = (Button) findViewById(R.id.talk_button);
		wordsList = (ListView) findViewById(R.id.listView1);
		// Disable button if no recognition service is present
		PackageManager pm = getPackageManager();
		List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(
				RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
		if (activities.size() == 0) {
			mTalkButton.setEnabled(false);
			mTalkButton.setText("Recognizer not present");
		} else {
			mTalkButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(
							RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
					intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
							RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
					intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
							"Voice recognition Demo...");
					intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");

					startActivityForResult(intent, REQUEST_CODE);

				}
			});
		}
	}

	/**
	 * Handle the results from the voice recognition activity.
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
			// Populate the wordsList with the String values the recognition
			// engine thought it heard
			ArrayList<String> matches = data
					.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			wordsList.setAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, matches));
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_voice_recognition, menu);
		return true;
	}

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub

	}

}
