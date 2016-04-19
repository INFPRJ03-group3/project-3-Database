package com.project3.database.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.project3.database.Database;

public class Piechart extends JPanel {

	ArrayList<Color> colors = new ArrayList<>();
	ArrayList<Integer> data = new ArrayList<>();
	ArrayList<String> legend = new ArrayList<>();
	Integer width;
	Integer height;
	Integer pos_x;
	Integer pos_y;
	String piechart_name;
	String text_horizontal;
	Integer value;

	public Piechart(Integer width, Integer height, Integer pos_x, Integer pos_y, ArrayList<Integer> data,
			String piechart_name, String text_horizontal, ArrayList<String> legend, Integer value) {
		super();
		setBackground(Color.WHITE);

		fill_colors();
		this.width = width;
		this.height = height;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.data = data;
		this.piechart_name = piechart_name;
		this.text_horizontal = text_horizontal;
		this.legend = legend;
		this.value = value;

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

	Piechart[] slices = null;

	public void paintComponent(Graphics g) {
		drawPie((Graphics2D) g, getBounds(), slices);
	}

	void drawPie(Graphics2D g, Rectangle area, Piechart[] slices) {
		double total = 0.0D;
		for (int i = 0; i < slices.length; i++) {
			total = total + slices[i].value;
			{
				for (Piechart piechart : Database.piecharts) {
					double curValue = 0.0D;
					int startAngle = 0;
					Piechart[] slices1;
					Iterator<Integer> data_iter = piechart.data.iterator();
					Iterator<Color> color_iter = piechart.colors.iterator();
					for (int i1 = 0; i1 < slices1.length; i1++) {
						startAngle = (int) (curValue * 360 / total);
						int arcAngle = (int) (slices1[i1].value * 360 / total);
						while (data_iter.hasNext()) {
							if (color_iter.hasNext()) {
								Color color = color_iter.next();
								g.setColor(color); // Use a list with colors to
													// change the color of every
													// slice
							}
							g.fillArc(piechart.pos_x, piechart.pos_y, piechart.width, piechart.height, startAngle,
									arcAngle);
							curValue += slices1[i1].value;
						}
						Integer bar_width = ((piechart.width) / piechart.data.size()); // Calculate
																						// the
																						// width
																						// of
																						// every
																						// slice
						Integer bar_x = piechart.pos_x / 3;

						Integer legend_height;
						if (50 * piechart.legend.size() < piechart.height) {
							legend_height = 50 + ((piechart.height / 60));
						} else {
							legend_height = ((piechart.height) / piechart.legend.size()); // Calc
																							// legend_height
																							// of
																							// every
																							// slice
						}

						Integer legend_y = piechart.pos_y + 15; // Calc legend y
																// position

						Integer piechart_name_size = (piechart.width + piechart.height) / 50;
						g.setFont(new Font(null, Font.BOLD, piechart_name_size)); // Change
																					// text_size
						g.drawString(piechart.piechart_name, piechart.pos_x + piechart.width / 2, piechart.pos_y - 20); // Set
																														// the
																														// piechart_name

						Integer i2 = Collections.max(piechart.data); // Calc the
																		// max
																		// value
																		// in
																		// the
																		// list

						Integer data = data_iter.next();

						Integer text_size = null;
						if (piechart.height > piechart.width) {
							text_size = ((piechart.height) / 50);
						} else {
							text_size = ((piechart.height) / 50);
						}

						g.setFont(new Font(null, Font.BOLD, text_size));

						g.setColor(Color.BLACK);

						double screenSize = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

						if (piechart.text_horizontal != null && screenSize - 100 > (piechart.height + piechart.pos_y)) { // Add
																															// horizontal
																															// text
							g.drawString(piechart.text_horizontal + " --->",
									piechart.width + piechart.pos_x - piechart.width / 10,
									piechart.height + piechart.pos_y + 20);
						}

					}

				}
			}
		}
	}

	public void drawScreen() {
		Database.piecharts.add(this);

		if (Database.frames.isEmpty()) {
			JFrame frame = new JFrame();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setSize(screenSize);
			frame.setVisible(true);
			frame.setTitle("piechart " + DrawMap.current_region);
			Database.frames.add(frame);
		}

		Database.frames.get(0).add(this);

	}

}