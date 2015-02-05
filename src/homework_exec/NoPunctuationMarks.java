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

public class NoPunctuationMarks {

	private JFrame frame;
	private JButton generateButton;
	
	private ArrayList<String> noPunctuation;
	private StringBuilder textNoPunct;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NoPunctuationMarks window = new NoPunctuationMarks();
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
				String temp = read.readLine();
				String[] tempNoPunct = temp.split("[^а-яА-Я ]+");
				
				for (String x : tempNoPunct) {
					textNoPunct.append(x);
				}
				
				noPunctuation.add(textNoPunct.toString());
				textNoPunct.setLength(0);// this is to empty the stringbuilder...
				
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
			fileWindow1.showDialog(null, "Избери ФАЙЛ");
			
			File file1 = fileWindow1.getSelectedFile();
			
			FileWriter writeStream1 = new FileWriter(file1.getPath());
			BufferedWriter write1 = new BufferedWriter(writeStream1);
			
			for (String x : noPunctuation) {
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
	public NoPunctuationMarks() {
		initialize();
		this.noPunctuation = new ArrayList<String>();
		this.textNoPunct = new StringBuilder();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("                         БЕЗ ПУНКТУАЦИЯ");
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
		
		this.generateButton = new JButton("БЕЗ ПУНКТУАЦИЯ");
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
