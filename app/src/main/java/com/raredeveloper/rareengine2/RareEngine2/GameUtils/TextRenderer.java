package RareEngine2.GameUtils;
import android.graphics.*;
import java.util.*;

public class TextRenderer extends Renderer
{
	public float fontSize = 30;
	public String text="";
	public int textcolor=Color.WHITE;
	public boolean centered = false;
	@Override
	public void render(Canvas canvas, GameObject object, Paint p, GameView gv)
	{
		super.render(canvas, object, p, gv);
		String[] s =text.split("\n");
		float width = 0;
		float height = 0;
		p.setTextSize(fontSize);
		p.setColor(textcolor);
		for(String ss:s){
			float measuredWidth = p.measureText(ss);
			float measuredHeight = p.getFontMetrics().descent-p.getFontMetrics().ascent;
			if(width<measuredWidth){
				width=measuredWidth;
			}
			int index = Arrays.asList(s).indexOf(ss);
			height+=measuredHeight;
			if(!centered){
				canvas.drawText(ss,0,index*measuredHeight,p);
			}
		}
		object.setScale(width,height);
		if(!centered)return;
		for(String ss:s){
			float measuredWidth = p.measureText(ss);
			float measuredHeight = p.getFontMetrics().descent-p.getFontMetrics().ascent;
			int index = Arrays.asList(s).indexOf(ss);
			canvas.drawText(ss,(width-measuredWidth)/2,index*measuredHeight,p);
		}
	}
	
	public float getFontSize() {
		return fontSize;
	}

	public void setFontSize(float fontSize) {
		this.fontSize = fontSize;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getTextcolor() {
		return textcolor;
	}

	public void setTextcolor(int textcolor) {
		this.textcolor = textcolor;
	}

	public boolean isCentered() {
		return centered;
	}

	public void setCentered(boolean centered) {
		this.centered = centered;
	}
}
