package com.beta.a360ar;

import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.ar.core.ArCoreApk;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;

public class MainActivity extends AppCompatActivity {
private Button button;
private ArFragment arFragment;
private ModelRenderable toyotaR;
private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        //maybeEnableArButton();

        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("Toyota Prius XW30 2010.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept(renderable -> toyotaR = renderable)
                .exceptionally(
                        throwable -> {
                            Log.e(TAG, "Unable to load Renderable.", throwable);
                            return null;
                        });
        Node node = new Node();
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ar_fragment);
        node.setParent(arFragment.getArSceneView().getScene());
        node.setRenderable(toyotaR);




    }

    @Override
    protected void onResume() {
        super.onResume();



    }




    void maybeEnableArButton() {
        ArCoreApk.Availability availability = ArCoreApk.getInstance().checkAvailability(this);
        if (availability.isTransient()) {
            // Re-query at 5Hz while compatibility is checked in the background.
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    maybeEnableArButton();
                }
            }, 200);
        }
        if (availability.isSupported()) {
            mArButton.setVisibility(View.VISIBLE);
            mArButton.setEnabled(true);
            // indicator on the button.
        } else { // Unsupported or unknown.
            mArButton.setVisibility(View.INVISIBLE);
            mArButton.setEnabled(false);
        }
    }


}
