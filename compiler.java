import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class compiler{
  static ArrayList<String> data = new ArrayList();
  static void checkLines(String[] line){
      addToData(line[1].contains("array") ? true :false, line);
}
static void addToData(Boolean checkArray,String[] line){
  int dataType = line[1].contains("real") ? 4 : line[1].contains("integer") ? 2 : 1;
  String a = line[1].replaceAll("[^0-9]", "");
  String[] variables = line[0].split(",");
  if(checkArray)
    for(int i=0; i<a.length(); i+=2)
      dataType *= (int)(a.charAt(i+1) - a.charAt(i) + 1);
  for(int i=0; i < variables.length; i++)
    data.add(variables[i]+" " + dataType);
}
public static void main(String[] args) {
  try {
    File file = new File("input.txt");
    FileWriter writer = new FileWriter("output.txt");
    Scanner reader = new Scanner(file);
    while(reader.hasNextLine())
      checkLines(reader.nextLine().split(":"));
    data.sort(String::compareToIgnoreCase);
    int i=0;
    for(String a : data)
      writer.write(i++ == data.size()-1 ? a : a + "\n" );
    reader.close();
    writer.close();
  } catch (IOException e) {
    System.out.println("error");
    e.printStackTrace();
  }
}
}