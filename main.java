import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class main {

	public static void main(String[] args) throws Exception {

		Scanner input = new Scanner(System.in);
		System.out.print("Enter msg: ");
		String msg = input.nextLine();

		final BufferedImage img = ImageIO.read(new URL(
				"http://columbia.edu/~hg2304/blank.png"));
		
		Graphics g = img.getGraphics();
		g.setFont(g.getFont().deriveFont(60f));
		g.setColor(Color.black);
		g.drawString(msg, 20, img.getHeight()/2);
		g.dispose();
		
		ImageIO.write(img, "png", new File("test.png"));
		
	}

}
