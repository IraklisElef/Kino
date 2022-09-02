import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;
	 
	public SpriteSheet(BufferedImage ss) {
		this.image = ss;
	}
	
	public BufferedImage getImage(int width, int height) {
		
		BufferedImage img = image.getSubimage(0, 0, width, height);
		return img;
		
	}
	
}
