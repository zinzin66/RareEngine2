package RareEngine2.GameUtils;

import RareEngine2.GameUtils.*;
import java.util.*;

public class Scene
{
	public ArrayList<GameObject> objects ;
	public int backgroundcolor=0;
	public Vector2 CameraPosition = new Vector2();
	private SceneComponent comp;
	private float zoom = 1;
	private Vector2 pinchPoint;
	public void setZoom(float zoom){
		this.zoom = zoom;
	}
	public float getZoom(){
		return zoom;
	}
	public void setPinchPoint(Vector2 pinchPoint){
		this.pinchPoint = pinchPoint;
	}
	public void setPinchPoint(float x,float y){
		pinchPoint.set(x,y);
	}
	public Vector2 getPinchPoint(){
		return pinchPoint;
	}
	public Scene(){
		objects = new ArrayList<GameObject>();
		pinchPoint = new Vector2();
		
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
