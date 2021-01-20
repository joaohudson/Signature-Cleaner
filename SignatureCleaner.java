import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class SignatureCleaner{
	public static void main(String[] args) throws Exception{
		
		if(args.length == 0){
			System.err.println("Invalid parameters!");
			return;
		}
		
		BufferedImage img = ImageIO.read(new File(args[0]));
		
		WritableRaster wr = img.getRaster();
		Scanner scan = new Scanner(System.in);
		double[] color = new double[4];
		
		System.out.println("Threshould factor: ");
		double threshould = scan.nextDouble();
		
		for(int x = 0; x < img.getWidth(); x++){
			for(int y = 0; y < img.getHeight(); y++){
				
				wr.getPixel(x, y, color);
				double b = (color[0] + color[1] + color[2])/3.;
				if( b > threshould){
					color[0] = color[1] = color[2] = 255f;
				}
				wr.setPixel(x, y, color);
			}
		}
		
		ImageIO.write(img, "png", new File(args.length == 1 ? "out.png" : args[1]));
	}
}