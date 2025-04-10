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
		o.position.setY(o.position.getY()+1);
		o.rotation =(float) Vector2.angleBetween(o.position,gv.touchPosition);
		if(gv.isDrag)gv.toast(gv.touchPosition.toString(),Toast.LENGTH_SHORT);
	}
	
	
}
