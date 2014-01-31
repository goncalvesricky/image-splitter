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
		
		String[][] duets = new String[10][2];
		duets[0][0] = "Coke"; duets[0][1] = "Pepsi";
		duets[1][0] = "Hector"; duets[1][1] = "Achilles";
		duets[2][0] = "Batman"; duets[2][1] = "Joker";
		duets[3][0] = "Potter"; duets[3][1] = "Voldemort";
		duets[4][0] = "Rocky"; duets[4][1] = "Apollo";
		duets[5][0] = "Sherlock"; duets[5][1] = "Moriarty";
		duets[6][0] = "Mozart"; duets[6][1] = "Salieri";
		duets[7][0] = "Nike"; duets[7][1] = "Adidas";
		duets[8][0] = "Pele"; duets[8][1] = "Maradona";
		duets[9][0] = "Messi"; duets[9][1] = "Ronaldo";
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter message: ");
		String msg = input.nextLine();
		
		final BufferedImage img = ImageIO.read(new URL(
				"http://columbia.edu/~hg2304/blank.png"));
		
		for(int y = 0; y < img.getHeight(); y++) {
			for(int x = 0; x < img.getWidth(); x++) {
				
				if(Math.random() < .33)
					img.setRGB(x, y, Color.BLACK.getRGB());
				
			}
		}
		
		Graphics g = img.getGraphics();
		g.setFont(g.getFont().deriveFont(30f));
		g.setColor(Color.black);
		g.drawString(msg, 20, img.getHeight()/2);
		g.dispose();

		BufferedImage original = img;

		BufferedImage first = new BufferedImage(800, 440, BufferedImage.TYPE_INT_RGB);
		BufferedImage second = new BufferedImage(800, 440, BufferedImage.TYPE_INT_RGB);

		for(int y = 0; y < original.getHeight(); y++) {
			for(int x = 0; x < original.getWidth(); x++) {

				int rand = (int) (Math.random()*2);
				
				if(original.getRGB(x, y) == Color.WHITE.getRGB()) {
					
					if(rand == 0) {
						first.setRGB(x, y, Color.WHITE.getRGB());
						second.setRGB(x, y, Color.WHITE.getRGB());
					} else if(rand == 1) {
						first.setRGB(x, y, Color.BLACK.getRGB());
						second.setRGB(x, y, Color.BLACK.getRGB());
					}
					
				}

				else if(original.getRGB(x, y) == Color.BLACK.getRGB()) {

					if(rand == 0) {
						first.setRGB(x, y, Color.WHITE.getRGB());
						second.setRGB(x, y, Color.BLACK.getRGB());
					} else if(rand == 1) {
						first.setRGB(x, y, Color.BLACK.getRGB());
						second.setRGB(x, y, Color.WHITE.getRGB());
					}
					
				}
				
			}
			
		}
		
		int index = (int) (Math.random()*10);
		
		ImageIO.write(first, "png", new File(duets[index][0] + ".png"));
		ImageIO.write(second, "png", new File(duets[index][1] + ".png"));
		System.out.println("Done! Outputted " + duets[index][0] + ".png and " + duets[index][1] + ".png");

	}
	
	public static void recreateOriginal() throws Exception {
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter first image name: ");
		BufferedImage first = ImageIO.read(new File(in.next()));
		System.out.print("Enter second image name: ");
		BufferedImage second = ImageIO.read(new File(in.next()));
		BufferedImage original = new BufferedImage(800, 440, BufferedImage.TYPE_INT_RGB);
		
		for(int y = 0; y < first.getHeight(); y++) {
			for(int x = 0; x < first.getWidth(); x++) {
				
				if(first.getRGB(x, y) == second.getRGB(x, y)) {
					original.setRGB(x, y, Color.WHITE.getRGB());
				} else {
					original.setRGB(x, y, Color.BLACK.getRGB());
				}
				
			}
		}
		
		ImageIO.write(original, "png", new File("original-recreated.png"));	
		System.out.println("Done! Outputted original-recreated.png");
		
	}
	
}
