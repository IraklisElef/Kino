import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

public class Ticket extends MouseAdapter implements MouseMotionListener {

	private int mx, my;
	
	private int[][][] numbersX = new int[6][8][10];
	private int[][][] numbersY = new int[6][8][10];
	
	private int[][] gameSelectionX = new int[6][12];
	private int[][] gameSelectionY = new int[6][12];
	
	private int[] kinoSelectionX = new int[6];
	private int[] kinoSelectionY = new int[6];
	
	private boolean[] hoverKino = new boolean[6];
	private boolean[] selectedKino = new boolean[6];
	
	private boolean[][] hoverGame = new boolean[6][12];
	private boolean[][] selectedGame = new boolean[6][12];
	
	private boolean[][][] hoverNumber = new boolean[6][8][10];
	private boolean[][][] selectedNumber = new boolean[6][8][10];
	private boolean[][][] selectedKinoNumber = new boolean[6][8][10];
	
	private int[] totalNumbers = new int[6];
	private int[] kinoNumber = new int[6];
	
	public Ticket() {
		
		for(int i = 0; i < kinoNumber.length; i++)
			kinoNumber[i] = 2;
		
	}
	
	public void render(Graphics g) {
		
		if(Kino.programState == STATE.Number_Selection) {
			
			g.setColor(Color.ORANGE);
			g.fillRect(0, 0, Kino.WIDTH, Kino.HEIGHT);
			
			drawSquares(g);
			drawText(g);
			drawWinnings(g);
			drawGameSelection(g);
			drawNumberSelection(g);
			drawMouseText(g);
			
		}
		
	}
	
	private void drawSquares(Graphics g) {
		
		g.setColor(Color.RED);
		
		g.drawRect(0, 0, Kino.WIDTH / 3, Kino.HEIGHT / 2 - 30);
		g.drawRect(Kino.WIDTH / 3, 0, Kino.WIDTH / 3, Kino.HEIGHT / 2 - 30);
		g.drawRect((Kino.WIDTH / 3) * 2, 0, Kino.WIDTH / 3 - 5, Kino.HEIGHT / 2 - 30);
		
		g.drawRect(0, Kino.HEIGHT / 2 - 10, Kino.WIDTH / 3, Kino.HEIGHT / 2 - 20);
		g.drawRect(Kino.WIDTH / 3, Kino.HEIGHT / 2 - 10, Kino.WIDTH / 3, Kino.HEIGHT / 2 - 20);
		g.drawRect((Kino.WIDTH / 3) * 2, Kino.HEIGHT / 2 - 10, Kino.WIDTH / 3 - 5, Kino.HEIGHT / 2 - 20);
		
	}
	
