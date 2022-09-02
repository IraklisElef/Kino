import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.SwingUtilities;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class HUD extends MouseAdapter implements MouseMotionListener {
	
	RandomNumbers randomNumbers = new RandomNumbers();
	Random rand = new Random();
	
	private Color yellowCircle = new Color(255,227,111);
	private Color greenBorder = new Color(119,200,111);
	
	public static int totalNumbers[] = new int[19];
	public static int kinoNumber;

	public static int numbersX[] = new int[80];
	public static int numbersY[] = new int[80];
	
	private boolean[] mouseOver = new boolean[3];
	
	private BufferedImage kinoLogo;
	
	public HUD() {
		
		SpriteSheet ss = new SpriteSheet(Kino.kinoLogo);
		kinoLogo = ss.getImage(373, 190);
		
		kinoLogo = resize(kinoLogo, Kino.WIDTH / 8, Kino.HEIGHT / 13);
		
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g){
		
		drawBorder(g);
		drawButtons(g);
		drawDateTime(g);
		
		if(Kino.programState == STATE.Generate_Fast && kinoNumber != 0)
			drawFastCircles(totalNumbers, g);
		else if(Kino.programState == STATE.Generate_Slow)
			drawSlowCircles(totalNumbers, g);
		
		drawNumbers(g);
		
		g.drawImage(kinoLogo, (int) (Kino.WIDTH / 2) - kinoLogo.getWidth() / 2, (int) (Kino.HEIGHT / 250), null);
		
	}
	
	private void drawBorder(Graphics g) {
		
		g.setColor(greenBorder);
		g.fillRect(0, 0, Kino.WIDTH, Kino.HEIGHT / 12);
		g.fillRect(0, 0, Kino.WIDTH / 60, (int) (Kino.HEIGHT / 1.4));
		g.fillRect((int) (Kino.WIDTH - Kino.WIDTH / 45), 0, Kino.WIDTH / 60, (int) (Kino.HEIGHT / 1.4));
		g.fillRect(0, (int) (Kino.HEIGHT / 1.4), Kino.WIDTH, (int) (Kino.HEIGHT / 45));
		
	}
	
	private void drawButtons(Graphics g) {
		
		g.setColor(greenBorder);
		
		g.drawRect((int) (Kino.WIDTH / 20), (int) (Kino.HEIGHT / 1.280), (int) (Kino.WIDTH / 4), (int) (Kino.HEIGHT / 8.5));
		g.drawRect((int) (Kino.WIDTH / 2.7), (int) (Kino.HEIGHT / 1.280), (int) (Kino.WIDTH / 4), (int) (Kino.HEIGHT / 8.5));
		g.drawRect((int) (Kino.WIDTH / 1.45), (int) (Kino.HEIGHT / 1.280), (int) (Kino.WIDTH / 4), (int) (Kino.HEIGHT / 8.5));

		g.setFont(new Font("arial", 1, Kino.WIDTH / 15));
		
		if(mouseOver[0])
			g.setColor(Color.LIGHT_GRAY.darker().darker());
		else
			g.setColor(Color.LIGHT_GRAY);
		g.drawString("Fast", (int) (Kino.WIDTH / 20) + (Kino.WIDTH / 4) / 2 - g.getFontMetrics().stringWidth("Fast") / 2, (int) (Kino.HEIGHT / 1.15));
		
		if(mouseOver[1])
			g.setColor(Color.LIGHT_GRAY.darker().darker());
		else
			g.setColor(Color.LIGHT_GRAY);
		g.drawString("Slow", (int) (Kino.WIDTH / 2.7) + (Kino.WIDTH / 4) / 2 - g.getFontMetrics().stringWidth("Slow") / 2, (int) (Kino.HEIGHT / 1.15));
		
		if(mouseOver[2])
			g.setColor(Color.LIGHT_GRAY.darker().darker());
		else
			g.setColor(Color.LIGHT_GRAY);
		g.drawString("Exit", (int) (Kino.WIDTH / 1.45) + (Kino.WIDTH / 4) / 2 - g.getFontMetrics().stringWidth("Exit") / 2, (int) (Kino.HEIGHT / 1.15));
		
	}
	
	private void drawDateTime(Graphics g) {
		
		Calendar time = Calendar.getInstance();
        SimpleDateFormat timeSdf = new SimpleDateFormat("HH:mm:ss");
        
        g.setFont(new Font("Arial Hebrew", 1, Kino.WIDTH / 22));
        g.setColor(Color.WHITE);
        
        g.drawString(timeSdf.format(time.getTime()), (int) (Kino.WIDTH / 1.4), (int) (Kino.HEIGHT / 15));
		
        Calendar date = Calendar.getInstance();
        SimpleDateFormat dateSdf = new SimpleDateFormat("dd/MM/yyyy");
        
        g.drawString(dateSdf.format(date.getTime()), (int) (Kino.WIDTH / 12), (int) (Kino.HEIGHT / 15));
        
	}
	
	public void drawFastCircles(int totalNumbers[], Graphics g) {
		
		g.setColor(yellowCircle);
		
		for(int i = 0; i < totalNumbers.length; i++) {
			
			if(kinoNumber == 0)
				break;
			
			g.fillOval(numbersX[totalNumbers[i] - 1] - (int) (Kino.WIDTH / 80), numbersY[totalNumbers[i] - 1] - (int) (Kino.HEIGHT / 18), Kino.WIDTH / 14, Kino.HEIGHT / 14);
			
		}
			
		if(kinoNumber != 0) {
			
			g.setColor(Color.RED);
			g.fillOval(numbersX[kinoNumber - 1] - (int) (Kino.WIDTH / 80), numbersY[kinoNumber - 1] - (int) (Kino.HEIGHT / 18), Kino.WIDTH / 14, Kino.HEIGHT / 14);
			
		}
		
	}
	
	public void drawSlowCircles(int totalNumbers[], Graphics g){
		
		g.setColor(yellowCircle);
		
		int times = rand.nextInt(4) + 1;
		
		for(int i = 0; i < totalNumbers.length; i++) {
			
			for(int j = 1; j <= times; j++) {
			
				//TODO

			}
			
		}
		
	}
	
	private void drawNumbers(Graphics g) {
		
		int counter = 1;
		int xNum = Kino.WIDTH / 25, yNum = Kino.HEIGHT / 6;
		
		g.setFont(new Font("arial", 1, Kino.WIDTH / 25));
		
		for(int i = 1; i <= 8; i++) {
			
			for(int j = 1; j <= 10; j++) {
				
				if(Kino.programState == STATE.Generate_Fast || Kino.programState == STATE.Menu) {
					
					if(selectedNumber(totalNumbers, kinoNumber, counter))
						g.setColor(Color.BLACK);
					else
						g.setColor(Color.LIGHT_GRAY);
					
				}
				else if(Kino.programState == STATE.Generate_Slow) {
					
					g.setColor(Color.LIGHT_GRAY);
					
				}
				
				if(counter < 10)
					g.drawString(Integer.toString(counter), xNum + Kino.WIDTH / 90, yNum);
				else
					g.drawString(Integer.toString(counter), xNum, yNum);
				
				numbersX[counter - 1] = xNum;
				numbersY[counter - 1] = yNum;
				
				xNum += Kino.WIDTH / 10.3;
				
				counter++;
				
			}
			
			yNum += Kino.HEIGHT / 13.5;
			xNum = Kino.WIDTH / 25;
			
		}
		
	}
	
	private boolean selectedNumber(int totalNumbers[], int kinoNumber, int counter) {
		
		for(int i = 0; i < totalNumbers.length; i++) {
			
			if(totalNumbers[i] == counter || kinoNumber == counter)
				return true;
			
		}
		
		return false;
		
	}
	
	public boolean mouseOver(int mx, int my, double x, double y, double width, double height) {
		
		if(mx > x && mx < x + width) {
			 if(my > y && my < y + height)
				 return true;
			 
			 return false;
		}
		
		return false;
		
	}
	
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		if(SwingUtilities.isLeftMouseButton(e) && Kino.programState != STATE.Number_Selection) {
			
			if(mouseOver(mx, my, Kino.WIDTH / 20, Kino.HEIGHT / 1.280, Kino.WIDTH / 4, Kino.HEIGHT / 8.5)) {
				
				Kino.programState = STATE.Generate_Fast;
			
				totalNumbers = randomNumbers.randomNumbers(totalNumbers);
				kinoNumber = randomNumbers.randomKino(totalNumbers);
				
			}
			
			/*if(mouseOver(mx, my, Kino.WIDTH / 2.7, Kino.HEIGHT / 1.280, Kino.WIDTH / 4, Kino.HEIGHT / 8.5)) {
				
				Kino.programState = STATE.Generate_Slow;
		
				totalNumbers = randomNumbers.randomNumbers(totalNumbers);
				kinoNumber = randomNumbers.randomKino(totalNumbers);
				
			}*/
			
			if(mouseOver(mx, my, Kino.WIDTH / 1.45, Kino.HEIGHT / 1.280, Kino.WIDTH / 4, Kino.HEIGHT / 8.5))
				System.exit(0);
			
		}
		
		else if(SwingUtilities.isRightMouseButton(e)) {
			
			if(Kino.programState != STATE.Number_Selection)
				Kino.programState = STATE.Number_Selection;
			else if(Kino.programState == STATE.Number_Selection) {
				
				if(totalNumbers[0] == 0)
					Kino.programState = STATE.Menu;
				else
					Kino.programState = STATE.Generate_Fast;
				
			}
				
			
		}
		else if(SwingUtilities.isMiddleMouseButton(e) && Kino.programState != STATE.Number_Selection)
			reset();
			
		
	}
	
	public void mouseMoved(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		mouseOver[0] = mouseOver(mx, my, Kino.WIDTH / 20, Kino.HEIGHT / 1.280, Kino.WIDTH / 4, Kino.HEIGHT / 8.5);
		mouseOver[1] = mouseOver(mx, my, Kino.WIDTH / 2.7, Kino.HEIGHT / 1.280, Kino.WIDTH / 4, Kino.HEIGHT / 8.5);
		mouseOver[2] = mouseOver(mx, my, Kino.WIDTH / 1.45, Kino.HEIGHT / 1.280, Kino.WIDTH / 4, Kino.HEIGHT / 8.5);
		
	}
	
	public static BufferedImage resize(BufferedImage image, int newW, int newH) { 
		
		int type = 0;
        type = image.getType() == 0? BufferedImage.TYPE_INT_ARGB : image.getType();
        BufferedImage resizedImage = new BufferedImage(newW, newH, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, newW, newH, null);
        g.dispose();
        
        return resizedImage;
	    
	}
	
	private void reset() {
		
		Kino.programState = STATE.Menu;
		
		kinoNumber = 0;
		
		for(int i = 0; i < totalNumbers.length; i++)
			totalNumbers[i] = 0;
		
	}
	
}
