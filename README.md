# JavaPasswordAuthInstructions

1. Start with the Register Class
a. After running the program, console will prompt the user to enter a new username they would like to register
b. Once the desired username is entered, the console will prompt the user to enter their desired password to correspond with the username.
c. Upon pressing enter, if the username and password are successfully added to the users file, A thank you message is displayed.
2. The Login class
a. When run, the user is prompted in the console to enter a username that has been previously registered
b. After the username is entered the program asks the user to input the password that corresponds to the username that has just been entered
c. The password that is entered is hashed using the MD5 algorithm, then both the username and password are compared to the users file. 
If the username and hashed password match a line on the users file, the login is correct and a Succesful message is displayed
3. Cracker2 class
a. When run, the user is prompted via the console to enter a username whose password they would like to crack. 
b. Upon pressing Enter, the program reads the users file which contains usernames and MD5 hashes of their passwords.
c. The first loop checks to see if the user's password is an exact match to a dictionary word, if it is not,
it begins generating hashes of type two passwords and compares those to the user's hash in the users file. if there is
a match, the plaintext password is output to the console along with the runtime it took to find the plaintext password.
4. PasswordStrength Class
This program tests the strength of a particular password based on if it the word is contained in the password dictionary or if it contains any other
characters
a. When run, the program prompts the user in the console to enter a password they would like to test
b. When enter is pressed, the program first checks if the password is an exact match to a dictionary word, if so,
it is classified as a WEAK PASSWORD
c. If the password tested contains a portion or a complete dictionary word plus one or more of the following: 0,1,2,3,4,5,6,7,8,9,0,!,@,#,$,%,^,&,*,(,)
it is classified as a MODERATE PASSWORD
d. Finally if the tested password does not contain a portion of a dictionary word it is classified as a STRONG PASSWORD