	private void drawText(Graphics g) {
		
		g.setColor(Color.RED);
		
		//Game option
		g.fillRect(10, 10, Kino.WIDTH / 3 - 20, 15);
		g.fillRect(Kino.WIDTH / 3 + 10, 10, Kino.WIDTH / 3 - 20, 15);
		g.fillRect((Kino.WIDTH / 3) * 2 + 10, 10, Kino.WIDTH / 3 - 20, 15);
		
		g.fillRect(10, Kino.HEIGHT / 2, Kino.WIDTH / 3 - 20, 15);
		g.fillRect(Kino.WIDTH / 3 + 10, Kino.HEIGHT / 2, Kino.WIDTH / 3 - 20, 15);
		g.fillRect((Kino.WIDTH / 3) * 2 + 10, Kino.HEIGHT / 2, Kino.WIDTH / 3 - 20, 15);
		
		//Choose numbers
		g.fillRect(10, 70, Kino.WIDTH / 3 - 20, 15);
		g.fillRect(Kino.WIDTH / 3 + 10, 70, Kino.WIDTH / 3 - 20, 15);
		g.fillRect((Kino.WIDTH / 3) * 2 + 10, 70, Kino.WIDTH / 3 - 20, 15);
		
		g.fillRect(10, Kino.HEIGHT / 2 + 70, Kino.WIDTH / 3 - 20, 15);
		g.fillRect(Kino.WIDTH / 3 + 10, Kino.HEIGHT / 2 + 70, Kino.WIDTH / 3 - 20, 15);
		g.fillRect((Kino.WIDTH / 3) * 2 + 10, Kino.HEIGHT / 2 + 70, Kino.WIDTH / 3 - 20, 15);
		
		//Choose Kino number
		
		Color hoverColor = new Color(255, 0, 0, 100);
		
		kinoSelectionX[0] = 35;
		kinoSelectionY[0] = 35;
		
		kinoSelectionX[1] = Kino.WIDTH / 3 + 35;
		kinoSelectionY[1] = 35;
		
		kinoSelectionX[2] = Kino.WIDTH / 3 * 2 + 35;
		kinoSelectionY[2] = 35;
		
		kinoSelectionX[3] = 35;
		kinoSelectionY[3] = Kino.HEIGHT / 2 + 35;
		
		kinoSelectionX[4] = Kino.WIDTH / 3 + 35;
		kinoSelectionY[4] = Kino.HEIGHT / 2 + 35;
		
		kinoSelectionX[5] = Kino.WIDTH / 3 * 2 + 35;
		kinoSelectionY[5] = Kino.HEIGHT / 2 + 35;
		
		for(int i = 0; i < kinoSelectionX.length; i++) {
			
			if(selectedKino[i]) {
				
				g.setColor(Color.RED);
				g.fillRect(kinoSelectionX[i], kinoSelectionY[i], 20, 20);
				
			}
			else if(hoverKino[i]) {
				
				g.setColor(hoverColor);
				g.fillRect(kinoSelectionX[i], kinoSelectionY[i], 20, 20);
				
			}
			else {
				
				g.setColor(Color.RED);
				g.drawRect(kinoSelectionX[i], kinoSelectionY[i], 20, 20);
				
			}
			
		}
		
		//Text
		g.setColor(Color.WHITE);
		g.setFont(new Font("Impact", 4, 15));
		
		g.drawString("GAME SELECTION", ((Kino.WIDTH / 3) / 2 - (g.getFontMetrics().stringWidth("GAME SELECTION") / 2)), 23);
		g.drawString("GAME SELECTION", ((Kino.WIDTH / 3) + (Kino.WIDTH / 3) / 2 - (g.getFontMetrics().stringWidth("GAME SELECTION") / 2)), 23);
		g.drawString("GAME SELECTION", ((Kino.WIDTH / 3) * 2 + (Kino.WIDTH / 3) / 2 - (g.getFontMetrics().stringWidth("GAME SELECTION") / 2)), 23);
		
		g.drawString("GAME SELECTION", ((Kino.WIDTH / 3) / 2 - (g.getFontMetrics().stringWidth("GAME SELECTION") / 2)), Kino.HEIGHT / 2 + 13);
		g.drawString("GAME SELECTION", ((Kino.WIDTH / 3) + (Kino.WIDTH / 3) / 2 - (g.getFontMetrics().stringWidth("GAME SELECTION") / 2)), Kino.HEIGHT / 2 + 13);
		g.drawString("GAME SELECTION", ((Kino.WIDTH / 3) * 2 + (Kino.WIDTH / 3) / 2 - (g.getFontMetrics().stringWidth("GAME SELECTION") / 2)), Kino.HEIGHT / 2 + 13);
		
		g.drawString("NUMBER SELECTION", ((Kino.WIDTH / 3) / 2 - (g.getFontMetrics().stringWidth("NUMBER SELECTION") / 2)), 23 + 60);
		g.drawString("NUMBER SELECTION", ((Kino.WIDTH / 3) + (Kino.WIDTH / 3) / 2 - (g.getFontMetrics().stringWidth("NUMBER SELECTION") / 2)), 23 + 60);
		g.drawString("NUMBER SELECTION", ((Kino.WIDTH / 3) * 2 + (Kino.WIDTH / 3) / 2 - (g.getFontMetrics().stringWidth("NUMBER SELECTION") / 2)), 23 + 60);
		
		g.drawString("NUMBER SELECTION", ((Kino.WIDTH / 3) / 2 - (g.getFontMetrics().stringWidth("NUMBER SELECTION") / 2)), Kino.HEIGHT / 2 + 13 + 70);
		g.drawString("NUMBER SELECTION", ((Kino.WIDTH / 3) + (Kino.WIDTH / 3) / 2 - (g.getFontMetrics().stringWidth("NUMBER SELECTION") / 2)), Kino.HEIGHT / 2 + 13 + 70);
		g.drawString("NUMBER SELECTION", ((Kino.WIDTH / 3) * 2 + (Kino.WIDTH / 3) / 2 - (g.getFontMetrics().stringWidth("NUMBER SELECTION") / 2)), Kino.HEIGHT / 2 + 13 + 70);
		
		g.setColor(Color.RED);
		for(int i = 0; i < kinoSelectionX.length; i++) {
			
			g.drawString("Kino", kinoSelectionX[i] - 30, kinoSelectionY[i] + 15);
			g.drawString("Bonus", kinoSelectionX[i] - 30, kinoSelectionY[i] + 33);

		}
		
	}
	
