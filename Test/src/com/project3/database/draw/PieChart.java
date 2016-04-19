package com.project3.database.draw;

import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

import com.project3.database.other.Lists;

@SuppressWarnings("serial")
public class PieChart extends ApplicationFrame {	
	static ArrayList<Integer> data = new ArrayList<>();
	static ArrayList<String> slice_names = new ArrayList<>();
	static String title; 
	
	public PieChart(String title, ArrayList<Integer> data, ArrayList<String> slice_names) {
		super(title);
		setContentPane(createDemoPanel());
		this.data = data;
		this.slice_names = slice_names;
		this.title = title; 
	}
	
	private static PieDataset createDataset() {	
		DefaultPieDataset dataset = new DefaultPieDataset();
		Integer index = 0;
		for (Integer value : data) {
			dataset.setValue(slice_names.get(index), value);
			index += 1;
		}
		return dataset;
	}

	private static JFreeChart createChart(PieDataset dataset) {		
		JFreeChart chart = ChartFactory.createPieChart(title, // chart title																		
				dataset, // data
				true, // include legend
				true, false);		
		return chart;
	}
	

	public static JPanel createDemoPanel() {
		JFreeChart chart = createChart(createDataset());	
		
		return new ChartPanel(chart);
	}

	public void drawScreen() {
		PieChart chart = new PieChart(title, data, slice_names);		
		chart.setSize(1200, 800);	
		//RefineryUtilities.centerFrameOnScreen(demo);			
		chart.setVisible(true);		
			
		Lists.PieCharts.add(chart); 
	}
}