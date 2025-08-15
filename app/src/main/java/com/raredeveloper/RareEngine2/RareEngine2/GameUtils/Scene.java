package RareEngine2.GameUtils;

import RareEngine2.GameUtils.*;
import java.util.*;

public class Scene
{
	public ArrayList<GameObject> objects ;
	public int backgroundcolor=0;
	public Vector2 CameraPosition = new Vector2();
	private SceneComponent comp;
	public Scene(){
		objects = new ArrayList<GameObject>();
	}
	public GameObject getObjectWithName(String name){
		if(objects!=null){
			for(GameObject o : objects){
				if(o.getName().equals(name)){
					return o;
				}
			}
		}
		return new GameObject();
	}
	public ArrayList<GameObject> getObjectsWithName(String name){
		ArrayList<GameObject> obs = new ArrayList<>();
		if(objects!=null){
			for(GameObject o : objects){
				if(o.getName().equals(name)){
					obs.add(o);
				}
			}
		}
		return obs;
	}
	public ArrayList<GameObject> getObjectsFromLayer(String layer){
		ArrayList<GameObject> obs = new ArrayList<>();
		if(objects!=null){
			for(GameObject o : objects){
				if(o.getLayer().equals(layer)){
					obs.add(o);
				}
			}
		}
		return obs;
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
			if(o.getName().equals(name)){
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
