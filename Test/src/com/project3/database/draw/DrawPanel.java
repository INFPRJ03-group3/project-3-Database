package com.project3.database.draw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel {
	ArrayList<Integer> numbers = new ArrayList<>();
	ArrayList<Color> colors = new ArrayList<>();
	//Image map = ImageIO.read(new File("image name and path"))

	public DrawPanel()                       
    {
        super();
        setBackground(Color.WHITE);      
        numbers.add(1); 
        numbers.add(4); 
        numbers.add(4); 
        numbers.add(3); 
        numbers.add(4);     
        numbers.add(2);  
        numbers.add(2); 
        
        if (colors.isEmpty()) {        	
            colors.add(Color.BLUE);
            colors.add(Color.RED);
            colors.add(Color.CYAN);
            colors.add(Color.ORANGE);
            colors.add(Color.GREEN);
            colors.add(Color.MAGENTA);           
            colors.add(Color.DARK_GRAY);
        }
        
    }

	public void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();

		super.paintComponent(g);

		Integer x1 = width / 40;
		Integer y1 = height - 60;
		Integer x2 = width / 40 + 20;
		Integer recy1 = 100; 
		
		g.setFont(new Font(null, Font.BOLD, 15));

		Integer i = Collections.max(numbers);
		Integer scale = 170;

		if (i >= 80) {
			scale = 7;
		} else if (i >= 60) {
			scale = 9;
		} else if (i >= 40) {
			scale = 15;
		} else if (i >= 30) {
			scale = 18;
		} else if (i >= 20) {
			scale = 36;
		} else if (i >= 10) {
			scale = 70;
		} else if (i >= 5) {
			scale = 80;
		}
	

		while (numbers.isEmpty() == false) {
			g.setColor(colors.get(0));
			colors.remove(0); 
			g.drawRect(x1, y1 - (numbers.get(0) * scale), x2, numbers.get(0) * scale);
			g.fillRect(x1, y1 - (numbers.get(0) * scale), x2, numbers.get(0) * scale);
			g.drawString(numbers.get(0).toString(), x1 + 35, y1 - (numbers.get(0) * scale + 1));
			x1 += 80;
			
			g.drawRect(650, recy1, 60, 30);
			g.fillRect(650, recy1, 60, 30);
			g.setColor(Color.BLACK);		
			g.drawString("crime type", 720, recy1 + 20);
			recy1 += 50; 
			numbers.remove(0);				
			 
		}

		g.setColor(Color.BLACK);
		g.drawLine(width / 40, width / 40, width / 40, height - 60);
		g.drawLine(width / 40, height - 60, 600, height - 60);
		g.drawString("Percentage", 10, 30);
		g.setFont(new Font(null, Font.BOLD, 20));
		g.drawString("Table name", width / 5, 50);

	}

	public static void main(String[] args) {
		DrawPanel panel = new DrawPanel();
		JFrame application = new JFrame();

		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		application.add(panel);

		application.setSize(2000, 960);
		application.setVisible(true);
		application.setTitle("Graph");		
	}

}