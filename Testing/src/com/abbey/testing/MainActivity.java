package com.abbey.testing;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	int TAKE_PHOTO_CODE = 0;
	public static int count=0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);

	//here,we are making a folder named picFolder to store pics taken by the camera using this application
	        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
	    //final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) +"/";
	        File newdir = new File(dir); 
	        newdir.mkdirs();

	    Button capture = (Button) findViewById(R.id.btnCapture);
	    capture.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {

	            // here,counter will be incremented each time,and the picture taken by camera will be stored as 1.jpg,2.jpg and likewise.
	            count++;
	            String file = dir+count+".jpg";
	            Log.d("##########################"+file, "##########################"+file);
	            File newfile = new File(file);
	            try {
	                newfile.createNewFile();
	            } catch (IOException e) {
	            	e.printStackTrace();
	            }       

	            Uri outputFileUri = Uri.fromFile(newfile);

	            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); 
	            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

	            startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
	        }
	    });
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);

	    if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
	        Log.d("CameraDemo", "Pic saved");


	    }
	}

}
