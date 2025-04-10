package RareEngine2.GameUtils;
import android.graphics.*;
import android.content.res.*;
import android.widget.*;



public class ImageRenderer extends Renderer
{
	public Bitmap image;
	public Vector2 tilescale,tileid;
	public static final float MATCH_IMAGE =-1;
	public ImageRenderer(){
		tileid = new Vector2(0,0);
		tilescale = new Vector2();
	}
	@Override
	public void start(GameObject o,GameView gv)
	{
		super.start(o,gv);
		
	}
	public Vector2 getTileIdWithNumber(int number){
		int width = Math.round(image.getWidth()/tilescale.getX());
		int x = number%width;
		int y = (int)Math.ceil((double)number/width);
		return new Vector2(x,y);
	}
	@Override
	public void update(GameObject o,GameView gv)
	{
		super.update(o,gv);
	}
	
	@Override
	public void destroy(GameObject o,GameView gv)
	{
		super.destroy(o,gv);
		if(image!= null){
			image.recycle();
			image = null;
		}
	}
	@Override
	public void render(Canvas canvas, GameObject object,Paint p,GameView gv)
	{
		super.render(canvas, object,p,gv);
		if(image != null){
			if(!(tilescale.getX() == MATCH_IMAGE)){
				tilescale.setX(image.getWidth());
			}
			if(!(tilescale.getY() == MATCH_IMAGE)){
				tilescale.setY(image.getHeight());
			}
			Rect src = new Rect();
			src.set(Math.round(tileid.getX()*tilescale.getX()),Math.round(tileid.getY()*tilescale.getY()),Math.round(tilescale.getX()+(tileid.getX()*tilescale.getX())),Math.round(tilescale.getY()+(tileid.getY()*tilescale.getY())));
			RectF dst = new RectF();
			//ltrb
			dst.set(-object.scale.getX()/2,-object.scale.getY()/2,object.scale.getX()/2,object.scale.getY()/2);
			canvas.drawBitmap(image,src,dst,p);
			
		}
		
	}
	public void setImage(Bitmap map){
		image = map;
	}
	public Bitmap getFromResource(Resources resource,int resid){
		Bitmap m = BitmapFactory.decodeResource(resource,resid);
		return m;
	}
	public Bitmap getFromPath(String path){
		Bitmap m=BitmapFactory.decodeFile(path);
		return m;
	}
}
