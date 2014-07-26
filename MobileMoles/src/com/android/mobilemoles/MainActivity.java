package com.android.mobilemoles;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	private static final String PLANTPLACES_COLOR_ACTION = "com.plantplaces.Color";
	private static final String IMAGE_FILENAME = "IMAGE_FILENAME";
	private static final int IMAGE_GALLERY = 10;
	public static final int PLANT_RESULTS = 1;
	public static final String SEARCH_PLANT_NAME = "SEARCH_PLANT_NAME";
	private AutoCompleteTextView actPlantName;
	private Bitmap selectedImage;
	private ImageView imgPlantSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment())
			.commit();
		}
	}


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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			return rootView;
		}
	}

	public static void retreivePhoto(){
		// we want to pick an image from a gallery, so specify the action pick.
		Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

		// give me the path (file system directory) where we store images.
		String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();

		// Convert to URI, because that's what our Gallery is expecting.
		Uri picturesDirectory = Uri.parse(path);

		// set the data and type on this intent, so we tell it where to look for files, and 
		// what file types we want.
		photoPickerIntent.setDataAndType(picturesDirectory, "image/*");

		// find the path of the selected image.
		Uri photoLocation = photoPickerIntent.getData();

		// invoke the color match activity from PlantPlaces.com Mobile for Android - Production.
		Intent plantPlacesColor = new Intent(PLANTPLACES_COLOR_ACTION);

		// pass in the path of the image that was selected.

		// a bundle is a collection of related data that you can pass from one intent to another.
		Bundle fileInfo = new Bundle();

		// add the file location to this bundle.
		fileInfo.putString(IMAGE_FILENAME, photoLocation.toString());

	}


}
