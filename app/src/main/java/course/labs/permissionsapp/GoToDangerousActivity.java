package course.labs.permissionsapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.List;

public class GoToDangerousActivity extends Activity {
	
	private static final String TAG = "Lab-Permissions";

	private static final String DANGEROUS_ACTIVITY_ACTION = "course.labs.permissions.DANGEROUS_ACTIVITY";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.go_to_dangerous_activity);

		Button startDangerousActivityButton = (Button) findViewById(R.id.start_dangerous_activity_button);
		startDangerousActivityButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				startDangerousActivity();

			}
		});

	}

	private void startDangerousActivity() {

		Log.i(TAG, "Entered startDangerousActivity()");

        Intent dangerousActivityIntent = new Intent(DANGEROUS_ACTIVITY_ACTION);

        // Verify if the intent resolves
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(dangerousActivityIntent, 0);
        boolean isIntentSafe = activities.size() > 0;

        // Start an activity if it's safe
        if (isIntentSafe) {
            Log.i(TAG, "There is an app to receive dangerousActivityIntent");
            startActivity(dangerousActivityIntent);
        }
        else{
            Log.i(TAG, "There is NO app to receive dangerousActivityIntent");
        }

	}

}
