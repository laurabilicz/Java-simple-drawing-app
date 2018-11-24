package drawing;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) {
		Picture picture = new Picture();
		try {
			picture.build(new FileReader("example/example.in"));
			picture.write(new OutputStreamWriter(new FileOutputStream("example/out.svg")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SyntaxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
