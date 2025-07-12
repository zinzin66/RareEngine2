package RareEngine2.GameUtils;

/**
 * @Author Rare Developer 
 * @Date 2025/06/15 14:01
 */
import java.util.ArrayList;
/*
its is like scene file
you can store scene here and create scene from anywhere to initialize to screen
*/
public class PreLoadScene {
    public ArrayList<GameObject> objects ;
	public int backgroundcolor=0;
	private SceneComponent comp;
    public PreLoadScene(){
        objects = new ArrayList<>();
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
				if(o.name.equals(name)){
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
				if(o.name.equals(name)){
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
				if(o.layer.equals(layer)){
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
            if(o.name.equals( name)){
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
		if(comp!= null)s.setSceneComponent(getSceneComponent().copy(),gv);
        s.setBackgroundColour(backgroundcolor);
        return s;
    }
}
