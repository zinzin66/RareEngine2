package RareEngine2.GameUtils;
import android.graphics.*;
import java.util.*;
import java.io.*;

public class TextRenderer extends Renderer
{
	public float fontSize = 30;
	public String text="";
	public int textcolor=Color.WHITE;
	public boolean centered = false,isBold = false;
	public Typeface typeface=Typeface.DEFAULT;
	public FontFrom fontfrom = FontFrom.Default;
	public String fontpath="";
	public int fontResourceId=-1;
	
	public enum FontFrom{
		Asset,File,Default,Resource
	}
	@Override
	public void render(Canvas canvas, GameObject object, Paint p, GameView gv)
	{
		super.render(canvas, object, p, gv);
		String[] s =text.split("\n");
		float width = 0;
		float height = 0;
		p.setTextSize(fontSize);
		p.setColor(textcolor);
		p.setAntiAlias(true);
		if(fontfrom==FontFrom.Asset){
			typeface=Typeface.createFromAsset(gv.getActivity().getAssets(),fontpath);
		}else if(fontfrom==FontFrom.File){
			File f = new File(fontpath);
			typeface=typeface.createFromFile(f);
		}else if(fontfrom == FontFrom.Resource){
			typeface=gv.getContext().getResources().getFont(fontResourceId);
		}
		if(isBold){
			Typeface boldFont = Typeface.create(typeface, Typeface.BOLD);
			p.setTypeface(boldFont);
		}else{
			p.setTypeface(typeface);
		}
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
	
	public FontFrom getFontFrom(){
		return fontfrom;
	}
	
	public void setFontFrom(FontFrom fontfrom){
		this.fontfrom=fontfrom;
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
	
	public String getFontPath() {
		return fontpath;
	}

	public void setFontPath(String fontpath) {
		this.fontpath = fontpath;
	}
	
	public int getTextcolor() {
		return textcolor;
	}

	public void setTextcolor(int textcolor) {
		this.textcolor = textcolor;
	}
	
	public int getFontResourceId() {
		return fontResourceId;
	}

	public void setFontResourceId(int fontResourceId) {
		this.fontResourceId = fontResourceId;
	}
	
	public boolean isCentered() {
		return centered;
	}

	public void setCentered(boolean centered) {
		this.centered = centered;
	}
	
	public boolean isBold() {
		return isBold;
	}

	public void setBold(boolean isBold) {
		this.isBold = isBold;
	}
}
