/**
 * The DataCheck class contains two static methods which are used within other classes to verify whether emails and
 * phone numbers inputted by the user are valid.
 * 
 * @author Atharva Washimkar
 * @version 1.0 03.12.15
 * 
 */
public class DataCheck {
  
  /**Description of isValidEmail ()
    * This method is used to verify whether or not a given email (given as a parameter) is valid, and returns a boolean
    * value indicating whether or not the email is correct. The method determines if the email is not null, if it matches
    * the format xxx@xxxxx.xxx, with no illegal characters (using regex). It also checks if the first character is not a period,
    * underscore or dash, and checks whether the last character is not a period or underscore. If any of those conditions
    * are false, the method will return false, indicating the String given is not a valid email.
    * 
    * @param email is the String containing the email which is then checked by the method.
    * @return a boolean value is returned indicating whether or not the given email is valid.
    * 
    * <p>
    * <b>Local Variables:</b>
    * <p>
    * <b>email</b> The String passed in which holds the email and is evaluated by the method.
    */
  public static boolean isValidEmail (String email){  
    return email != null && email.matches ("[\\w.-]{1,}\\@[\\w]{1,}\\.[\\w.]{1,}") && !email.substring (0, 1).matches ("[._-]") && !email.substring (email.length () - 1, email.length ()).matches ("[._]");
  }
  
  /**Description of isValidPhoneNum ()
    * This method is used to verify whether or not a given phone number (given as a parameter) is valid, and returns a boolean
    * value indicating whether or not the phone number is correct. The method determines if the phone number is not null.
    * It also checks if the phone number matches any of these approved formats: XXXXXXXXXX, XXX-XXX-XXXX or XXX XXX XXXX (using regex).
    * If the phone number is null or does not match the above formats, the method will return false,
    * indicating the String given is not a valid phone number.
    * 
    * @param phoneNum is the String containing the phone number which is then checked by the method.
    * @return a boolean value is returned indicating whether or not the given phone number is valid.
    * 
    * <p>
    * <b>Local Variables:</b>
    * <p>
    * <b>phoneNum</b> The String passed in which holds the phone number and is evaluated by the method.
    */
  public static boolean isValidPhoneNum (String phoneNum){
    return (phoneNum != null && (phoneNum.matches ("\\d{3}-\\d{3}-\\d{4}") || phoneNum.trim ().matches ("\\d{10}") || phoneNum.trim ().matches ("\\d{3} \\d{3} \\d{4}")));
  }
}