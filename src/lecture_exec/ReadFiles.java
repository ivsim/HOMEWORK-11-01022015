package lecture_exec;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFiles {

	public static void main(String[] args) {

		readFile("/home/ivo/Documents/JAVA course/java-course/examples/12.working-with-files/files/students.txt");
		
	}
	
	public static void readFile(String path){
		
		List<Student> listStudents = new ArrayList<Student>();
		
		try {
			
			FileReader fileReader = new FileReader(path);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
		
			while (bufferedReader.ready()) {
				
				String line = bufferedReader.readLine();
		
				String[] temp = line.split("[ ]+");
				Student currentStudent = new Student(temp[0], temp[1]);
				
				listStudents.add(currentStudent);
			}
			
			bufferedReader.close();
			
			int sum = 0;		
			for (int i = 1; i < listStudents.size(); i++)
			{sum = sum + i;}//1+2+3+...+(.length-2)+(.length-1)
			
			for (int j = 1; j <= sum; j++){ 
					
				for (int n = 1; n < listStudents.size(); n++){ // overall number of the internal bubble iterations for one array round: (.length -1)
					String lastNamePrevoius = listStudents.get(n-1).lastName;
					String lastNameAfter = listStudents.get(n).lastName;
					
					if (lastNamePrevoius.compareTo(lastNameAfter) > 0) // simple comparison of two adjacent array members
					{
						Student numTransfer = listStudents.get(n); //passing an empty variable to serve as placeholder
						listStudents.set(n, listStudents.get(n-1));
						listStudents.set(n-1, numTransfer);
					}
				}
			}
			
			for (Student x : listStudents) {
				System.out.println(x);
			}
			
		
		} catch (Exception e) {
			System.out.println("Възникна грешка при четенето на файла");
			System.out.println(e.getMessage());
		}
		
		}
	
}

//for (int i = 1040; i <= 1071; i++) {
//for (Student x : listStudents) {
//	int tempCast = (int)x.lastName.charAt(0);
//	
//	if (tempCast == i) {
//		System.out.println(x);
//	}
//}
//}
