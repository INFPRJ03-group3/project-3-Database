import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

public class Draw extends JFrame{
		
	ArrayList<Integer> numbers = new ArrayList<>(); 	
	

	public Draw() {	
		setTitle("test");
		setSize(960, 960);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);		
	}
	
	public void paint(Graphics g){
		Integer x1 = 60;
		Integer y1 = 710;
		Integer x2 = 100;
		Integer y2 = 190;	
		
		numbers.add(10);
		numbers.add(20); 
		
		while (numbers.isEmpty() == false) {
			g.drawRect(x1, y1 - numbers.get(0) * 5, x2, y2 + numbers.get(0) * 5);	
			g.fillRect(x1, y1 - numbers.get(0) * 5, x2, y2 + numbers.get(0) * 5);			
			x1 += 110;			
			numbers.remove(0); 
		}	
		
		g.setColor(Color.BLACK);
		g.drawLine(50, 50, 50, 900);
		g.drawLine(50, 900, 900, 900);
		
	}
	
	public static void main(String[] args) {
		Draw draw = new Draw();
		draw.paint(null);

	}
		
		
}