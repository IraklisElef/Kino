import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Kino extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 800, HEIGHT = WIDTH / 12 * 9;
	
	private boolean running = false;
	
	private Thread thread;
	public static HUD hud;
	public static Ticket ticket;
	
	public static BufferedImage kinoLogo;
	
	public static STATE programState = STATE.Menu;
	
	public Kino() throws IOException {
		
		loadSprites();
		initObjects();
		
		addMouseListener(hud);
		addMouseMotionListener(hud);
		addMouseListener(ticket);
		addMouseMotionListener(ticket);
		
		addKeyListener(new KeyInput());
		
		this.start();
		
	}
	
	public synchronized void start() {
		
		thread = new Thread(this);
		thread.start();
		running = true;
		
	}
	
	public synchronized void stop() {
		
		try{
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void run() {

		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		
		while(running) {
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now; 
			
			while(delta >= 1) {
				tick();
				delta--;
			}
			
			if(running)
				render();

			if(System.currentTimeMillis() - timer > 1000)
				timer += 1000;
			
		}
		
		stop();
		
	}
	
	private void tick() {
		
		hud.tick();

	}
	
	private void render(){
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		hud.render(g);
		ticket.render(g);
	
		g.dispose();
		bs.show();
		
	}
	
	//Load all game sprite
	private void loadSprites() {
			
		BufferedImageLoader loader = new BufferedImageLoader();
		
		kinoLogo = loader.loadImage("/Kino_Logo.png");
			
	}
	
	//Initialize all game objects
	private void initObjects() {
		
		hud = new HUD();
		ticket = new Ticket();
		
		new Window(WIDTH, HEIGHT, "KINO", this);
			
	}
	
	public static void main(String[] args) throws IOException {
		
		new Kino();
		
	}
	
}
