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
	//this is example activity show how rare engine works
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		GameView gv = new GameView(this,this);
        setContentView(gv);
		GameObject o = new GameObject();
		ImageRenderer renderer = new ImageRenderer();
		renderer.setImage(renderer.getFromResource(getResources(),R.drawable.ic_launcher));
		o.addComponent(renderer,gv);
		o.addComponent(new ObjectScript(),gv);
		gv.addObject(o);
		GameObject oo = new GameObject();
		TextRenderer atr = new TextRenderer();
		oo.addComponent(atr,gv);
		atr.text="hello world\nhi\nthis is multiline text";
		gv.addObject(oo);
    }
}
