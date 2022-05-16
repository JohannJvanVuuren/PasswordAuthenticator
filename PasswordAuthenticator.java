import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Formatter;

/*
 * A program to authenticate a password entered by the user. The program will
 * reject passwords that do not match and that does not contain at least one
 * number. This will be communicated to the user. Finally, all passwords entered
 * by the user will be written to a file, password.txt.
 */

public class PasswordAuthenticator {

  public static void main(String[] args) {

    /*
     * State variable to control whether the while loop is executed. This variable
     * is updated at the end of the body of the while loop.
     */
    boolean tryAgain = true;

    /*
     * While statement to allow user to try again if an attempt results in a
     * rejected password.
     */
    while (tryAgain) {

      /*
       * Declaration and assignment of strings needed for the getPassword() method as
       * well as the variable that will store the successfully created password.
       */
      String PASSWORD_REQUEST_ONE = "Please enter a passsword that contains at least one number";
      String PASSWORD_REQUEST_TWO = "Please confirm the password";
      String finalPassword = "";

      /*
       * Reading the two passwords entered by the user using the getPassword() method
       * and then writing the password to the "password.txt" using the
       * writePasswordToFile() method.
       */
      String passwordOne = getUserInput(PASSWORD_REQUEST_ONE);
      writePasswordToFile(passwordOne);
      String passwordTwo = getUserInput(PASSWORD_REQUEST_TWO);
      writePasswordToFile(passwordTwo);

      /*
       * First section of defensive code to prevent user errors when entering the
       * passwords. This code will test if the two password match and then if there is
       * at least one number in the password. Boolean variables are assigned values
       * based on these tests that will be used to determine output to the user.
       */
      boolean hasNumber = false;
      boolean doesCharactersMatch = true;
      for (int i = 0; i < passwordOne.length(); i++) {
        /* Checking if passwords match (length as well as character for character) */
        if (passwordOne.length() != passwordTwo.length() || passwordOne.charAt(i) != passwordTwo.charAt(i)) {
          doesCharactersMatch = false;
        }
        for (int j = 0; j <= 9; j++) {
          /* Converting the index number j to a character. */
          String indexToString = Integer.toString(j);
          char numberAsCharacter = indexToString.charAt(0);

          /* Checking if the first password has at least one number character. */
          if (passwordOne.charAt(i) == numberAsCharacter) {
            hasNumber = true;
          }
        }
      }

      /*
       * First part of the user output that will tell the user if the password is
       * acceptable or not.
       */
      if (hasNumber == false || doesCharactersMatch == false) {
        System.out.println("The password could not be created sucessfully.\n");
      } else {
        finalPassword = passwordOne;
        System.out.println("The password, " + finalPassword + ", was successfully created\n");

        /*
         * Updating of state variable of while loop to prevent further iterations
         * determined by the if statement at the end of the program.
         */
        tryAgain = false;
      }

      /*
       * Second part of the user feedback to inform the user what the reasons were for
       * rejecting the password.
       */
      if (hasNumber == false && doesCharactersMatch == false) {
        System.out.println("The passwords did not match and did not contain at least one number.\n");
      } else if (hasNumber == false) {
        System.out.println("The password did not contain at least one number.\n");
      } else if (doesCharactersMatch == false) {
        System.out.println("The passwords did not match.\n");
      }

      /*
       * Updating of the state variable "tryAgain" based on user choice gathered as
       * user input using the getUserInput() method. If the user chooses "N", the
       * variable will change to false and the loop will end.
       */
      if (tryAgain == true) {
        String REENTERPASSWORD = "Would you like to re-enter the password? (Y/N)";
        String userChoice = getUserInput(REENTERPASSWORD);
        if (userChoice.equalsIgnoreCase("n")) {
          tryAgain = false;
        }
      }
    }
  }

  /* A generic method for reading user input. */
  public static String getUserInput(String question) {

    /*
     * Declaration and initialisation of variable that will store the read input and
     * that will be returned when the method is invoked in main().
     */
    String input = "";

    /*
     * Creation of an instance of the BufferedReader and InputStreamReader classes
     * to read text from a character-input stream.
     */
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    System.out.println(question);

    /*
     * Defensive programming to catch any IOExceptions thrown by the BufferedReader
     * instance.
     */
    try {
      input = reader.readLine();
    } catch (IOException e) {
      /* Returns the error message associated with the exception. */
      e.getMessage();
      /* Returns the call stack associated with the error. */
      e.printStackTrace();
      /* A more user friendly printout of the exception that occurred. */
      System.out.println("Exception thrown while accessing information using streams, files and directories.");
    }

    return input;

  }

  /*
   * A method to write each password that the user enter, to a file
   * "password.txt".
   */
  public static void writePasswordToFile(String password) {
    try {
      /*
       * Declaration and assignment of the variables used to store the file to which
       * the passwords must be written as well as a file path to this file. The use of
       * System.getProperty("file.separator") is to ensure the path will work across
       * Windows and Unix/Linux OSs.
       */
      String FILE_TO_WRITE_TO = "password.txt";

      File targetFile = new File(FILE_TO_WRITE_TO);

      /*
       * Creation of instances of FileWriter and Formatter which is needed to write
       * the password to file.
       */
      FileWriter writer = new FileWriter(targetFile, true);
      Formatter form = new Formatter(writer);

      /* Writing of the password to the "password.txt" file. */
      form.format("%s", password + "\n");
      form.close();

      /*
       * Defensive programming to catch any IOExceptions that might be thrown by the
       * FileWriter instance.
       */
    } catch (IOException e) {
      e.getMessage();
      e.printStackTrace();
      System.out.println("Exception thrown while accessing information using streams, files and directories.");
    }

  }
}
