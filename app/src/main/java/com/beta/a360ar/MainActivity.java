package com.beta.a360ar;

import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
private Button button;
private ArFragment arFragment;
private ModelRenderable toyotaR;

    private ViewRenderable infoR;

private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setVisibility(View.GONE);



    /* TODO MYSQL CONNECTION WITH JDBC */







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
                            System.out.println("CANT LOOAD");
                            return null;
                        });

        ViewRenderable.builder().setView(this,R.layout.info_board)
                .build()
                .thenAccept(renderable -> infoR = renderable);



        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ar_fragment);





        arFragment.setOnTapArPlaneListener(
                (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {

                    // Create the Anchor.
                    Anchor anchor = hitResult.createAnchor();
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    anchorNode.setParent(arFragment.getArSceneView().getScene());


                    // Create the transformable andy and add it to the anchor.
                    TransformableNode andy = new TransformableNode(arFragment.getTransformationSystem());
                    andy.setParent(anchorNode);

                    andy.setRenderable(toyotaR);



                    Node node = new Node();
                    node.setParent(andy);
                    node.setRenderable(infoR);
                    node.setLocalPosition(new Vector3(0.4f, 1.2f, 1f));

                    andy.select();

                });








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
            button.setVisibility(View.VISIBLE);
            button.setEnabled(true);
            // indicator on the button.
        } else { // Unsupported or unknown.
            button.setVisibility(View.INVISIBLE);
            button.setEnabled(false);
        }
    }


}