	private void drawMouseText(Graphics g) {
		
		int[] x = new int[6];
		int[] y = new int[6];
		
		int[] width = new int[6];
		int[] height = new int[6];
		
		x[0] = 0;
		x[1] = Kino.WIDTH / 3;
		x[2] = Kino.WIDTH / 3 * 2;
		x[3] = 0;
		x[4] = Kino.WIDTH / 3;
		x[5] = Kino.WIDTH / 3 * 2;
		
		y[0] = 0;
		y[1] = 0;
		y[2] = 0;
		y[3] = Kino.HEIGHT / 2 - 10;
		y[4] = Kino.HEIGHT / 2 - 10;
		y[5] = Kino.HEIGHT / 2 - 10;
		
		width[0] = Kino.WIDTH / 3;
		width[1] = Kino.WIDTH / 3;
		width[2] = Kino.WIDTH / 3 - 5;
		width[3] = Kino.WIDTH / 3;
		width[4] = Kino.WIDTH / 3;
		width[5] = Kino.WIDTH / 3 - 5;
		
		height[0] = Kino.HEIGHT / 2 - 30;
		height[1] = Kino.HEIGHT / 2 - 30;
		height[2] = Kino.HEIGHT / 2 - 30;
		height[3] = Kino.HEIGHT / 2 - 20;
		height[4] = Kino.HEIGHT / 2 - 20;
		height[5] = Kino.HEIGHT / 2 - 20;
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", 1, 13));
		
		for(int i = 0; i < 6; i++) {
			
			if(isSelectedGame(i)) {
				
				if(Kino.hud.mouseOver(mx, my, x[i], y[i], width[i], height[i])) {
					
					if(!selectedKino[i] && totalNumbers[i] > 1)
						g.drawString("Select Number", mx + 20, my - 20);
					else if(selectedKino[i]) {
						
						if(totalNumbers[i] > 1)
							g.drawString("Select Number", mx + 20, my - 20);
						else if(kinoNumber[i] > 1) {
							g.setColor(Color.BLUE);
							g.drawString("Select Kino", mx + 20, my - 20);
						}
							
						
					}
					
				}
				
			}
			
		}
		
	}
	
	private void drawWinnings(Graphics g) {
		
		if(!selectedKino[0]) {
			
			if(selectedGame[0][11]) {
				
				
				
			}
			
		}
		
	}
	
	private void drawGameSelection(Graphics g) {
		
		int ovalX = 0, ovalY;
		
		for(int i = 0; i < 6; i++) {
			
			if(i <= 2)
				ovalY = 30;
			else
				ovalY = Kino.HEIGHT / 2 + 25;
			
			if(i == 0 || i == 3)
				ovalX = ((Kino.WIDTH / 3) / 2) - 70; 
			else if(i == 1 || i == 4)
				ovalX = ((Kino.WIDTH / 3 + (Kino.WIDTH / 3) / 2)) - 70;
			else if(i == 2 || i == 5)
				ovalX = ((Kino.WIDTH / 3 * 2 + (Kino.WIDTH / 3) / 2)) - 70;
			
			for(int j = 0; j < 12; j++) {
				
				if(selectedGame[i][j])
					g.setColor(Color.BLACK);
				else if(hoverGame[i][j])
					g.setColor(Color.WHITE.darker());
				else
					g.setColor(Color.WHITE);
				
				g.fillOval(ovalX, ovalY, 15, 15);
				
				gameSelectionX[i][j] = ovalX;
				gameSelectionY[i][j] = ovalY;
				
				ovalX += 25;
				
				if(j == 5) {
					
					if(i == 0 || i == 3)
						ovalX = ((Kino.WIDTH / 3) / 2) - 70; 
					else if(i == 1 || i == 4)
						ovalX = ((Kino.WIDTH / 3 + (Kino.WIDTH / 3) / 2)) - 70;
					else if(i == 2 || i == 5)
						ovalX = ((Kino.WIDTH / 3 * 2 + (Kino.WIDTH / 3) / 2)) - 70;
					
					if(i <= 2)
						ovalY = 50;
					else
						ovalY = Kino.HEIGHT / 2 + 45;
					
				}		
				
			}
			
		}
		
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", 1, 12));
		
		for(int i = 0; i < 6; i++) {
		
			int temp = 12;
			
			for(int j = 0; j < 12; j++) {
				
				g.drawString(Integer.toString(temp), (gameSelectionX[i][j] + 15 / 2 - g.getFontMetrics().stringWidth(Integer.toString(temp)) / 2), gameSelectionY[i][j] + 12);
				temp--;
				
			}

		}

	}
	
