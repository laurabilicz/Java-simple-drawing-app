package drawing;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolyLine implements Component {
	private ArrayList<Float> x, y;
	private int numOfPoints;
	
	public PolyLine() {
		x = new ArrayList<>();
		y = new ArrayList<>();
		numOfPoints = 0;
	}
	
	public PolyLine(String s) throws SyntaxErrorException {
		x = new ArrayList<>();
		y = new ArrayList<>();
		numOfPoints = 0;
		Pattern p = Pattern.compile("(-?\\d*\\.{0,1}\\d+) (-?\\d*\\.{0,1}\\d+)");
		Matcher m = p.matcher(s);
		while (m.find()) {
			x.add(Float.parseFloat(m.group(2)));
			y.add(Float.parseFloat(m.group(1)));
			numOfPoints += 1;
		}
		if (numOfPoints < 2) {
			throw new SyntaxErrorException("Not enough points for polyline");
		}
	}
	
	public void write(Writer output) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(output);
		StringBuilder codeBuilder = new StringBuilder("<polyline points=\"");
		codeBuilder.append(getPointX(0)).append(',').append(getPointY(0));
		for (int i = 1; i < getNumberOfPoints(); i++) {
		  codeBuilder.append(' ').append(getPointX(i)).append(',').append(
		      getPointY(i));
		}
		codeBuilder.append("\" style='fill:none;stroke:black;stroke-width:2'/>");
		String code = codeBuilder.toString();
		bufferedWriter.write(code, 0, code.length());
		bufferedWriter.newLine();
		bufferedWriter.flush();
	}

	private int getNumberOfPoints() {
		return numOfPoints;
	}

	private String getPointY(int i) {
		return Float.toString(x.get(i));
	}

	private String getPointX(int i) {
		return Float.toString(y.get(i));
	}

	@Override
	public void translate(float dx, float dy) {
		for (int i=0; i<numOfPoints; i++) {
			x.set(i, x.get(i) + dx);
			y.set(i, y.get(i) + dy);
		}
	}
	
	@Override
	public void flipHorizontal(float axis) {
		for (int i=0; i<numOfPoints; i++) {
			x.set(i, axis - x.get(i));
		}
	}
	
	@Override
	public void flipVertical(float axis) {
		for (int i=0; i<numOfPoints; i++) {
			y.set(i, axis - y.get(i));
		}
	}
}
