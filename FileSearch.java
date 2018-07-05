import java.io.File;
import java.util.*;
import java.util.regex.*;
public class FileSearch{
   private String fileName;  //name of the file that'll be searched
   private static ArrayList<String> result = new ArrayList<String>(); //Create an ArrayList of type string to store the final path
   
   //getter function for filename
   public String getFileName() {
      return fileName;
   }
   //setter function for filename
   public void setFileName(String fileName) {
      this.fileName = fileName;
   }
   /**
   * getter function to get the result that is list of all matching files
   * @return Returns list of all matching files.
   */
   public List<String> getResult() {
      return result;
   }

public static void main(String[] args) {

      //create an obect of this class  
      FileSearch f = new FileSearch();
      Scanner input = new Scanner(System.in);
     while(true){ // user input
      	 System.out.println("Enter java regular expression to be matched with file name");
      String name=input.next();
result.clear();
         
         int count =0;  //to count the number of occurences of the file
         
         f.searchDirectory(new File("/home/zadmin"), name);

         count = f.getResult().size();

         if(count == 0){
            System.out.println("\nNo result found!");
         }
         else{
            System.out.println("\nFound " + count + " result!\n");
            for(String matched : f.getResult()){
               System.out.println("Found : " + matched);
            }
         }
      }//end of while
   }

   //checks whether directory is legal or not
   public void searchDirectory(File directory, String fileName){
      setFileName(fileName);
      if (directory.isDirectory()) {
         search(directory);
      }
      else{
         System.out.println(directory.getAbsoluteFile() + " is not a directory!");
      }
   }
/**
     * Prints the complete path if file matching regex is found. It searches in all directories and
     * subdirectories recursively.
     */
   private void search(File file){

      if (file.isDirectory()){
         for (File temp : file.listFiles()){
               if (temp.isDirectory()) {
                  search(temp);
               }
               //if it's a file and not a directory
               else {
                  //check regex, if true, then add it to results
                  String g = getFileName();
                  Pattern p = Pattern.compile(g);
                  Matcher m = p.matcher(temp.getName());  
                  boolean b = m.matches();  
                  if (b){
                     result.add(temp.getAbsoluteFile().toString());
                 }
               }
            }
         }
         else{
            System.out.println(file.getAbsoluteFile() + "Permission Denied");
         }
      }
   
}




