package com.raredeveloper.rareengine2;

import RareEngine2.GameUtils.*;
import android.app.*;
import android.content.*;
import android.os.*;
import android.text.*;
import android.widget.*;

import android.content.ClipboardManager;

public class MainActivity extends Activity 
{
	public static Context ct;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		GameView gv = new GameView(this,this);
        setContentView(gv);
		MainActivity.ct = this;
		
		try{
		GameObject o = new GameObject();
		ImageRenderer renderer = new ImageRenderer();
		renderer.setImage(renderer.getFromResource(getResources(),R.drawable.ic_launcher));
		o.addComponent(renderer,gv);
		o.addComponent(new ObjectScript(),gv);
		gv.addObject(o);
		}catch(Exception e){
			String exceptionMessage = e.getMessage();

			// Get the ClipboardManager system service
			ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

			// Copy the exception message to the clipboard
			if (clipboard != null) {
				android.content.ClipData clip = android.content.ClipData.newPlainText("Exception Message", exceptionMessage);
				clipboard.setPrimaryClip(clip);

				// Show a toast message to inform the user
				Toast.makeText(this, "Exception copied to clipboard", Toast.LENGTH_SHORT).show();
			}
		}
    }
}
