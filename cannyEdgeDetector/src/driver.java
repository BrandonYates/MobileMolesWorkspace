import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BufferedImage image = null;
		try {
			System.out.println("inside file retreival try");
			image = ImageIO.read(new File("src/guyfawkesmask.jpg"));

		// Do something with the image.
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		//create the detector
		CannyEdgeDetector detector = new CannyEdgeDetector();
		//adjust its parameters as desired
		detector.setLowThreshold(0.5f);
		detector.setHighThreshold(1f);
		//apply it to an image
		detector.setSourceImage(image);
		detector.process();
		BufferedImage edges = detector.getEdgesImage();
		try {
		    // retrieve image
			System.out.println("inside outputfile writer try");
		    File outputfile = new File("edges.jpg");
		    ImageIO.write(edges, "jpg", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
