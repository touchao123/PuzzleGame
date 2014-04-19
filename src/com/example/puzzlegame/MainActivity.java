/**
 *  
 * Author:  Victor Dibia 
 * Date last modified: Feb 10, 2014
 * Sample Code for Learning Cocos2D for Android 
 */

package com.example.puzzlegame;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;



public class MainActivity extends Activity {

	public static MainActivity  app ;
	private static final int REQUEST_CODE = 1;
	public static Bitmap bitmap =  null ;
    
    

	protected CCGLSurfaceView _glSurfaceView; 
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		app =  this ;
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		_glSurfaceView = new CCGLSurfaceView(this);
		setContentView( _glSurfaceView); 

		CCDirector director = CCDirector.sharedDirector();
		director.attachInView(_glSurfaceView);
		director.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft);
		CCDirector.sharedDirector().setAnimationInterval(1.0f / 60.0f);
		CCDirector.sharedDirector().setDisplayFPS(true);
		CCScene scene =  SlidingMenuLayer.scene(); //  
		CCDirector.sharedDirector().runWithScene(scene); 

	}
	@Override
	public void onStart()
	{
		super.onStart();
		

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onPause()
	{
		super.onPause();

		CCDirector.sharedDirector().pause();
	}

	@Override
	public void onResume()
	{
		super.onResume();

		CCDirector.sharedDirector().resume();
	}

	@Override
	public void onStop()
	{
		super.onStop();

		CCDirector.sharedDirector().end();
	} 
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK)
            try {
                // We need to recyle unused bitmaps
                if (MainActivity.bitmap != null) {
                	MainActivity.bitmap.recycle();
                }
                InputStream stream = getContentResolver().openInputStream(
                        data.getData());
                MainActivity.bitmap = Bitmap.createBitmap(BitmapFactory.decodeStream(stream));
                stream.close();
                //imageView.setImageBitmap(bitmap);
                CCScene scene =  CameraPictureGameLayer.scene(); //  
        		CCDirector.sharedDirector().runWithScene(scene); 
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
