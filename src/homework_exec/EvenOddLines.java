package homework_exec;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class EvenOddLines {

	private JFrame frame;
	private JButton generateButton;
	
	private ArrayList<String> oddLines;
	private ArrayList<String> evenLines;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EvenOddLines window = new EvenOddLines();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void chooseFile() {
		
		try {
			
			JFileChooser fileWindow = new JFileChooser();
			fileWindow.showDialog(null, "ИЗБЕРИ");
			
			File file = fileWindow.getSelectedFile();
			
			FileReader readStream = new FileReader(file.getAbsolutePath());
			BufferedReader read = new BufferedReader(readStream);
			
			while (read.ready()) {
				String oddTemp = read.readLine();
				String evenTemp = read.readLine();
				
				oddLines.add(oddTemp);
				evenLines.add(evenTemp);
			}
			
			read.close();
			
			generateButton.setEnabled(true);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void generateOddEven() {
		
		try {
			
			JFileChooser fileWindow1 = new JFileChooser();
			fileWindow1.showDialog(null, "Избери ФАЙЛ ЗА ЧЕТНИ");
			
			File file1 = fileWindow1.getSelectedFile();
			
			FileWriter writeStream1 = new FileWriter(file1.getPath());
			BufferedWriter write1 = new BufferedWriter(writeStream1);
			
			for (String x : evenLines) {
				write1.write(x);
				write1.newLine();
			}
			
			write1.close();
			
			fileWindow1.showDialog(null, "Избери ФАЙЛ ЗА НЕЧЕТНИ");
			
			file1 = fileWindow1.getSelectedFile();
			
			writeStream1 = new FileWriter(file1);
			write1 = new BufferedWriter(writeStream1);
			
			for (String x : oddLines) {
				write1.write(x);
				write1.newLine();
			}
			
			write1.close();		
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	/**
	 * Create the application.
	 */
	public EvenOddLines() {
		initialize();
		this.oddLines = new ArrayList<String>();
		this.evenLines = new ArrayList<String>();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("               ЧЕТНИ / НЕЧЕТНИ РЕДОВЕ");
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 184);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton openFileButton = new JButton("ИЗБЕРИ ТЕКСТОВ ФАЙЛ");
		openFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFile();
			}
		});
		openFileButton.setBounds(23, 37, 407, 46);
		frame.getContentPane().add(openFileButton);
		
		this.generateButton = new JButton("ГЕНЕРИРАЙ");
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateOddEven();
			}
		});
		generateButton.setEnabled(false);
		generateButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		generateButton.setBounds(23, 106, 407, 46);
		frame.getContentPane().add(generateButton);
	}

}
