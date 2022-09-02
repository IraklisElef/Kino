import javax.swing.JFrame;

public class Window {

	public Window(int width, int height, String title, Kino kino) {
		
		JFrame jf = new JFrame(title);
		
		jf.setSize(width, height);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(kino);
		
		jf.setVisible(true);
		
	}
	
}