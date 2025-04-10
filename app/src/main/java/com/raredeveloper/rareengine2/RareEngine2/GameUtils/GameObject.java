package RareEngine2.GameUtils;
import java.util.*;

public class GameObject
{
	public boolean isEnabled,isVisible,isDown,isUp,isClick,isDrag;
	public Vector2 position,scale,globalposition,globalscale,touchPosition;
	public GameObject parent;
	public float rotation,globalrotation;
	private ArrayList<Component> components;
	public String name,layer;
	public GameObject(){
		isEnabled = true;
		isVisible = true;
		parent = null;
		isDown = false;isUp = true;isClick = false;isDrag = false;
		position=new Vector2();
		scale = new Vector2(100,100);
		name = "game object";
		layer = "objects";
		touchPosition = new Vector2();
		globalposition = new Vector2();
		globalscale = new Vector2();
		rotation = 0;
		globalrotation = 0;
		components = new ArrayList<Component>();
	}
	public ArrayList<Component> getComponents(){
		return components;
	}
	public void addComponent(Component component,GameView gv){
		components.add(component);
		component.start(this,gv);
	}
	public void removeComponent(Component component,GameView gv){
		components.remove(component);
		component.destroy(this,gv);
	}
	public Component getComponentAt(int index){
		return components.get(index);
	}
	public void removeComponentAt(int id){
		components.remove(id);
	}
	public void calculateGlobals(){
		if(parent!=null){
			parent.calculateGlobals();
			globalposition = new Vector2(position.getX()+parent.globalposition.getX(),position.getY()+parent.globalposition.getY());
			globalscale = new Vector2(scale.getX()+parent.globalscale.getX(),scale.getY()+parent.globalscale.getY());
			globalrotation = rotation+parent.globalrotation;
		}else{
			globalposition = new Vector2(position);
			globalscale = new Vector2(scale);
			globalrotation = rotation;
		}
		
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setLayer(String layer){
		this.layer = layer;
	}
	public String getLayer(){
		return layer;
	}
	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public void setPosition(float x, float y) {
		if (this.position == null) this.position = new Vector2();
		this.position.set(x, y);
	}

	public Vector2 getScale() {
		return scale;
	}

	public void setScale(Vector2 scale) {
		this.scale = scale;
	}

	public void setScale(float x, float y) {
		if (this.scale == null) this.scale = new Vector2();
		this.scale.set(x, y);
	}

	public GameObject getParent() {
		return parent;
	}

	public void setParent(GameObject parent) {
		this.parent = parent;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public void setRotation(float x, float y) {
		this.rotation = (float) Math.toDegrees(Math.atan2(y, x));
	}
	
}
