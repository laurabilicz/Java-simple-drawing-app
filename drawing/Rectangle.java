package drawing;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rectangle implements Component {
	
	private float height, width, x, y;
	
	public Rectangle(String s) {
		Pattern p = Pattern.compile("(-?\\d*\\.{0,1}\\d+) (-?\\d*\\.{0,1}\\d+) (-?\\d*\\.{0,1}\\d+) (-?\\d*\\.{0,1}\\d+)");
		Matcher m = p.matcher(s);
		if (m.find()) {
			x = Float.parseFloat(m.group(1));
			y = Float.parseFloat(m.group(2));
			width = Float.parseFloat(m.group(3));
			height = Float.parseFloat(m.group(4));
		}
	}
	
	public void write(Writer output) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(output);
		String code = "<rect x='" + getTopLeftX() + "' y='" + getTopLeftY() + "' " + "" 
		+ "width='" + getWidth() + "' height='" + getHeight() + "' " + "stroke='black' " + "fill='black' />";
		bufferedWriter.write(code, 0, code.length());
		bufferedWriter.newLine();
		bufferedWriter.flush();
	}

	private String getHeight() {
		return Float.toString(height);
	}
	
	private String getWidth() {
		return Float.toString(width);
	}

	private String getTopLeftX() {
		return Float.toString(x);
	}
	
	private String getTopLeftY() {
		return Float.toString(y);
	}

	@Override
	public void translate(float dx, float dy) {
		
		System.out.println("translate");
		System.out.println(x + " " + y);
		System.out.println("+" + dx + " " + dy);
		x += dx;
		y += dy;
		System.out.println(x + " " + y);
		
	}

	@Override
	public void flipHorizontal(float axis) {
		x = (axis - x) + axis;
	}

	@Override
	public void flipVertical(float axis) {
		y = (axis - y) + axis;
		
	}
}