	private void drawNumberSelection(Graphics g) {
		
		Color rectColor = new Color(255, 255, 255, 175);
		
		int rectX = 0, rectY;
		
		for(int i = 0; i < 6; i++) {
			
			int temp = 1;
			if(i <= 2)
				rectY = 85;
			else
				rectY = Kino.HEIGHT / 2 + 85;
			
			if(i == 0 || i == 3)
				rectX = 8;
			else if(i == 1 || i == 4)
				rectX = Kino.WIDTH / 3 + 8;
			else if(i == 2 || i == 5)
				rectX = Kino.WIDTH / 3 * 2 + 8;
			
			for(int j = 0; j < 8; j++) {
			
				for(int k = 0; k < 10; k++) {
					
					if(numberMatch(temp, false) && selectedNumber[i][j][k])
						g.setColor(Color.GREEN);
					else if(numberMatch(temp, true) && selectedKinoNumber[i][j][k])
						g.setColor(Color.BLUE);
					else if(selectedNumber[i][j][k] || selectedKinoNumber[i][j][k])
						g.setColor(Color.BLACK);
					else if(hoverNumber[i][j][k])
						g.setColor(Color.WHITE.darker());
					else if(j == 0 || j == 2 || j == 4 || j == 6)
						g.setColor(Color.WHITE);
					else
						g.setColor(rectColor);
					
					g.fillRect(rectX, rectY, (Kino.WIDTH / 3 - 26) / 10, 20);

					numbersX[i][j][k] = rectX;
					numbersY[i][j][k] = rectY;
					
					rectX += (Kino.WIDTH / 3 - 26) / 10 + 1;
					
					temp++;
					
				}
				
				if(i == 0 || i == 3)
					rectX = 8;
				else if(i == 1 || i == 4)
					rectX = Kino.WIDTH / 3 + 8;
				else if(i == 2 || i == 5)
					rectX = Kino.WIDTH / 3 * 2 + 8;
				
				rectY += 21;
				
			}
			
		}
	
		g.setFont(new Font("Arial", 1, 15));
		
		for(int i = 0; i < 6; i++) {
			
			int temp = 1;
			
			for(int j = 0; j < 8; j++) {
				
				for(int k = 0; k < 10; k++) {
					
					if(numberMatch(temp, false) && selectedNumber[i][j][k])
						g.setColor(Color.BLACK);
					else if(numberMatch(temp, true) && selectedKinoNumber[i][j][k])
						g.setColor(Color.WHITE);
					else
						g.setColor(Color.RED);
					
					g.drawString(Integer.toString(temp), numbersX[i][j][k] + ((Kino.WIDTH / 3 - 26) / 10) / 2 - g.getFontMetrics().stringWidth(Integer.toString(temp)) / 2, numbersY[i][j][k] + 15);
					temp++;
					
				}

			}
			
		}
		
	}
	
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		if(SwingUtilities.isLeftMouseButton(e) && Kino.programState == STATE.Number_Selection) {
			
			for(int i = 0; i < hoverKino.length; i++) {
				
				if(!isSelectedGame(i)) {
					
					if(Kino.hud.mouseOver(mx, my, kinoSelectionX[i], kinoSelectionY[i], 20, 20))
						selectedKino[i] = !selectedKino[i];
					
				}
				
				
			}
			
			for(int i = 0; i < 6; i++) {
				
				for(int j = 0; j < 12; j++) {
					
					if(Kino.hud.mouseOver(mx, my, gameSelectionX[i][j], gameSelectionY[i][j], 15, 15)) {

						for(int k = 0; k < 12; k++) {
							
							if(selectedGame[i][j])
								continue;
							
							selectedGame[i][k] = false;
							
						}
						
						for(int k = 0; k < 8; k++) {
							
							for(int m = 0; m < 10; m++)
								selectedNumber[i][k][m] = false;
								
						}
					
						selectedGame[i][j] = !selectedGame[i][j];
						
						setNumbers(i);
						
					}
						
				}

			}
			
			for(int i = 0; i < 6; i++) {
				
				if(isSelectedGame(i)) {
				
					if(totalNumbers[i] != 1) {
						
						for(int j = 0; j < 8; j++) {
							
							for(int k = 0; k < 10; k++) {
								
								if(Kino.hud.mouseOver(mx, my, numbersX[i][j][k], numbersY[i][j][k], (Kino.WIDTH / 3 - 26) / 10, 20)) {
									
									if(selectedNumber[i][j][k])
										totalNumbers[i]++;
									else
										totalNumbers[i]--;
									
									
									selectedNumber[i][j][k] = !selectedNumber[i][j][k];
									
								}
									
							}
							
						}
						
					}
					
					else if(kinoNumber[i] != 1 && selectedKino[i]) {
						
						for(int j = 0; j < 8; j++) {
							
							for(int k = 0; k < 10; k++) {
								
								if(Kino.hud.mouseOver(mx, my, numbersX[i][j][k], numbersY[i][j][k], (Kino.WIDTH / 3 - 26) / 10, 20)) {
									
									if(selectedNumber[i][j][k]) {
										
										selectedNumber[i][j][k] = !selectedNumber[i][j][k];
										totalNumbers[i]++;
									}
										
									else {
										
										selectedKinoNumber[i][j][k] = !selectedKinoNumber[i][j][k];
										kinoNumber[i]--;
										
									}
										
								}
									
							}
							
						}
						
					}
					
				}
				
			}
			
		}
		else if(SwingUtilities.isMiddleMouseButton(e) && Kino.programState == STATE.Number_Selection)
			reset();
		
	}
	
	public void mouseMoved(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		this.mx = mx;
		this.my = my;
		
		if(Kino.programState == STATE.Number_Selection) {
			
			
			for(int i = 0; i < hoverKino.length; i++) {
				
				if(!isSelectedGame(i))
					hoverKino[i] = Kino.hud.mouseOver(mx, my, (double) (kinoSelectionX[i]), (double) (kinoSelectionY[i]), 20, 20);

			}
			
			for(int i = 0; i < 6; i++) {
				
				for(int j = 0; j < 12; j++)
					hoverGame[i][j] = Kino.hud.mouseOver(mx, my, gameSelectionX[i][j], gameSelectionY[i][j], 15, 15);
				
			}
			
			for(int i = 0; i < 6; i++) {
				
				if(isSelectedGame(i)) {
				
					if(totalNumbers[i] != 1 || (kinoNumber[i] != 1 && selectedKino[i])) {
						
						for(int j = 0; j < 8; j++) {
							
							for(int k = 0; k < 10; k++)
								hoverNumber[i][j][k] = Kino.hud.mouseOver(mx, my, numbersX[i][j][k], numbersY[i][j][k], (Kino.WIDTH / 3 - 26) / 10, 20);
								
						}
						
					}
					
					
				}
				
									
			}
			
		}
		
	}
	
	private boolean isSelectedGame(int column) {
		
		for(int i = 0; i < 12; i++)
			if(selectedGame[column][i])
				return true;
				
		return false;
		
	}
	
	private boolean numberMatch(int number, boolean kino) {
		
		if(!kino) {
			
			for(int i = 0; i < 19; i++) {
				
				if(HUD.totalNumbers[i] == number)
					return true;
				
			}
			
		}
		else
			if(HUD.kinoNumber == number)
				return true;
		
		return false;
		
	}
	
	private void reset() {
		
		for(int i = 0; i < kinoNumber.length; i++)
			kinoNumber[i] = 2;
		
		for(int i = 0; i < 6; i++)
			selectedKino[i] = false;
		
		for(int i = 0; i < 6; i++) {
			
			for(int j = 0; j < 12; j++)
				selectedGame[i][j] = false;
			
		}
		
		for(int i = 0; i < 6; i++) {
			
			for(int j = 0; j < 8; j++) {
				
				for(int k = 0; k < 10; k++) {
					
					selectedNumber[i][j][k] = false;
					selectedKinoNumber[i][j][k] = false;
					hoverNumber[i][j][k] = false;
					
				}
				
			}
			
		}
		
	}
	
	private void setNumbers(int column) {

		if(selectedGame[column][0])
			totalNumbers[column] = 12;
		else if(selectedGame[column][1])
			totalNumbers[column] = 11;
		else if(selectedGame[column][2])
			totalNumbers[column] = 10;
		else if(selectedGame[column][3])
			totalNumbers[column] = 9;
		else if(selectedGame[column][4])
			totalNumbers[column] = 8;
		else if(selectedGame[column][5])
			totalNumbers[column] = 7;
		else if(selectedGame[column][6])
			totalNumbers[column] = 6;
		else if(selectedGame[column][7])
			totalNumbers[column] = 5;
		else if(selectedGame[column][8])
			totalNumbers[column] = 4;
		else if(selectedGame[column][9])
			totalNumbers[column] = 3;
		else if(selectedGame[column][10])
			totalNumbers[column] = 2;
		else if(selectedGame[column][11])
			totalNumbers[column] = 1;
		
		if(!selectedKino[column])
			totalNumbers[column]++;

	}
	
}
