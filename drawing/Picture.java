package drawing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Picture {
	private Stack<Group> groups;
	
	public Picture() {
		groups = new Stack<>();
		groups.push(new Group());
	}
	
	public void build (Reader input) throws IOException, SyntaxErrorException {
		ArrayList<Pattern> patterns = new ArrayList<>();
		patterns.add(Pattern.compile("^add_line_segments"));
		patterns.add(Pattern.compile("^add_circle"));
		patterns.add(Pattern.compile("^add_rectangle"));
		patterns.add(Pattern.compile("^new_group"));
		patterns.add(Pattern.compile("^translate"));
		patterns.add(Pattern.compile("^flip_vertical"));
		patterns.add(Pattern.compile("^flip_horizontal"));
		patterns.add(Pattern.compile("^merge"));
		try (BufferedReader br = new BufferedReader(input)) {
		    String line;
		    
		    while ((line = br.readLine()) != null) {
		       for (int i=0; i<patterns.size(); i++) {
		    	   Matcher m = patterns.get(i).matcher(line);
		    	   if (m.find()) {
		    		   switch (i) {
							case 0:
								groups.peek().addComponent(new PolyLine(line));
								break;
							case 1:
								groups.peek().addComponent(new Circle(line));
								break;
							case 2:
								groups.peek().addComponent(new Rectangle(line));
								break;
							case 3:
								groups.push(new Group());
								break;
							case 4:
								Pattern p = Pattern.compile("^translate ([0-9]+) ([0-9]+)");
								Matcher m0 = p.matcher(line);
								if (m0.find()) {
									groups.peek().translate(Integer.parseInt(m0.group(1)), Integer.parseInt(m0.group(2)));
								}
								
								break;
							case 5:
								Pattern p1 = Pattern.compile("^flip_vertical ([0-9]+)");
								Matcher m1 = p1.matcher(line);
								if (m1.find()) {
									groups.peek().flipVertical(Integer.parseInt(m1.group(1)));
								}
								break;
							case 6:
								Pattern p2 = Pattern.compile("^flip_horizontal ([0-9]+)");
								Matcher m2 = p2.matcher(line);
								if (m2.find()) {
									groups.peek().flipHorizontal(Integer.parseInt(m2.group(1)));
								}
								break;
							case 7:
								if (groups.size() <= 1) {
									throw new SyntaxErrorException("Need atleast 2 groups to merge" + line);
								}
								Group g = groups.peek();
								groups.pop();
								groups.peek().merge(g);
								
								break;
							default:
								break;
		    		   }
		    		   break;
		    	   }
		       }
		    }
		}
	}
	
	public void write (Writer output) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(output);

		bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		bufferedWriter.flush();
		bufferedWriter.write("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">");
		bufferedWriter.flush();
		while(!groups.isEmpty()) {
			groups.peek().write(output);
			groups.pop();
		}
		bufferedWriter.write("</svg>");
		bufferedWriter.flush();
	}
}
