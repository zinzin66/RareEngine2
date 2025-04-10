package RareEngine2.GameUtils;
import android.content.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.view.*;
import java.util.*;
import android.widget.*;
import android.app.*;


public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
	public boolean paused = false;
	private Handler h;
	public static final String REMOVE_WATERMARK_PASS = "-watermark";
	private long frames = 0;
	private HandlerThread thread;
	public ArrayList<GameObject> objects = new ArrayList<GameObject>();
	public ArrayList<String> layers = new ArrayList<>();
	private Paint paint;
	private int backgroundcolor,touchmilli;
	private Vector2 touchdist = new Vector2();
	public Vector2 CameraPosition = new Vector2(),touchPosition = new Vector2(),touchPositionui = new Vector2();
	public boolean isDown,isUp,isClick,isDrag;
	private Activity activity;
	private boolean drawWaterMark=true;
	@Override
	public void surfaceCreated(final SurfaceHolder sh)
	{
		thread = new HandlerThread("thread");
		thread.start();
		paint = new Paint();
		layers.add("background");
		layers.add("objects");
		isDown = false;isUp = true;isClick = false;isDrag = false;
		layers.add("ui");
		setBackground(Color.BLACK);
		h= new Handler(thread.getLooper());
		h.post(new Runnable(){

				@Override
				public void run()
				{
					Canvas can = sh.lockCanvas();
					can.drawColor(backgroundcolor);
					update(can);
					if(drawWaterMark){
						drawWaterMark(can);
					}
					if(can != null)sh.unlockCanvasAndPost(can);
					
					h.postDelayed(this,16);
					
				}
			});
	}
	public void drawWaterMark(Canvas can){
		can.save();
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(8);
		float width = paint.measureText("made with RareEngine2");
		can.translate(getWidth()/2-(width/2),getHeight()-80);
		paint.setColor(Color.WHITE);paint.setTextSize(30);paint.setAntiAlias(true);
		
		
		can.drawText("made with RareEngine2",0,0,paint);
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.FILL);
		can.drawText("made with RareEngine2",0,0,paint);
		can.restore();
		
	}
	public void removeWaterMark(String password){
		if(password.equals(GameView.REMOVE_WATERMARK_PASS)){
			drawWaterMark = false;
		}else{
			toast("wrong password",Toast.LENGTH_LONG);
		}
	}
	
	public void findPositions(MotionEvent e){
		touchPositionui.set(e.getX()-(getWidth()/2),-(e.getY()-(getHeight()/2)));
		touchPosition.set(e.getX()-(getWidth()/2)+CameraPosition.getX(),-(e.getY()-(getHeight()/2)+CameraPosition.getY()));
	}
	
	public void setBackground(int color)
	{
		backgroundcolor = color;
	}
	public void onUp(){
		for(GameObject object:objects){
			if(object.getLayer().equals("ui")){
				if(isPointInsideRect(getRectFOfObjectUi(object),touchPositionui)){
					object.isUp = true;
					object.isDown = false;
					object.isDrag = false;
					
				}else{
					allup(object);
				}
			}else{
				if(isPointInsideRect(getRectFOfObject(object),touchPosition)){
					object.isUp = true;
					object.isDown = false;
					object.isDrag = false;
					
				}else{
					allup(object);
				}
			}
		}
	}
	public void allup(GameObject object){
		object.isDown = false;
		object.isDrag = false;
		object.isUp = true;
		object.isClick = false;
	}
	public void onDown(){
		for(GameObject object:objects){
			if(object.getLayer().equals("ui")){
				if(isPointInsideRect(getRectFOfObjectUi(object),touchPositionui)){
					object.isDown = true;
					object.isUp = false;
					object.isClick = false;
					object.touchPosition.set(touchPositionui.getX(),touchPositionui.getY());
				}else{
					allup(object);
				}
			}else{
				if(isPointInsideRect(getRectFOfObject(object),touchPosition)){
					object.isDown = true;
					object.isUp = false;
					object.isClick = false;
					object.touchPosition.set(touchPosition.getX(),touchPosition.getY());
				}else{
					allup(object);
				}
			}
		}
	}
	public void onDrag(){
		for(GameObject object:objects){
			if(object.getLayer().equals("ui")){
				if(isPointInsideRect(getRectFOfObjectUi(object),touchPositionui)){
					object.isDrag=true;
					object.isUp = false;
					object.isClick = false; 
					object.touchPosition.set(touchPositionui.getX(),touchPositionui.getY());
				}else{
					allup(object);
				}
			}else{
				if(isPointInsideRect(getRectFOfObject(object),touchPosition)){
					object.isDrag = true;
					object.isUp = false;
					object.isClick = false;
					object.touchPosition.set(touchPosition.getX(),touchPosition.getY());
				}else{
					allup(object);
				}
			}
		}
	}
	public void onClick(){
		for(GameObject object:objects){
			if(object.getLayer().equals("ui")){
				if(isPointInsideRect(getRectFOfObjectUi(object),touchPositionui)){
					object.isClick = true;
					object.isDrag = false;
					object.isDown = false;
					
				}else{
					allup(object);
				}
			}else{
				if(isPointInsideRect(getRectFOfObject(object),touchPosition)){
					object.isClick =true;
					object.isDrag = false;
					object.isDown = false;
				}else{
					allup(object);
				}
			}
		}
	}
	public Vector2 invertY(Vector2 vec){
		Vector2 vec2 = new Vector2(vec);
		vec2.setY(-vec2.getY());
		return vec2;
	}
	public RectF getRectFWithScaleAndPositionUi(Vector2 p,Vector2 s){
		return new RectF(p.getX()-s.getX()/2,p.getY()-s.getY()/2,p.getX()+s.getX()/2,p.getY()+s.getY()/2);
	}
	public RectF getRectFOfObjectUi(GameObject o){
		return getRectFWithScaleAndPositionUi(o.globalposition,o.globalscale);
	}
	public boolean isPointInsideRect(RectF r,Vector2 point){
		return r.contains(point.getX(),point.getY());
	}
	public RectF getRectFWithScaleAndPosition(Vector2 p,Vector2 s){
		return new RectF(p.getX()-s.getX()/2,p.getY()-s.getY()/2,p.getX()+s.getX()/2,p.getY()+s.getY()/2);
	}
	public RectF getRectFOfObject(GameObject o){
		return getRectFWithScaleAndPosition(o.globalposition,o.globalscale);
	}
	@Override
	public void surfaceChanged(SurfaceHolder sh, int p2, int p3, int p4)
	{
		Canvas can = sh.lockCanvas();
		update(can);
		if(can !=null)sh.unlockCanvasAndPost(can);
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder sh)
	{
		paused=true;
		h.removeCallbacksAndMessages(null);
		thread.quitSafely();
	}
	public void addObject(GameObject o){
		objects.add(o);
	}
	private void update(Canvas canvas){
		frames++;
		
		if(frames == 1){
			start(frames);
		}
		
		for(GameObject object:objects){
			object.calculateGlobals();
			for(String layer : layers){
				if(!object.getLayer().equals("ui")&&layer.equals(object.getLayer()))render(canvas,object);
			}
		}
		for(GameObject object:objects){
			if(object.getLayer().equals("ui"))render(canvas,object);
		}
	}
	public void render(Canvas canvas,GameObject object){
		if(object.isEnabled&&!paused){
			
			for(Component component:object.getComponents()){
				component.update(object,this);
			}
		}
		for(Component component:object.getComponents()){
			if(component instanceof Renderer){
				if(component.isEnabled&&object.isVisible&&object.isEnabled){
					int id=canvas.save();
					Vector2 cam = new Vector2(CameraPosition);
					if(!object.getLayer().equals("ui")){
						canvas.translate((getWidth()/2)+object.globalposition.getX()-cam.getX(),(getHeight()/2)-object.globalposition.getY()-cam.getY());
						canvas.rotate(object.globalrotation);
						((Renderer)component).render(canvas,object,paint,this);
						canvas.restoreToCount(id);
					}else{
						canvas.translate((getWidth()/2)+object.globalposition.getX(),(getHeight()/2)-object.globalposition.getY());
						canvas.rotate(object.globalrotation);
						((Renderer)component).render(canvas,object,paint,this);
						canvas.restoreToCount(id);
					}
				}
			}
		}
	}
	private void start(long frames){
		
	}
	public void toast(String text,int length){
		Toast.makeText(getContext(),text,length).show();
	}
	public void copy(String text){
		ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);

		// Copy the exception message to the clipboard
		if (clipboard != null) {
			android.content.ClipData clip = android.content.ClipData.newPlainText("text", text);
			clipboard.setPrimaryClip(clip);
		}
	}
	public GameView(Context context,Activity activity){
		super(context);
		getHolder().addCallback(this);
		this.activity = activity;
		setFocusable(true);
		setFocusableInTouchMode(true);
		setOnTouchListener(new SurfaceView.OnTouchListener(){

				@Override
				public boolean onTouch(View v, MotionEvent event)
				{
					switch(event.getAction()){
						case MotionEvent.ACTION_DOWN:
							isDown = true;
							isUp = false;
							isClick = false;
							isDrag = false;
							touchmilli = (int) System.currentTimeMillis();
							touchdist.set(event.getX(),-event.getY());
							findPositions(event);
							onDown();
							break;
						case MotionEvent.ACTION_UP:
							isUp = true;
							isDrag = false;

							if(isDown){
								if(((System.currentTimeMillis()-touchmilli)<= 200)&&touchdist!=null){
									if(touchdist.distance(new Vector2(event.getX(),-event.getY()))<=50){
										isClick = true;
										onClick();
									}else isClick =false;
								}else isClick = false;
							}else isClick = false;
							isDown=true;
							touchmilli = 0;
							touchdist.set(0,0);
							onUp();
							break;
						case MotionEvent.ACTION_MOVE:
							isDrag = true;
							isClick = false;
							isUp = false;
							findPositions(event);
							onDrag();
							break;
					}
					return true;
				}
			});
	}
	public Activity getActivity(){
		return activity;
	}
}
