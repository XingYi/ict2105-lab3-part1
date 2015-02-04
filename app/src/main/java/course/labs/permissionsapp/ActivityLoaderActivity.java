package course.labs.permissionsapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;

public class ActivityLoaderActivity extends Activity {

	private static final String TAG = "Lab-Permissions";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loader_activity);

		Button startBookmarksButton = (Button) findViewById(R.id.start_bookmarks_button);
		startBookmarksButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				startBookMarksActivity();
			
			}
		});

        Button startConverterButton = (Button) findViewById(R.id.start_converter_button);
        startConverterButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                startConverterActivity();

            }
        });

	}

	private void startBookMarksActivity() {
		
		Log.i(TAG, "Entered startBookMarksActivity()");
	
		// TODO - Start the BookmarksActivity
        // Create an intent stating which Activity you would like to start
        Intent intent = new Intent(this, BookmarksActivity.class);

        // Launch the Activity using the intent
        startActivity(intent);

    }

    private void startConverterActivity() {

        Log.i(TAG, "startConverterActivity()");

        // TODO - Start the ConverterActivity
        Intent intent = new Intent(this, ConverterActivity.class);
        startActivity(intent);
    }

}
