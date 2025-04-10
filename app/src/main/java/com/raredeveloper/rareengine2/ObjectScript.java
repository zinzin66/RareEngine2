package com.raredeveloper.rareengine2;
import android.content.*;
import RareEngine2.GameUtils.*;
import android.widget.*;

public class ObjectScript extends Component
{
	@Override
	public void start(GameObject o,GameView gv)
	{
		super.start(o,gv);
	}

	@Override
	public void update(GameObject o,GameView gv)
	{
		super.update(o,gv);
	if(o.isDrag){
			o.position.set(o.touchPosition.getX(),o.touchPosition.getY());
		}
		
	}
	
	
}
