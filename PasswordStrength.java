package passwordAuth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
*
* @authors Victoria Baquiran & Alex Wolford
*/
public class PasswordStrength {

	public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        //prompts user to enter pw
        System.out.println("Enter Password: ");
        
        if(sc.hasNextLine()){
            String password = sc.nextLine();
            
            //read dictionary file line by line
            BufferedReader dFile = new BufferedReader(new FileReader("..\\passwordAuth\\dictionary.txt"));
            
            //store words in dictionary to strings in array list
            ArrayList<String> dWords = new ArrayList<String>();
            
            while(dFile.ready()){
                //Add all words from dictionary to arraylist
                String line = dFile.readLine();
                dWords.add(line);
            }
            
            //strength boolean to keep track of the password type 
            boolean strengthFound = false;
            
            // Will check to see if inputted password is in ArrayList
            if(dWords.contains(password) && !strengthFound){
                System.out.println("WEAK PASSWORD");
                strengthFound = true;
            }
            // Will check to see if inputted password contains a word in the arraylist with special characters/numbers 
            if(!strengthFound){
                //Check for special characters
                Pattern p = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(password);
                boolean specCharFound = m.find();

                //Goes through arraylist comparing dictionary word to password input
                for(int i = 0; i < dWords.size(); i++){
                    String word = dWords.get(i);
                    
                    //If the dictionary word is found in the password, plus with special characters, it is a moderate password
                    if((password.contains(word)) && specCharFound){
                        System.out.println("MODERATE PASSWORD");
                        strengthFound = true;
                        break;
                    }
                }
            }
            //If it is not a weak or moderate password aka strengthFound is still false, it is a strong password
            if(!strengthFound){
                System.out.println("STRONG PASSWORD");
                strengthFound = true;
            }      
        sc.close();
        dFile.close();
        }   
		
	}//main close

}//class close
	

