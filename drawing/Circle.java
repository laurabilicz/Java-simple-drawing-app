package drawing;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Circle implements Component {
	private float x, y, r;
	
	public Circle(String s) {
		x = 0;
		y = 0;
		r = 0;
		Pattern p = Pattern.compile("(-?\\d*\\.{0,1}\\d+) (-?\\d*\\.{0,1}\\d+) (-?\\d*\\.{0,1}\\d+)");
		Matcher m = p.matcher(s);
		if (m.find()) {
			x = Float.parseFloat(m.group(1));
			y = Float.parseFloat(m.group(2));
			r = Float.parseFloat(m.group(3));
		}
	}
	
	public void write(Writer output) throws IOException {
	    BufferedWriter bufferedWriter = new BufferedWriter(output);
	    String code = "<circle cx='" + getCenterX() + "' cy='" + getCenterY() + "' r='" + getRadius() + "' stroke='black' fill='black' />";
	    bufferedWriter.write(code, 0, code.length());
	    bufferedWriter.newLine();
	    bufferedWriter.flush();
	}

	private String getRadius() {
		return Float.toString(r);
	}

	private String getCenterY() {
		return Float.toString(y);
	}

	private String getCenterX() {
		return Float.toString(x);
	}
	

	@Override
	public void flipHorizontal(float axis) {
		x += axis - x;
		
	}

	@Override
	public void flipVertical(float axis) {
		y += axis - y;
		
	}

	@Override
	public void translate(float dx, float dy) {
		x += dx;
		y += dy;
		
	}
}
