import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class main {

	public static void main(String[] args) throws Exception {

		Scanner in = new Scanner(System.in);
		System.out.println("1) Split message\n2) Recreate message");
		System.out.print("Enter number of task you'd like performed: ");
		
		if(in.nextInt() == 1) 
			splitImage();
		else
			recreateOriginal();
		
	}

/*
	public static BufferedImage doubleImage(BufferedImage img) {
		
		AffineTransform scaleTransform = AffineTransform.getScaleInstance(2, 2);
		AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);
	
		return bilinearScaleOp.filter(img, new BufferedImage(2*img.getWidth(), 2*img.getHeight(), img.getType()));
	}
*/
	
	public static void splitImage() throws Exception {
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter message: ");
		String msg = input.nextLine();
		
		final BufferedImage img = ImageIO.read(new URL(
				"http://columbia.edu/~hg2304/blank.png"));

		Graphics g = img.getGraphics();
		g.setFont(g.getFont().deriveFont(30f));
		g.setColor(Color.black);
		g.drawString(msg, 20, img.getHeight()/2);
		g.dispose();

		BufferedImage original = img;

		BufferedImage first = new BufferedImage(800, 440, BufferedImage.TYPE_INT_RGB);
		BufferedImage second = new BufferedImage(800, 440, BufferedImage.TYPE_INT_RGB);

		for(int y = 0; y < original.getHeight(); y = y + 2) {
			for(int x = 0; x < original.getWidth(); x = x + 2) {

				int rand = (int) (Math.random()*2);
				
				if(original.getRGB(x, y) == Color.WHITE.getRGB()) {
					
					if(rand == 0) {
						first.setRGB(x, y, Color.WHITE.getRGB());
						first.setRGB(x+1, y, Color.BLACK.getRGB());
						first.setRGB(x, y+1, Color.BLACK.getRGB());
						first.setRGB(x+1, y+1, Color.WHITE.getRGB());
						second.setRGB(x, y, Color.WHITE.getRGB());
						second.setRGB(x+1, y, Color.BLACK.getRGB());
						second.setRGB(x, y+1, Color.BLACK.getRGB());
						second.setRGB(x+1, y+1, Color.WHITE.getRGB());
					}
					
					else if(rand == 1) {
						first.setRGB(x, y, Color.BLACK.getRGB());
						first.setRGB(x+1, y, Color.WHITE.getRGB());
						first.setRGB(x, y+1, Color.WHITE.getRGB());
						first.setRGB(x+1, y+1, Color.BLACK.getRGB());
						second.setRGB(x, y, Color.BLACK.getRGB());
						second.setRGB(x+1, y, Color.WHITE.getRGB());
						second.setRGB(x, y+1, Color.WHITE.getRGB());
						second.setRGB(x+1, y+1, Color.BLACK.getRGB());						
					}
					
				}

				else if(original.getRGB(x, y) == Color.BLACK.getRGB()) {

					if(rand == 0) {
						first.setRGB(x, y, Color.WHITE.getRGB());
						first.setRGB(x+1, y, Color.BLACK.getRGB());
						first.setRGB(x, y+1, Color.BLACK.getRGB());
						first.setRGB(x+1, y+1, Color.WHITE.getRGB());
						second.setRGB(x, y, Color.BLACK.getRGB());
						second.setRGB(x+1, y, Color.WHITE.getRGB());
						second.setRGB(x, y+1, Color.WHITE.getRGB());
						second.setRGB(x+1, y+1, Color.BLACK.getRGB());						
					}
					
					else if(rand == 1) {
						first.setRGB(x, y, Color.BLACK.getRGB());
						first.setRGB(x+1, y, Color.WHITE.getRGB());
						first.setRGB(x, y+1, Color.WHITE.getRGB());
						first.setRGB(x+1, y+1, Color.BLACK.getRGB());
						second.setRGB(x, y, Color.WHITE.getRGB());
						second.setRGB(x+1, y, Color.BLACK.getRGB());
						second.setRGB(x, y+1, Color.BLACK.getRGB());
						second.setRGB(x+1, y+1, Color.WHITE.getRGB());	
					}
					
				}
				
			}
			
		}
		
		ImageIO.write(first, "png", new File("first.png"));
		ImageIO.write(second, "png", new File("second.png"));
		System.out.println("Done! Outputted first.png and second.png");

	}
	
	public static void recreateOriginal() throws Exception {
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter first image name: ");
		BufferedImage first = ImageIO.read(new File(in.next()));
		System.out.print("Enter second image name: ");
		BufferedImage second = ImageIO.read(new File(in.next()));
		BufferedImage original = new BufferedImage(800, 440, BufferedImage.TYPE_INT_RGB);
		
		for(int y = 0; y < first.getHeight(); y = y + 2) {
			for(int x = 0; x < first.getWidth(); x = x + 2) {
				
				if(first.getRGB(x, y) == second.getRGB(x, y)) {
					original.setRGB(x, y, Color.WHITE.getRGB());
					original.setRGB(x+1, y, Color.WHITE.getRGB());
					original.setRGB(x, y+1, Color.WHITE.getRGB());
					original.setRGB(x+1, y+1, Color.WHITE.getRGB());					
				}
				
				else {
					original.setRGB(x, y, Color.BLACK.getRGB());
					original.setRGB(x+1, y, Color.BLACK.getRGB());
					original.setRGB(x, y+1, Color.BLACK.getRGB());
					original.setRGB(x+1, y+1, Color.BLACK.getRGB());										
				}
				
			}
		}
		
		ImageIO.write(original, "png", new File("original-recreated.png"));	
		System.out.println("Done! Outputted original-recreated.png");
		
	}
	
}
