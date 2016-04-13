package com.project3.database.draw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.project3.database.Database;

@SuppressWarnings("serial")
public class DrawGraph extends JPanel {
	ArrayList<Color> colors = new ArrayList<>();
	static ArrayList<JFrame> frames = new ArrayList<>();

	public DrawGraph() {
		super();
		setBackground(Color.WHITE);

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

		if (Database.crime_data.isEmpty() == false) {

			g.setFont(new Font(null, Font.BOLD, 15));
			g.setColor(Color.BLACK);

			g.drawLine(width / 40, width / 40, width / 40, height - 60);
			g.drawLine(width / 40, height - 60, 600, height - 60);
			g.drawString("Percentage", 10, 30);
			g.drawString("^", 30, 50);
			g.drawString("|", 32, 60);
			g.drawString("|", 32, 70);
			g.drawString("Crime type  --->", width / 4, height - 30);
			g.setFont(new Font(null, Font.BOLD, 20));
			g.drawString(DrawMap.current_region, width / 5, 30);

			Integer x1 = width / 40;
			Integer y1 = height - 60;
			Integer x2 = width / 40 + 20;
			Integer recy1 = 100;

			Iterator<Color> color_iter = colors.iterator();
			Iterator<Integer> crime_iter = Database.crime_data.iterator();
			Iterator<String> type_iter = Database.crime_types.iterator();

			Integer scale = calc_Scale(Database.crime_data);

			g.setFont(new Font(null, Font.BOLD, 15));

			while (crime_iter.hasNext()) {
				Integer num = crime_iter.next();
				String crime_type = type_iter.next();

				if (color_iter.hasNext()) {
					Color color = color_iter.next();
					g.setColor(color);
				}
				g.drawRect(x1, y1 - (num * scale), x2, num * scale);
				g.fillRect(x1, y1 - (num * scale), x2, num * scale);
				g.drawString(num.toString(), x1 + 35, y1 - (num * scale + 1));
				x1 += 80;

				g.drawRect(650, recy1, 60, 30);
				g.fillRect(650, recy1, 60, 30);
				g.setColor(Color.BLACK);
				if (type_iter.hasNext()) {
					g.drawString(crime_type, 720, recy1 + 20);

				} else {
					g.drawString(Database.crime_types.get(Database.crime_types.size() - 1).substring(0, 26), 720,
							recy1 + 10);
					g.drawString(
							Database.crime_types.get(Database.crime_types.size() - 1).substring(26,
									Database.crime_types.get(Database.crime_types.size() - 1).length()),
							720, recy1 + 30);
				}

				recy1 += 50;

			}

		} else {
			g.setColor(Color.RED);
			g.setFont(new Font(null, Font.BOLD, 30));
			g.drawString("No crime data available", width / 5, 30);
		}

		if (Database.income_data.isEmpty() == false) {
			g.setFont(new Font(null, Font.BOLD, 15));
			g.setColor(Color.BLACK);

			g.drawLine(1000, width / 40, 1000, height - 60);
			g.drawLine(1000, height - 60, 1160, height - 60);

			g.drawString("Hoeveelheid", 970, 30);
			g.drawString("^", 980, 50);
			g.drawString("|", 982, 60);
			g.drawString("|", 982, 70);

			Integer x1 = 1000;
			Integer y1 = height - 60;
			Integer x2 = width / 40 + 20;
			Integer recy1 = 100;

			g.drawString("Households", 1270, 120);
			g.drawString("Average income ", 1270, 160);
			g.drawString("per household", 1270, 177);

			Integer scale = 55;
			Integer i = Collections.max(Database.income_data);

			if (i <= 20000 && i > 10000) {
				scale = scale / 2;
			} else if (i <= 10000) {
				scale = scale / 4;
			}

			Iterator<Color> color_iter = colors.iterator();

			Iterator<Integer> income_iter = Database.income_data.iterator();

			while (income_iter.hasNext()) {
				Integer num = income_iter.next();
				if (color_iter.hasNext()) {
					Color color = color_iter.next();
					g.setColor(color);
				}

				g.drawRect(x1, y1 - (num / scale), x2, num / scale);
				g.fillRect(x1, y1 - (num / scale), x2, num / scale);
				g.drawString(num.toString(), x1 + 20, y1 - (num / scale));
				x1 += 80;

				g.drawRect(650, recy1, 60, 30);
				g.fillRect(650, recy1, 60, 30);

				g.drawRect(1200, recy1, 60, 30);
				g.fillRect(1200, recy1, 60, 30);
				g.setColor(Color.BLACK);
				recy1 += 50;

			}

		} else {
			g.setColor(Color.RED);
			g.setFont(new Font(null, Font.BOLD, 30));
			g.drawString("No income data available", width - width / 3, 30);
		}

	}

	private Integer calc_Scale(ArrayList<Integer> list) {
		Integer scale = 170;
		Integer i = Collections.max(list);

		if (i >= 80) {
			scale = 8;
		} else if (i >= 60) {
			scale = 10;
		} else if (i >= 40) {
			scale = 13;
		} else if (i >= 30) {
			scale = 18;
		} else if (i >= 20) {
			scale = 26;
		} else if (i >= 10) {
			scale = 40;
		} else if (i >= 5) {
			scale = 80;
		}

		return scale;
	}

	public static void main(String[] args) {
		DrawGraph panel = new DrawGraph();
		JFrame application = new JFrame();

		frames.add(application);

		while (frames.size() > 1) {
			frames.get(0).setVisible(false);
			frames.remove(0);
		}

		application.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		application.add(panel);
		application.setSize(2000, 960);

		application.setVisible(true);
		application.setTitle("Graph " + DrawMap.current_region);

	}

}