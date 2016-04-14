package com.project3.database.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.project3.database.Database;

@SuppressWarnings("serial")
public class Graph extends JPanel {

	ArrayList<Color> colors = new ArrayList<>();	
	ArrayList<Integer> data = new ArrayList<>();
	Integer width;
	Integer height;
	Integer pos_x;
	Integer pos_y;
	Integer space;
	String graph_name;
	String text_vertical; 
	String text_horizontal; 

	public Graph(Integer width, Integer height, Integer pos_x, Integer pos_y, ArrayList<Integer> data, Integer space,
			String graph_name, String text_vertical, String text_horizontal) {
		super();
		setBackground(Color.WHITE);

		fill_colors();
		this.width = width;
		this.height = height;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.data = data;
		this.space = space;
		this.graph_name = graph_name;
		this.text_vertical = text_vertical;
		this.text_horizontal = text_horizontal;
		
	}

	private void fill_colors() {
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
		super.paintComponent(g);

		Integer bar_width = ((width + space) / data.size()) - space; //Calculate the width of every bar
		Integer bar_x = pos_x + space/3;

		Iterator<Integer> data_iter = data.iterator(); 
		Iterator<Color> color_iter = colors.iterator();
		
		Integer graph_name_size = (width + height)/ 50;
		g.setFont(new Font(null, Font.BOLD, graph_name_size)); //Change text_size		
		g.drawString(graph_name, pos_x + width/2, pos_y); //Set the graph_name

		Integer i = Collections.max(data); //Calc the max value in the list

		while (data_iter.hasNext()) {
			if (color_iter.hasNext()) { 
				Color color = color_iter.next();
				g.setColor(color); //Use a list with colors to change the color of every bar
			}			
			Integer data = data_iter.next(); 

			Integer bar_height = ((data * height) / i); //Adapt the bar height to the highest value in the data list			
			
			g.drawRect(bar_x, (height - bar_height + pos_y), bar_width, bar_height); //Draw the bar
			g.fillRect(bar_x, height - bar_height + pos_y, bar_width, bar_height);
			
			g.setFont(new Font(null, Font.BOLD, (width + height)/ 80));
			g.drawString(data.toString(), bar_x + bar_width/3, height - bar_height + pos_y); //Draw value above every bar 
			
			bar_x += bar_width + space; //Calculate the new bar_x position
			
		}
		
		Graphics2D g2d = (Graphics2D) g.create(); // Used to change line width
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.BLACK);
		g2d.drawLine(pos_x, pos_y, pos_x, pos_y + height); //Graph line vertical
		g2d.drawLine(pos_x, height + pos_y, width + pos_x, height + pos_y); //Graph line horizontal	
		
		Integer text_size = ((width + height)/ 100);
		g.setFont(new Font(null, Font.BOLD, text_size)); 
		
		if (text_vertical != null && pos_x >= 100) {	//Add vertical text		
			g.drawString(text_vertical, pos_x - 90, pos_y + 30);
			g.drawString("^", pos_x - 80, pos_y + 50);
			g.drawString("|", pos_x - 78, pos_y + 60);
			g.drawString("|", pos_x - 78,  pos_y + 70);
			
		}
		
		double screenSize = Toolkit.getDefaultToolkit().getScreenSize().getHeight(); 	
		
		if (text_horizontal != null && screenSize - 100 > (height + pos_y)) { //Add horizontal text
			g.drawString(text_horizontal + " --->", width + pos_x - width/10, height + pos_y + 20);
		}
		
		

	}

	public void drawScreen() {
			
		JFrame application = new JFrame();	
		Database.frames.add(application);

		while (Database.frames.size() > 1) { //Close the old graph screen(s)			
			Database.frames.get(0).dispose();		
			Database.frames.remove(0);
		}			
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 

		application.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		application.setSize(screenSize);		

		application.setVisible(true);
		application.setTitle("Graph " + DrawMap.current_region);	
		application.add(this);			
					
		
	}

}
