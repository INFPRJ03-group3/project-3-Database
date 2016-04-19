package com.project3.database.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.project3.database.other.Lists;

@SuppressWarnings("serial")
public class Graph extends JPanel {

	ArrayList<Color> colors = new ArrayList<>();	
	
	ArrayList<Integer> data = new ArrayList<>();
	ArrayList<String> legend = new ArrayList<>();
	ArrayList<String> bar_text = new ArrayList<>();
	Integer width;
	Integer height;
	Integer pos_x;
	Integer pos_y;
	Integer space;
	String graph_name;
	String text_vertical; 
	String text_horizontal; 

	public Graph(Integer width, Integer height, Integer pos_x, Integer pos_y, ArrayList<Integer> data, Integer space,
			String graph_name, String text_vertical, String text_horizontal, ArrayList<String> legend, ArrayList<String> bar_text) {
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
		this.legend = legend; 	
		this.bar_text = bar_text;
		
	}	

	private void fill_colors() {
		Color light_blue = new Color(102, 102, 255);
		Color light_green = new Color(102, 255, 102);
		Color purple = new Color(75, 0, 130);
		Color dark_red = new Color(153, 0, 0);
		Color brown_green = new Color(153, 153, 0);
		Color other_blue = new Color(0, 153, 153);
		if (colors.isEmpty()) {
			colors.add(Color.RED);
			colors.add(light_blue);		
			colors.add(light_green);
			colors.add(Color.YELLOW);
			colors.add(Color.MAGENTA);
			colors.add(Color.CYAN);
			colors.add(Color.PINK);
			colors.add(Color.GRAY); 
			colors.add(dark_red);
			colors.add(Color.BLUE);	
			colors.add(Color.GREEN);
			colors.add(brown_green);
			colors.add(purple);
			colors.add(other_blue);		
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (Graph graph : Lists.graphs) {		
			Integer bar_width = ((graph.width + graph.space) / graph.data.size()) - graph.space; //Calculate the width of every bar
			Integer bar_x = graph.pos_x + graph.space/3;
			
			Integer legend_height = null;
			if (graph.legend != null) {
				if (50 * graph.legend.size() < graph.height) {
					legend_height = 50 + ((graph.height/60)+ graph.space);
				} else {
					legend_height = ((graph.height + graph.space) / graph.legend.size()) - graph.space; //Calc legend_height of every bar
				}				
			}		
			
			Integer legend_y = graph.pos_y + 15; //Calc legend y position

			Iterator<Integer> data_iter = graph.data.iterator(); 		
			Iterator<Color> color_iter = graph.colors.iterator();
			
			Integer graph_name_size = (graph.width + graph.height)/ 50;
			g.setFont(new Font(null, Font.BOLD, graph_name_size)); //Change text_size		
			g.drawString(graph.graph_name, graph.pos_x + graph.width/2, graph.pos_y - 20); //Set the graph_name

			Integer i = Collections.max(graph.data); //Calc the max value in the list
			
			while (data_iter.hasNext()) {
				if (color_iter.hasNext()) { 
					Color color = color_iter.next();
					g.setColor(color); //Use a list with colors to change the color of every bar
				}			
				Integer data = data_iter.next(); 								

				Integer bar_height = ((data * graph.height) / i); //Adapt the bar bar height to the highest value in the data list			
				
				g.drawRect(bar_x, (graph.height - bar_height + graph.pos_y), bar_width, bar_height); //Draw the bar
				g.fillRect(bar_x, graph.height - bar_height + graph.pos_y, bar_width, bar_height);
				
				g.setFont(new Font(null, Font.BOLD, (graph.width + graph.height)/ 100));				
				
				g.setFont(new Font(null, Font.BOLD, (graph.width + graph.height)/ 80));
				g.drawString(data.toString(), bar_x + bar_width/3, graph.height - bar_height + graph.pos_y); //Draw value above every bar 				
				
				bar_x += (bar_width + graph.space); //Calculate the new bar_x position				 				
			}
			Integer index = 0;
			
			if (graph.legend != null) {
				for (String legend_text : graph.legend) {
					g.setFont(new Font(null, Font.BOLD, 20)); 	
					g.setColor(colors.get(index));
					g.drawRect(graph.pos_x + graph.width + 15, legend_y, 100, ((graph.height/60)+ graph.space)); //Draw legend
					g.fillRect(graph.pos_x + graph.width + 15, legend_y, 100, ((graph.height/60)+ graph.space));
					g.setColor(Color.BLACK);
					g.drawString(legend_text, graph.pos_x + graph.width + 15, legend_y - 10);
					
					if (graph.bar_text != null && index < graph.bar_text.size()) {
						g.setFont(new Font(null, Font.BOLD, 15)); 					
						g.drawString(graph.bar_text.get(index), graph.width + 220, legend_y + 20);
						
					}
					
					legend_y += legend_height; //Calc the new legend_y pos
					index += 1; 
					
				}				
			}			
			
			Graphics2D g2d = (Graphics2D) g.create(); // Used to change line graph.width
			g2d.setStroke(new BasicStroke(3));
			g2d.setColor(Color.BLACK);
			g2d.drawLine(graph.pos_x, graph.pos_y, graph.pos_x, graph.pos_y + graph.height); //Graph line vertical
			g2d.drawLine(graph.pos_x, graph.height + graph.pos_y, graph.width + graph.pos_x, graph.height + graph.pos_y); //Graph line horizontal	
			
			Integer text_size = null;
			if (graph.height > graph.width) {
				text_size = ((graph.height)/ 50);
			} else {
				text_size = ((graph.height)/ 50);
			}
			
			g.setFont(new Font(null, Font.BOLD, text_size)); 
			
			g.setColor(Color.BLACK);
			
			if (graph.text_vertical != null && graph.pos_x >= 100) {	//Add vertical text		
				g.drawString(graph.text_vertical, graph.pos_x - 90, graph.pos_y + 30);
				g.drawString("^", graph.pos_x - 80, graph.pos_y + 50);
				g.drawString("|", graph.pos_x - 78, graph.pos_y + 60);
				g.drawString("|", graph.pos_x - 78,  graph.pos_y + 70);
				
			}
			
			double screenSize = Toolkit.getDefaultToolkit().getScreenSize().getHeight(); 	
			
			if (graph.text_horizontal != null && screenSize - 100 > (graph.height + graph.pos_y)) { //Add horizontal text
				g.drawString(graph.text_horizontal + " --->", graph.width + graph.pos_x - graph.width/10, graph.height + graph.pos_y + 20);
			}
					
		}		

	}

	public void drawScreen() {
		
		while (Lists.frames.size() > 2) {
			Lists.frames.remove(2);
		}
		
		Lists.graphs.add(this); 
		
		if (Lists.frames.isEmpty()) {		
			JFrame frame = new JFrame();			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 

			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
			frame.setSize(screenSize);	
			frame.setVisible(true);
			frame.setTitle("Graph " + Map.current_region);		
			Lists.frames.add(frame);
			
			JButton backbutton = Button.backButton(1600, 900);
			backbutton.addActionListener(new ActionListener() {		
				public void actionPerformed(ActionEvent e) {
					for (JFrame frame : Lists.frames) {
						frame.dispose();
					}
					
					for (JFrame frame : Lists.PieCharts) {
						frame.dispose();
					}
					Lists.PieCharts.clear();
					Lists.frames.clear();
					Lists.graphs.clear();					
				}
			});
			Lists.frames.get(0).add(backbutton); 
							
		}	
		Lists.frames.get(0).getContentPane().remove(this);
		Lists.frames.get(0).add(this); 	
		Lists.frames.get(0).revalidate();			
		
		
	}

}
