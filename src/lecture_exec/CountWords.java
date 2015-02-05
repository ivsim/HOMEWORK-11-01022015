package lecture_exec;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JFileChooser;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.HashMap;
import java.util.Set;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;;

public class CountWords {

	private JFrame frame;
	private JButton saveFileButton;
	
	private HashMap<String, Integer> fileContent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CountWords window = new CountWords();
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
			
			FileReader fileReader = new FileReader(file.getAbsolutePath());
			BufferedReader reader = new BufferedReader(fileReader);
			
			while (reader.ready()) {
				String temp = reader.readLine().toLowerCase();
				String[] splitTemp = temp.split("[^a-zа-я]+");
				
//				words.addAll(Arrays.asList(splitTemp));
				
				for (String x : splitTemp) {
					Integer count = fileContent.get(x);
					if (count == null) {
						count = 0;
					}
						fileContent.put(x, count+1);
				}
			}
			
			reader.close();
			
			//Enable the button
			saveFileButton.setEnabled(true);
						
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void saveFile() {
		
		try {
			
			// let's take the keys out...
			Set<String> keys = fileContent.keySet();
			
			// let's make 2 parallel arrays that go hand by hand...whatever we do with the one the same should happen with the other...
			String[] wordKeys = new String[keys.size()];
			Integer[] intValues = new Integer[keys.size()];
			int index = 0;
			
			for (String x : keys) {
				wordKeys[index] = x;
				intValues[index] = fileContent.get(x);			
				index++;
			}
			
			int sum = 0;		
			for (int i = 1; i < intValues.length; i++)
			{sum = sum + i;}//1+2+3+...+(.length-2)+(.length-1)
			
			for (int j = 1; j <= sum; j++){ 
					
				for (int n = 1; n < intValues.length; n++){ // overall number of the internal bubble iterations for one array round: (.length -1)
					
					if (intValues[n-1] > intValues[n]) // simple comparison of two adjacent array members
					{
						Integer numTransfer = intValues[n]; //passing an empty variable to serve as placeholder
						String texTransfer = wordKeys[n];
						
						intValues[n] = intValues[n-1];
						wordKeys[n] = wordKeys[n-1];
						
						intValues[n-1] = numTransfer;
						wordKeys[n-1] = texTransfer;
					}
				}
			}
			
			JFileChooser fileWindow = new JFileChooser();
			fileWindow.showDialog(null, "ИЗБЕРИ");
			
			File file = fileWindow.getSelectedFile();
			
			FileWriter fileStream = new FileWriter(file.getPath());
			BufferedWriter writer = new BufferedWriter(fileStream);
			
			for (int i = intValues.length - 1; i >= 0; i--) {
				writer.append("\'" + wordKeys[i] + "\' - " + intValues[i] + " път(и).");
				writer.newLine();				
			}
			
			writer.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}	

	/**
	 * Create the application.
	 */
	public CountWords() {
		initialize();
		this.fileContent = new HashMap<String, Integer>();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setTitle("                         ЛЕКСИ СТАТИСТИКА");
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton chooseFileButton = new JButton("ИЗБЕРИ ФАЙЛ");
		chooseFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFile();
			}
		});
		chooseFileButton.setBackground(Color.WHITE);
		chooseFileButton.setFont(new Font("Dialog", Font.BOLD, 20));
		chooseFileButton.setBounds(25, 41, 401, 82);
		frame.getContentPane().add(chooseFileButton);
		
		this.saveFileButton = new JButton("ЗАПИШИ СТАТИСТИКА");
		saveFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
		saveFileButton.setEnabled(false);
		saveFileButton.setBackground(Color.WHITE);
		saveFileButton.setFont(new Font("Dialog", Font.BOLD, 20));
		saveFileButton.setBounds(25, 174, 401, 82);
		frame.getContentPane().add(saveFileButton);
	}
}
