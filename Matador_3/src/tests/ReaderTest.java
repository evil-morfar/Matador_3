import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReaderTest {

	
	
	
  public static void main(String[] args) {
    // TODO Auto-generated method stub/ indlæs fil med tekster
	  
	  String[] testie;
		testie = new String[100];
		int i = 0;
	  
	  
    File file = new File("src/game_boundaries/GUItext.csv");

    try {

      Scanner scanner = new Scanner(file);
      
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        System.out.println(line);
        testie[i] = line;
        i++;
        
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }


  }

}
