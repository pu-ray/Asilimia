package com.gamecodeschool.asilimia;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hover.sdk.actions.HoverAction;
import com.hover.sdk.api.Hover;
import com.hover.sdk.api.HoverParameters;
import com.hover.sdk.permissions.PermissionActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Hover.initialize( this);

        Button permissionsButton = findViewById(R.id.permissions_button);

        permissionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PermissionActivity.class);
                startActivityForResult(i, 0);
            }
        });


        Button button= (Button) findViewById(R.id.action_button);
        button.setEnabled(true);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new HoverParameters.Builder(MainActivity.this)
                        .request("action_id")
                        .buildIntent();
                startActivityForResult(i, 0);
            }
        });
    }

     public void onError(String message) {
		Toast.makeText(this, "Error while attempting to download actions, see logcat for error", Toast.LENGTH_LONG).show();
        Log.e(TAG, "Error: " + message);
    }
     public void onSuccess(ArrayList<HoverAction> actions) {
		Toast.makeText(this, "Successfully downloaded " + actions.size() + " actions", Toast.LENGTH_LONG).show();
        Log.d(TAG, "Successfully downloaded " + actions.size() + " actions");
    }
}



