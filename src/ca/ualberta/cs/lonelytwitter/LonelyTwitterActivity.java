package ca.ualberta.cs.lonelytwitter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;
	private TweetListController tlc;
	private ArrayAdapter<LonelyTweetModel> adapter;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		tlc = new TweetListController();

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);
		adapter = new ArrayAdapter<LonelyTweetModel>(this,
				R.layout.list_item, tlc.getList());
		oldTweetsList.setAdapter(adapter);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				tlc.addTweet(new String(new Date(System.currentTimeMillis()).toString() + " | " + text));
				adapter.notifyDataSetChanged();
				saveInFile();
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		loadFromFile();
		adapter.notifyDataSetChanged();
	}

	private void loadFromFile() {
		try {
			Gson gson = new Gson();
			FileInputStream fis = openFileInput(FILENAME);
			InputStreamReader isr = new InputStreamReader(fis);
			TweetListModel tlm = gson.fromJson(isr, TweetListModel.class);
			tlc.setTweetListModel(tlm);
			isr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		adapter.notifyDataSetChanged();
	}
	
	private void saveInFile() {
		try {
			Gson gson = new Gson();
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_APPEND);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			gson.toJson(tlc.getTweetListModel(), osw);
			osw.flush();
			osw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}