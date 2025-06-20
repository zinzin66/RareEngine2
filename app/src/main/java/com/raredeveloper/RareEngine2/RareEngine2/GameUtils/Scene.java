package RareEngine2.GameUtils;

import RareEngine2.GameUtils.*;
import java.util.*;

public class Scene
{
	public ArrayList<GameObject> objects ;
	public int backgroundcolor=0;
	private SceneComponent comp;
	public Scene(){
		objects = new ArrayList<GameObject>();
	}
	public void setSceneComponent(SceneComponent comp,GameView gv){
		this.comp = comp;
		if(comp!=null)getSceneComponent().onAttach(gv);else getSceneComponent().onRemove(gv);
	}
	public SceneComponent getSceneComponent(){
		return comp;
	}
	public void addObject(GameObject o){
		objects.add(o);
	}
	public void removeObject(GameObject o){
		objects.remove(o);
	}
	public void removeObjectWithName(String name){
		for(GameObject o:objects){
			if(o.name.equals(name)){
				objects.remove(o);
				break;
			}
		}
	}
	public void setBackgroundColour(int color)
	{
		backgroundcolor = color;
	}
}
