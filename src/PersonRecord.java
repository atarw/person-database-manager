/**
 * The PersonRecord class is used as a data class which stores data about one person, and uses methods to access and
 * change the data within each PersonRecord object. The data of each object is accessed and edited within the record editor program
 * created by this project. The class also contains private methods to format data inputted by the user for the names and phone
 * number data fields.
 * 
 * @author Atharva Washimkar
 * @version 1.0 03.12.15
 * 
 * <p>
 * <b>Instance Variables:</b>
 * <p>
 * <b>firstName</b> A private String object which stores the first name of a PersonRecord object.
 * <p>
 * <b>lastName</b> A private String object which stores the last name of a PersonRecord object.
 * <p>
 * <b>email</b> A private String object which stores the email of a PersonRecord object.
 * <p>
 * <b>phoneNum</b> A private String object which stores the phone number of a PersonRecord object.
 * <p>
 * <b>numOfRecords</b> A static int which stores the total number of PersonRecord instances which are not blank.
 */
public class PersonRecord {
  
  private String firstName = "", lastName = "", email = "", phoneNum = "";
  static int numOfRecords = 0;
  
  /**Description of getFirstName ()
    * A public method which returns the String firstName and its value.
    *
    * @return The String firstName and its value
    */
  public String getFirstName (){
    return firstName;
  }
  
  /**Description of getLastName ()
    * A public method which returns the String lastName and its value.
    *
    * @return The String lastName and its value
    */
  public String getLastName (){
    return lastName;
  }
  
  /**Description of getEmail ()
    * A public method which returns the String email and its value.
    *
    * @return The String email and its value
    */
  public String getEmail (){
    return email;
  }
  
  /**Description of getPhoneNum ()
    * A public method which returns the String phoneNum and its value.
    *
    * @return The String phoneNum and its value
    */
  public String getPhoneNum (){
    return phoneNum;
  }
  
  /**Description of setFirstName ()
    * Sets the value of the firstName variable through a given String parameter. The String parameter is
    * formatted before it is assigned to the firstName variable.
    *
    * @param newFirstName The String representing the new first name to replace the old firstName value
    * 
    * <p>
    * <b>Local Variable Dictionary</b>
    * <p>
    * <b>newFirstName</b> The String parameter passed into the method which contains the value of the new first name that will replace the old value of the firstName variable
    */
  public void setFirstName (String newFirstName){
    firstName = formatName (newFirstName);
  }
  
  /**Description of setLastName ()
    * Sets the value of the lastName variable through a given String parameter. The String parameter is
    * formatted before it is assigned to the lastName variable.
    *
    * @param newLastName The String representing the new last name to replace the old lastName value
    * 
    * <p>
    * <b>Local Variable Dictionary</b>
    * <p>
    * <b>newLastName</b> The String parameter passed into the method which contains the value of the new last name that will replace the old value of the lastName variable
    */
  public void setLastName (String newLastName){
    lastName = formatName (newLastName);
  }
  
  /**Description of setEmail ()
    * Sets the value of the email variable through a given String parameter.
    *
    * @param newEmail the String representing the new email to replace the old email value
    * 
    * <p>
    * <b>Local Variable Dictionary</b>
    * <p>
    * <b>newEmail</b> The String parameter passed into the method which contains the value of the new email that will replace the old value of the email variable
    */
  public void setEmail (String newEmail){
    email = newEmail;
  }
  
  /**Description of setPhoneNum ()
    * Sets the value of the phoneNum variable through a given String parameter. The String parameter is
    * formatted before it is assigned to the phoneNum variable.
    *
    * @param newPhoneNum the new phone number to replace the old phoneNum value
    * 
    * <p>
    * <b>Local Variable Dictionary</b>
    * <p>
    * <b>newPhoneNum</b> The String parameter passed into the method which contains the value of the new phone number that will replace the old value of the phoneNum variable
    */
  public void setPhoneNum (String newPhoneNum){
    phoneNum = formatPhoneNum (newPhoneNum);
  }
  
  /**Description of formatName ()
    * This method is used to format the first and last names. A given String is formatted so that each word in the name after a space or dash
    * will be capitalized, with the rest of each word set to lowercase. However, if the given String is null or empty,
    * the String is returned with no changes made.
    *
    * @param name The String parameter which contains the name to be formatted
    * @return The String which contains the value of the formatted name
    * 
    * <p>
    * <b>Local Variable Dictionary</b>
    * <p>
    * <b>name</b> The String parameter passed into the method which contains the value of the name to be formatted and returned
    * <p>
    * <b>i</b> The int variable used within the for loop which capitalizes each word within the String that is after a space or dash
    * <p>
    * The first if statement checks if the String is null or empty, and if it is, then the name value is returned with no changes made.
    * 
    * The purpose of the for loop is to capitalize the first letter of each word coming directly after a dash or space. The 
    * for loop uses the variable i, which starts at a value of 0, increments by 1 and runs as long as i is less than the length
    * of the String name.
    * 
    * Within the for loop is an if statement which checks whether the character within name at the position of i is equal to a space or
    * dash, and if it is, name's value is set to a substring of name from 0 to i, plus the next letter capitalized, plus the rest of the string.
    * 
    * This process continues until the name has been correctly formatted, and is then returned.
    */
  private String formatName (String name){
    
    if (name == null || name.equals (""))
      return name;
    
    name = name.toLowerCase ().trim ();
    name = name.substring (0, 1).toUpperCase () + name.substring (1);
    
    for (int i = 0; i < name.length ();i++){
      if (name.charAt (i) == ' ' || name.charAt (i) == '-'){
        name = name.substring (0, i) + name.substring (i, i + 2).toUpperCase () + name.substring (i + 2);
      }
    }
    return name;
  }
  
