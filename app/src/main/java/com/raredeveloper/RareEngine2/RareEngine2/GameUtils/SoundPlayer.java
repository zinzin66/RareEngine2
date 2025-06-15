package RareEngine2.GameUtils;
import android.media.MediaPlayer;
import android.content.Context;
import java.io.IOException;
import android.content.res.AssetFileDescriptor;

public class SoundPlayer extends Component {    
    public MediaPlayer soundplayer;
    public boolean playAtStart=false,looping = false;
    public float volumeRight=100,volumeLeft=100;
    private Context ct;
    @Override
    public void start(GameObject o, GameView gv) {
        super.start(o, gv);
        ct = gv.getContext();
        if (playAtStart)play();
    }
    public void destroy() {
        if (soundplayer != null) {
            soundplayer.release();
            soundplayer = null;
            ct = null;
        }
    }
    @Override
    public void update(GameObject o, GameView gv) {
        super.update(o, gv);
        ct = gv.getContext();
        if (soundplayer != null) {
            soundplayer.setLooping(looping);
            volumeLeft = Math.max(0, Math.min(volumeLeft, 100));
            volumeRight = Math.max(0, Math.min(volumeRight, 100));
            soundplayer.setVolume(volumeLeft/100, volumeRight/100);
        }
    }
    public void play() {
        if (soundplayer != null)soundplayer.start();
    }
    public void pause() {
        if (soundplayer != null && soundplayer.isPlaying())soundplayer.pause();
    }
    public void resume() {
        if (soundplayer != null)soundplayer.start();
    }
    public void stop() {
        if (soundplayer != null && soundplayer.isPlaying())soundplayer.stop();
    }
    public void setAudioFromRes(int id) throws Exception {
        if (ct != null) {
            soundplayer = MediaPlayer.create(ct, id);
        } else {
            throw new Exception("Component not added to an object");
        }
    }
    public void setAudioFromPath(String path)throws IOException,Exception {
        if (ct != null) {
            soundplayer = new MediaPlayer();
            soundplayer.setDataSource(path);
            soundplayer.prepare();
        } else {
            throw new Exception("Component not added to an object");
        }
    }
    public void setAudioFromAsset(String path)throws IOException,Exception {
        if (ct != null) {
            soundplayer = new MediaPlayer();
            AssetFileDescriptor atp = ct.getAssets().openFd(path);
            soundplayer.setDataSource(atp.getFileDescriptor(), atp.getStartOffset(), atp.getLength());
            soundplayer.prepare();
        } else {
            throw new Exception("Component not added to an object");
        }
    }
}
