package RareEngine2.GameUtils;

public class Component implements Cloneable
{
	//this is main component that anyone can make their own by extending it
	public boolean isEnabled = true;
	//called when at start
	public void start(GameObject o,GameView gv){
		
	}
	//called before frame draw
	public void update(GameObject o,GameView gv){
		
	}
	//on destroy
	public void destroy(GameObject o,GameView gv){
		
	}
    //copy
    public Component copy(){
        try {
            return  (Component)this.clone();
        } catch (CloneNotSupportedException e) {
            return new Component();
        }
    }
	//on pause
	public void paused(GameObject o,GameView gv){
		
	}
}