  /** Description of formatPhoneNum ()
    * This method is used to format and return a phone number given as a parameter. The phone number will be formatted
    * to be in the format XXX-XXX-XXXX with the X representing each digit. The phone number String is formatted to remove
    * all non-digit characters (using regex), and is then returned with dashes between three groups of three, three and four
    * digits respectively (to look like XXX-XXX-XXXX).
    *
    * @param phoneNumber the phone number which is to be formatted by the method
    * @return the String phoneNumber which has been formatted
    * 
    * <p>
    * <b>Local Variable Dictionary</b>
    * <p>
    * <b>phoneNumber</b> The String parameter passed into the method which contains the value of the unformatted phone number
    * <p>
    * The first if statement is used to check whether or not the phone number given is null or empty, if so then the phoneNumber
    * variable is returned, unformatted.
    */
  
  private String formatPhoneNum (String phoneNumber){
    if (phoneNumber == null || phoneNumber.equals (""))
      return phoneNumber;
    phoneNumber = phoneNumber.replaceAll ("[^0-9]","");
    
    return phoneNumber.substring (0, 3) + "-" + phoneNumber.substring (3, 6) + "-" + phoneNumber.substring (6);
  }
  
  /**
   * Instantiates a new PersonRecord with the first name, last name, email and phone number values passed in as parameters.
   * The private instance variables firstName, lastName, email and phoneNum are set to the values passed in.
   *
   * @param firstName The String parameter passed into the constructor which is then assigned to the private firstName instance variable.
   * @param lastName The String parameter passed into the constructor which is then assigned to the private lastName instance variable.
   * @param phoneNum The String parameter passed into the constructor which is then assigned to the private phoneNum instance variable.
   * @param email The String parameter passed into the constructor which is then assigned to the private email instance variable.
   * 
   * <p>
   * <b>Local Variable Dictionary</b>
   * <p>
   * <b>firstName</b> The String parameter passed into the constructor which is then assigned to the private firstName instance variable.
   * <p>
   * <b>lastName</b> The String parameter passed into the constructor which is then assigned to the private lastName instance variable.
   * <p>
   * <b>email</b> The String parameter passed into the constructor which is then assigned to the private email instance variable.
   * <p>
   * <b>phoneNum</b> The String parameter passed into the constructor which is then assigned to the private phoneNum instance variable.
   */
  public PersonRecord(String firstName, String lastName, String phoneNum, String email) { 
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNum = phoneNum;
  }
  
  /**
   * Instantiates a new PersonRecord with the first name, last name and phone number values passed in as parameters.
   * The private instance variables firstName, lastName and phoneNum are set to the values passed in.
   *
   * @param firstName The String parameter passed into the constructor which is then assigned to the private firstName instance variable.
   * @param lastName The String parameter passed into the constructor which is then assigned to the private lastName instance variable.
   * @param phoneNum The String parameter passed into the constructor which is then assigned to the private phoneNum instance variable.
   * 
   * <p>
   * <b>Local Variable Dictionary</b>
   * <p>
   * <b>firstName</b> The String parameter passed into the constructor which is then assigned to the private firstName instance variable.
   * <p>
   * <b>lastName</b> The String parameter passed into the constructor which is then assigned to the private lastName instance variable.
   * <p>
   * <b>phoneNum</b> The String parameter passed into the constructor which is then assigned to the private phoneNum instance variable.
   */
  
  public PersonRecord (String firstName, String lastName, String phoneNum){
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNum = phoneNum;
  }
  
  /**
   * Instantiates a new PersonRecord with the first name and last name values passed in as parameters.
   * The private instance variables firstName and lastName are set to the values passed in.
   *
   * @param firstName The String parameter passed into the constructor which is then assigned to the private firstName instance variable.
   * @param lastName The String parameter passed into the constructor which is then assigned to the private lastName instance variable.
   * 
   * <p>
   * <b>Local Variable Dictionary</b>
   * <p>
   * <b>firstName</b> The String parameter passed into the constructor which is then assigned to the private firstName instance variable.
   * <p>
   * <b>lastName</b> The String parameter passed into the constructor which is then assigned to the private lastName instance variable.
   */
  
  public PersonRecord (String firstName, String lastName){
    this.firstName = firstName;
    this.lastName = lastName;
  }
  
  /**
   * Instantiates a new PersonRecord with the first name value passed in as a parameter.
   * The private instance variable firstName is set to the value passed in.
   *
   * @param firstName The String parameter passed into the constructor which is then assigned to the private firstName instance variable.
   * 
   * <p>
   * <b>Local Variable Dictionary</b>
   * <p>
   * <b>firstName</b> The String parameter passed into the constructor which is then assigned to the private firstName instance variable.
   */
  
  public PersonRecord (String firstName){
    this.firstName = firstName;
  }
  
  /**
   * Instantiates a new PersonRecord with default values assigned to the variables firstName, lastName, email and phoneNum.
   * The default variable is an empty String.
   */
  
  public PersonRecord (){
    this.firstName = "";
    this.lastName = "";
    this.email = "";
    this.phoneNum = "";
  }
}