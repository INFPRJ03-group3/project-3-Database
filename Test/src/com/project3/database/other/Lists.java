package com.project3.database.other;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import com.project3.database.draw.Graph;

public class Lists { //Class with all the public lists
	
	public static List<Color> colors = Arrays.asList(Color.BLUE, Color.RED, Color.CYAN, Color.ORANGE, Color.GREEN, Color.MAGENTA, Color.DARK_GRAY);	
	public static ArrayList<String> crime_types = new ArrayList<>();
	public static ArrayList<String> income_types = new ArrayList<>();
	public static ArrayList<String> max_crime = new ArrayList<>();
	public static ArrayList<String> queries = new ArrayList<>();
	public static ArrayList<JFrame> frames = new ArrayList<>();
	public static ArrayList<Graph> graphs = new ArrayList<>();	

}
