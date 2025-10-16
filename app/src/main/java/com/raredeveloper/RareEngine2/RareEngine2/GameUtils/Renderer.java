package RareEngine2.GameUtils;
import android.graphics.*;


public class Renderer extends Component
{
	public Vector2 scale;
	public Renderer(){
		scale = new Vector2();
	}
	public void render(Canvas canvas,GameObject object,Paint p,GameView gv){
		
	}
	public Vector2 getScale(){
		return scale;
	}
	public void setScale(Vector2 scale){
		this.scale = scale;
	}
	public void setScale(float x,float y){
		scale.set(x,y);
	}
	public void setScale(float a){
		scale.set(a,a);
	}
}
