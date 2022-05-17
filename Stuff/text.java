package Stuff;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class text {
  public static void main(String[] args) {
    try {
      FileWriter myWriter = new FileWriter("C:\\Users\\Jacob\\OneDrive\\Documents\\VS Created Files\\filename.txt");
      for(int i = 0; i < 10000; i++){
          String j = i + "";
          if(j.length() < 4){
              int p = j.length();
              for(int k = 0; k < 4 - p; k++){
                  j= "0" + j;

              }
          }

        myWriter.write("\n" + j);
      }
      
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}