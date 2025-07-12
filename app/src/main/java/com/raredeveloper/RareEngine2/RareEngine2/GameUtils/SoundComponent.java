package RareEngine2.GameUtils;
import android.media.MediaPlayer;
import java.io.IOException;
import android.app.Activity;

public class SoundComponent extends Component {
	private MediaPlayer player;
	public boolean finished = false;
	private MediaPlayer.OnCompletionListener compl = new MediaPlayer.OnCompletionListener(){
		@Override
		public void onCompletion(MediaPlayer mediaPlayer) {
			finished = true;
		}
	};
	public void start(GameObject o, GameView gv) {
		super.start(o, gv);
		player = new MediaPlayer();
		setListeners();
	}
	private void setListeners(){
		if(player!=null)player.setOnCompletionListener(compl);
	}
	public void setSoundFromFile(String path){
		player = new MediaPlayer();
		try {
			player.setDataSource(path);
			player.prepare();
		} catch (IOException e) {} catch (IllegalStateException e) {} catch (SecurityException e) {} catch (IllegalArgumentException e) {}
		setListeners();
	}
	public void setSoundFromAsset(String path,Activity act){
		player = new MediaPlayer();
		try {
			player.setDataSource(act.getAssets().openFd(path));
			player.prepare();
		} catch (IOException e) {} catch (IllegalStateException e) {} catch (SecurityException e) {} catch (IllegalArgumentException e) {}
		setListeners();
	}
	public void reset() {
		if (player != null) {
			player.stop();
			try {
				player.prepare();
				player.seekTo(0);
			} catch (IOException | IllegalStateException e) {}
		}
	}
	public void setFromSource(int id,GameView gv){
		player = MediaPlayer.create(gv.getContext(),id);
		setListeners();
	}
	public void play(){
		if(player!=null&&!player.isPlaying()){
			player.start();
			finished = false;
		}
	}
	public void playFromStart(){
		if(player!=null&&!player.isPlaying()){
			setToSeconds(0);
			player.start();
			finished = false;
		}
	}
	public void setToSeconds(float seconds){
		if(player!=null)player.seekTo(Math.round(seconds*1000));
	}
	public void stop(){
		if(player.isPlaying()&&player!=null){
			player.stop();
			try {
				player.prepare();
			} catch (IOException e) {} catch (IllegalStateException e) {}
		}
	}
	public void pause(){
		if(player!=null)player.pause();
	}
	public void setLooping(boolean looping){
		if(player!=null)player.setLooping(looping);
	}
	public void setVolume(float right,float left){
		if(player!=null)player.setVolume(left,right);
	}
	@Override
	public void destroy(GameObject o, GameView gv) {
		super.destroy(o, gv);
		if(player!=null)player.release();
	}
}
