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
    public PreLoadScene(){
        objects = new ArrayList<>();
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
        s.setBackgroundColour(backgroundcolor);
        return s;
    }
}
