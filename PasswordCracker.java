package passwordAuth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
*
* @authors Victoria Baquiran & Alex Wolford
* This program executes a dictionary password attack
* User is prompted to enter an MD5 password hash. A dicionary of English words
* is used to find the corresponding plaintext password and it is displayed in the console along
* with the time it has taken to find the password
* It is also able to find passwords that contain a dictionary word and begin with a single digit or end with a single digit
* example: music8 or 8music
*/
public class PasswordCracker {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        System.out.println("Hash: ");

        if(sc.hasNextLine()){
        	long start = System.currentTimeMillis();
            String hash = sc.nextLine();
            
            // Reads from the user text file, puts values into arraylist
            BufferedReader userFile = new BufferedReader(new FileReader("..\\passwordAuth\\users.txt"));
            ArrayList<String> userInfo = new ArrayList<String>();
            
            while(userFile.ready()){
                String line = userFile.readLine();
                userInfo.add(line);
            }
            
            // Reads from the dictionary file
            BufferedReader dFile = new BufferedReader(new FileReader("..\\passwordAuth\\dictionary.txt"));
            ArrayList<String> dWords = new ArrayList<String>();
            
            while(dFile.ready()){
                String dLine = dFile.readLine();
                dWords.add(dLine);
            }
                        
            //Turns all words in dictionary into its hash value
            for(int i=0; i<dWords.size(); i++){
                String hashedDictionaryWord = JavaMD5Hash.md5(dWords.get(i));
                
                //Test for Type 1 passwords (exact dictionary word)
                for(int k=0; k<userInfo.size(); k++){
                    String infoLine = userInfo.get(k);
                    if(infoLine.contains(hash) && infoLine.contains(hashedDictionaryWord)){
                        System.out.println("**Cracked Password: " + dWords.get(i));
                        long finish = System.currentTimeMillis();
                        long timeElapsed = finish - start;
                        System.out.println("Cracked in " + (double)timeElapsed / 1000 +" seconds");
                    	}
                 }
            }
            
            //Test for dictionary password with one number at the beginning or end. 
            ArrayList<String> hashedPass = new ArrayList<String>();

            for(int i=0; i<dWords.size(); i++){
                String plusOne = dWords.get(i);
                String plusOneTop = dWords.get(i);

                int count = 0;

                while(count < 10){
                    //One number added to the end
                    plusOne = dWords.get(i);
                    plusOne = plusOne + String.valueOf(count);
                    String plusOneHashed = JavaMD5Hash.md5(plusOne);
                    
                    //One number added to the beginning/top
                    plusOneTop = dWords.get(i);
                    plusOneTop = String.valueOf(count) + plusOneTop;
                    String plusOneTopHashed = JavaMD5Hash.md5(plusOneTop);
                 
                    hashedPass.add(plusOneHashed);
                    hashedPass.add(plusOneTopHashed);

                    if(hash.equals(plusOneHashed)){
                        System.out.println("**Cracked Password: " + plusOne);
                        long finish = System.currentTimeMillis();
                        long timeElapsed = finish - start;
                        System.out.println("Cracked in " + (double)timeElapsed / 1000 +" seconds");
                    }
                    
                    if(hash.equals(plusOneTopHashed)){
                        System.out.println("**Cracked Password: " + plusOneTop);
                        long finish = System.currentTimeMillis();
                        long timeElapsed = finish - start;
                        System.out.println("Cracked in " + (double)timeElapsed / 1000 +" seconds");
                    }
                    count++;
                }
            }
            
            userFile.close();
            dFile.close();
            
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            
            System.out.println("Run time: " + (double)timeElapsed / 1000 +" seconds");
            }
        sc.close();
        }//end of if statement for scanner
       
    }// end of class

