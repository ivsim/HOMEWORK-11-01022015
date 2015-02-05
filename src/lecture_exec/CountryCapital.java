package lecture_exec;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class CountryCapital {

	public static void main(String[] args) {

		Scanner input = new Scanner (System.in);
		
		String[] country = new String[5];
		String[] capital = new String[5];
		
		for (int i = 1; i <= 5; i++) {
			System.out.print("Please, enter country " + i + ": ");
			String temp = input.nextLine();
			country[i-1] = temp;
			
			System.out.print("Please, enter capital " + i + ": ");
			temp = input.nextLine();
			capital[i-1] = temp;			
		}
		
		input.close();
		
		String filePathCountry = "/home/ivo/Desktop/country.txt";
		String filePathCapital = "/home/ivo/Desktop/capital.txt";
		
		try {
			
			FileWriter fileStream1 = new FileWriter(filePathCountry);
			BufferedWriter writer1 = new BufferedWriter(fileStream1);
			
			for (String x: country) {
				writer1.write(x);
				writer1.newLine();
			}
			
				writer1.close();
			
			FileWriter fileStream2 = new FileWriter(filePathCapital);
			BufferedWriter writer2 = new BufferedWriter(fileStream2);
			
			for (String x: capital) {
				writer2.write(x);
				writer2.newLine();
			}
			
			writer2.close();
			
			} catch (Exception e) {
				System.out.println("Възникна грешка при записа на файла");
				System.out.println(e.getMessage());
			}
		
			System.out.print("Записът на файла приключи! \n");
		
	}

}

//String[] country = {"Romania", "Bulgaria", "USA", "Germany", "USSR"};
//String[] capital = {"Bucharest", "Sofia", "Washington", "Berlin", "Moscow"};
