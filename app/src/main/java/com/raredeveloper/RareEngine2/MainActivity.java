package com.raredeveloper.RareEngine2;
 
import android.app.Activity;
import android.os.Bundle;
import RareEngine2.GameUtils.GameView;
import android.view.Window;
import RareEngine2.GameUtils.GameObject;
import RareEngine2.GameUtils.ImageRenderer;
//@author raredeveloper
public class MainActivity extends Activity {
    //main game view
     public GameView gamev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialize a game view
        gamev = new GameView(this,this);
        //set game view to screen
        setContentView(gamev);
        //creating game object
        GameObject o = new GameObject();
        //create and add components
        ImageRenderer ir = new ImageRenderer();
        ir.setImage(ImageRenderer.getFromResource(getResources(),R.drawable.ic_launcher));
        o.addComponent(ir,gamev);
        Script sc = new Script();
        o.addComponent(sc,gamev);
        //add object to game view's current scene
        gamev.currentScene.addObject(o);
    }
	
}
