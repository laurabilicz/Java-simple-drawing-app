package drawing;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class Group implements Component {
	private ArrayList<Component> components;
	
	public Group() {
		components = new ArrayList<>();
	}

	@Override
	public void write(Writer output) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(output);
		bufferedWriter.write("<g>");
		bufferedWriter.flush();
		for (int i=0; i<components.size(); i++) {
			components.get(i).write(output);
		}
		bufferedWriter.write("</g>");
		bufferedWriter.flush();

	}

	@Override
	public void translate(float dx, float dy) {
		for (int i=0; i<components.size(); i++) {
			components.get(i).translate(dx, dy);
		}
	}

	@Override
	public void flipHorizontal(float axis) {
		for (int i=0; i<components.size(); i++) {
			components.get(i).flipHorizontal(axis);
		}
	}

	@Override
	public void flipVertical(float axis) {
		for (int i=0; i<components.size(); i++) {
			components.get(i).flipVertical(axis);
		}
	}

	
	public void merge(Group groups) {
		components.add(groups);
	}
	
	public void addComponent(Component c) {
		components.add(c);
	}

}
