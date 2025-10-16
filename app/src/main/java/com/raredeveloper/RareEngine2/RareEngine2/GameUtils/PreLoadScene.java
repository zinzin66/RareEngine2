package RareEngine2.GameUtils;

/**
 * @Author Rare Developer 
 * @Date 2025/06/15 14:01
 */
import android.app.Activity;
import java.util.ArrayList;
/*
its is like scene file
you can store scene here and create scene from anywhere to initialize to screen
*/
public class PreLoadScene {
    public ArrayList<GameObject> objects ;
	public int backgroundcolor=0;
	private SceneComponent comp;
	public Vector2 CameraPosition = new Vector2();
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
    public PreLoadScene(){
        objects = new ArrayList<>();
		pinchPoint = new Vector2();
    }
	public void setSceneComponent(SceneComponent comp){
		this.comp = comp;
	}
	public SceneComponent getSceneComponent(){
		return comp;
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
    public void addObject(GameObject o){
        objects.add(o);
    }
    public void removeObject(GameObject o){
        objects.remove(o);
    }
    public void removeObjectWithName(String name){
        for(GameObject o:objects){
            if(o.getName().equals( name)){
                objects.remove(o);
                break;
            }
        }
    }
    public void setBackgroundColour(int color)
    {
        backgroundcolor = color;
	}
    public Scene createScene(GameView gv){
        Scene s = new Scene();
        for(GameObject o:objects){
            s.addObject(o.copy(gv));
        }
		s.CameraPosition = new Vector2(CameraPosition);
		s.setZoom(getZoom());
		s.setPinchPoint(new Vector2(getPinchPoint()));
		if(comp!= null)s.setSceneComponent(getSceneComponent().copy(),gv);
        s.setBackgroundColour(backgroundcolor);
        return s;
    }

	@Override
	public String toString() {
		String ob = "";
		for(GameObject o: objects){
			ob.concat("\n").concat(o.toString());
		}
		ob.concat("\n").concat(""+backgroundcolor);
		ob.concat("\n").concat(comp.toString());
		return ob;
	}
	
}
