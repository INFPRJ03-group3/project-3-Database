import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Menumain extends JFrame {
	private JFrame frame;
	private JLabel statusLabel;
	
	public Menumain() {
		//calls the GUI
		prepareGui();
	}

	public static void main(String[] args) {
		Menumain menuMain = new Menumain();
		

	}
	
	public void prepareGui() {
		//sets up the main menu frame.
		JFrame frame = new JFrame("Project 3");
		try{
			frame.setContentPane(new JLabel(new ImageIcon (
					ImageIO.read(new File("C:/Users/Roy/Pictures/cover.jpg")))));
		}catch(IOException e) {
			System.out.println("Image file non existing");
		}
		
		frame.pack();
		frame.setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// creating the buttons
		JButton okButton = new JButton("Map");
		okButton.setSize(125, 50);
		okButton.setLocation(600, 850);
		frame.add(okButton);
		
		JButton quitButton = new JButton("Quit");
		quitButton.setSize(125, 50);
		quitButton.setLocation(1200, 850);
		frame.add(quitButton);
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
//		okButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				frame.dispose();
//				NextPage frame = new NextPage();
//				frame.setVisible(true)
//			}
//		});
		frame.setVisible(true);
	}
	
}
