import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.util.*;

/**
 * The AddressBook class is responsible for creating, saving, opening, modifying and displaying record data through the use
 * of various GUI elements such as buttons, labels and text fields. The user is able to add and edit records using the buttons, 
 * as well as navigate the database using two arrow key buttons which loop around. There is no limit on the amount of records
 * that can be stored within a database. The user can open, save, and create new databases using the program.
 * 
 * @author Atharva Washimkar
 * @version 1.1 03.12.15
 * 
 * <p>
 * <b>Instance Variable Dictionary:</b>
 * <p>
 * <b>currentRecord</b> A static int which stores the current index of the database the user is currently accessing.
 * <p>
 * <b>lastRecord</b> An int which stores the value of the last accessed index of the database.
 * <p>
 * <b>currentFile</b> A String which stores the file name of the file currently accessed, if one is being accessed.
 * <p>
 * <b>isSaved</b> A boolean value which determines whether or not the data displayed on the screen has been saved to a file.
 * <p>
 * <b>records</b> An ArrayList of PersonRecord which stores the data that is being accessed and modified by the program.
 * <p>
 * <b>firstName</b> An instance of the JLabel class which is named "First Name:"
 * <p>
 * <b>lastName</b> An instance of the JLabel class which is named "Last Name:"
 * <p>
 * <b>email</b> An instance of the JLabel class which is named "Email:"
 * <p>
 * <b>phoneNum</b> An instance of the JLabel class which is named "Phone Number:"
 * <p>
 * <b>recordCounter</b> An instance of the JLabel class which stores a String representing the currently accessed index over the total amount of indexes within the database.
 * <p>
 * <b>addEntry</b> An instance of the JButton class which is named "New Entry".
 * <p>
 * <b>updateEntry</b> An instance of the JButton class which is named "Edit Entry"
 * <p>
 * <b>deleteEntry</b> An instance of the JButton class which is named "Delete Entry"
 * <p>
 * <b>back</b> An instance of the JButton class which is named "Previous Entry"
 * <p>
 * <b>forward</b> An instance of the JButton class which is named "Next Entry"
 * <p>
 * <b>firstNameInput</b> An instance of the JTextField class which is used for displaying and editing first name data for each record
 * <p>
 * <b>lastNameInput</b> An instance of the JTextField class which is used for displaying and editing last name data for each record
 * <p>
 * <b>emailInput</b> An instance of the JTextField class which is used for displaying and editing email data for each record
 * <p>
 * <b>phoneNumInput</b> An instance of the JTextField class which is used for displaying and editing phone number data for each record
 *
 */
public class AddressBook extends JPanel {
  
  static int currentRecord = 0;
  int lastRecord = currentRecord;
  
  String currentFile = "";
  
  private boolean isSaved = true;
  
  ArrayList <PersonRecord> records = new ArrayList <PersonRecord> ();
  
  JLabel firstName = new JLabel ("First Name:");
  JLabel lastName = new JLabel ("Last Name:");
  JLabel email = new JLabel ("Email:");
  JLabel phoneNum = new JLabel ("Phone Number:");
  JLabel recordCounter = new JLabel (/*currentRecord == -1*/records.size () == 0 ? "0/0 (No entries have been created yet.)" : "Entry " + (currentRecord + 1) + "/" + (PersonRecord.numOfRecords + 1));
  
  JButton addEntry = new JButton ("New Entry");
  JButton updateEntry = new JButton ("Edit Entry");
  JButton back = new JButton ("Previous Entry");
  JButton forward = new JButton ("Next Entry");
  JButton deleteEntry = new JButton ("Delete Entry");
  
  JTextField firstNameInput = new JTextField ();
  JTextField lastNameInput = new JTextField ();
  JTextField emailInput = new JTextField ();
  JTextField phoneNumInput = new JTextField ();
  
  /**Description of newFile ()
    * 
    * This method is used to create a new database of records. All the data is reset to their default values,
    * including the GUI of the application, with the buttons resetting to their default functions and text,
    * and the fields disabling themselves. The method may also ask the user to save their data before clearing everything
    * if their work is unsaved.
    * 
    * The if statement checks for the value of the boolean value returned by the method savePrompt () which checks if the
    * data is unsaved, and if so, asks the user to save the data. If the value returned is true, the method will clear the data.
    */
  
  public void newFile (){
    if (modifyPrompt () && savePrompt ()) {
      
      records.clear ();
      PersonRecord.numOfRecords = records.size ();
      currentRecord = 0;
      isSaved = true;
      currentFile = "";
      
      loadEntry ();
      updateButtons (0);
      setFieldActivity (false);
    }
  }
  
  /**Description of openFile ()
    * 
    * This method allows the user to open a database file created by this program. The method calls methods to check
    * whether the current file open is saved, and to get the user's choice of file to open. Once the file chosen is
    * verified to be valid, the data from the file is loaded into the PersonRecord array using a for loop, and various
    * variables are updated, as well as GUI elements.
    * 
    * <p>
    * <b>Local Variable Dictionary:</b>
    * <p>
    * <b>isOpenable</b> A boolean variable determining whether or not the chosen file is able to be opened by the program
    * <p>
    * <b>line</b> A String variable which stores a line of text from the file as it is being read from.
    * <p>
    * <b>file</b> A File object which stores the file that the user wishes to open
    * <p>
    * <b>in</b> An object of the class BufferedReader which is used for opening and reading from the chosen file.
    * <p>
    * <b>e</b> An IOException/NumberFormatException variable which is used to catch any exceptions which may occur while reading the file
    * or parsing text for an integer value.
    * <p>
    * <b>i</b> An int value which is used within the for loop which assigns values from the file to the PersonRecord array.
    * 
    * The first if statement checks whether or not the value from the method modifyPrompt () is true or false.
    * If true, it then checks whether the value from the method savePrompt () is true or false. If true, then
    * the user's choice of file (recieved from a method) is assigned to the File object file.
    * 
    * The next if statement checks if file is not null, and if it isn't, then a try catch block is used to open the file, and
    * extract the data from it. An if statement is used to check if the file's first line is the correct header. If it is, the
    * next line is read, else, isOpenable is set to false. After the header is verified, the program reads the next line, and checks if it
    * is a valid integer above 0, and if it is, then PersonRecord.numOfRecords is set to the value of the integer, the records
    * ArrayList is cleared, and a for loop is used to read from the file, else isOpenable is set to false.
    * 
    * The for loop uses the variable i, which starts at 0, increments by 1 and runs the for loop as long as it is less than/equal to
    * the number of records. The for loop assigns values from the file to the PersonRecord array.
    * 
    * If an exception is caught, isOpenable is set to false.
    * 
    * Finally, at the end, if isOpenable is false, an error message is displayed, which allows the user to possibly open another file if they choose
    * the yes option, which would call openFile ().
    */
  
  public void openFile (){
    boolean isOpenable = true;
    String line = "";
    
    if (modifyPrompt () && savePrompt ()) {
      File file = getChosenFile (true);
      
      if (file != null && file.exists ()) {
        try {
          BufferedReader in = new BufferedReader (new FileReader (file));
          line = in.readLine ();
          
          if (line != null && line.equals ("Atharva's Record Files")){
            line = in.readLine ();
            
            if (Integer.parseInt (line) < 1){
              isOpenable = false;
            }
            else {
              PersonRecord.numOfRecords = Integer.parseInt (line);
              records.clear ();
              records.ensureCapacity (PersonRecord.numOfRecords);
              currentFile = file.toString ();
              
              for (int i = 0; i < PersonRecord.numOfRecords; i++){
                records.add (new PersonRecord ());
                records.get (i).setFirstName (in.readLine ());
                records.get (i).setLastName (in.readLine ());
                records.get (i).setEmail (in.readLine ());
                records.get (i).setPhoneNum (in.readLine ());
              }
              in.close ();
              isSaved = true;
              currentRecord = 0;
              loadEntry ();
              updateButtons (0);
              setFieldActivity (false);
            }
          }
          else{
            isOpenable = false;
          }
        }
        catch (IOException | NumberFormatException e){
          isOpenable = false; 
        }
      }
      else{
        if (file != null && !file.exists ())
          isOpenable = false;
      }
      if (!isOpenable){
        int choice = JOptionPane.showConfirmDialog (null, "This file may have been corrupted, is not compatible with this program or simply does not exist, making the file unopenable. Would you like to open another file?", "Error - Unable to Open File", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (choice == JOptionPane.YES_OPTION)
          openFile ();
      }
    }
  }
  
  /** Description of saveFile ()
    * 
    * This method is used to write the data within the database to a file created by the user (or a new file).
    * The method has various errortraps to check whether or not the file name is valid or not, and whether the write to the file
    * was successful. The method returns a boolean value indicating whether or not the write to the file was successful.
    * 
    * @return a boolean value indicating whether or not the save was successful
    * 
    * <p>
    * <b>Local Variable Dictionary</b>
    * <p>
    * <b>out</b> An instantiation of the PrintWriter class which is used to write the data to the file of the user's choosing.
    * <p>
    * <b>e</b> An Exception variable used to catch an IOException which may occur while writing to the file.
    * <p>
    * <b>i</b> An int variable used in the for loop which is used to write all the non-empty PersonRecords to file.
    * <p>
    * <b>didAddEdits</b> A boolean variable which stores the value returned from the method modifyPrompt ()
    * <p>
    * 
    * The first if statement checks if the value returned by modifyPrompt () is true, and if the file name is not empty.
    * 
    * If this if statement is not true, an if statement checks if the file name is empty, as well as if didAddEdits is true,
    * as well as if the database open is not empty by checking the size of the records ArrayList. If those conditions are
    * all true, the method calls saveAsFile () to get the file name from the user. Else, an if statement checks if the ArrayList
    * record size is 0, and if it is, it displays an error message and returns false, indicating that the save was not successful.
    * 
    * If the first if statement (which checked if the file name was empty, didAddEdits is true, and the size of the ArrayList)
    * is true, the method begins to start writing to the file specified by the user. 
    * The header and number of records + 1 are written to the file. A for loop is used to write the record data, which
    * uses the variable i, which starts at 0, increments by 1 and runs the for loop as long as it is less than/equal to
    * the total number of records.
    * 
    * A try catch is used while writing, and if an IOException occurs, an error message shows up, informing the user that the save failed, and
    * if they would like to try again. If they choose the yes option, then saveFile () is called and returned by this method (tail recursion!!).
    * Else, the saveFile () method returns false, indicating that the save did not work.
    */ 
  
  public boolean saveFile () {
    boolean didAddEdits = modifyPrompt ();
    
    if (didAddEdits && !isEmpty (currentFile) && records.size () != 0) {
      currentFile = addExtension (currentFile);
      try {
        PrintWriter out = new PrintWriter (new FileWriter (currentFile));
        
        out.println ("Atharva's Record Files");
        out.println (PersonRecord.numOfRecords);
        
        for (int i = 0; i < PersonRecord.numOfRecords; i++) {
          out.println (records.get (i).getFirstName ());
          out.println (records.get (i).getLastName ());
          out.println (records.get (i).getEmail ());
          out.println (records.get (i).getPhoneNum ());
        }
        out.close ();
        isSaved = true;
        return true;
      }
      catch (IOException e){
        isSaved = false;
        int choice = JOptionPane.showConfirmDialog (null, "An error occurred while saving the file. Would you like to try saving again?", "Error - Unable to Save File", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION)
          return saveFile ();
        else
          return false;
      }
    }
    else {
      if (isEmpty (currentFile) && didAddEdits && records.size () != 0)
        return saveAsFile ();
      else {
        if (records.size () == 0){
          JOptionPane.showMessageDialog (null, "You cannot save an empty file.", "Error - Empty File", JOptionPane.ERROR_MESSAGE);
          return false;
        }
      }
    }
    return true;
  }
  
  /** Description of saveAsFile ()
    * This method is responsible from getting a file name from the user to save a file as, and for calling the saveFile ()
    * method to actually save the file once the method gets the name. A boolean value is returned indicating whether the save
    * was successful.
    * 
    * @return a boolean value indicating whether or not the save was successful
    * 
    * <p>
    * <b>Local Variable Dictionary:</b>
    * <p>
    * <b>file</b> An object of the File class which stores the file choice the user wishes to save their database as.
    * <p>
    * <b>choice</b> An int which stores the choice of the user from the JOptionPane informing the user that the file
    * already exists and if they would like to overwrite the existing file.
    * <p>
    * 
    * The first if statement checks if any unadded changes are present within the database, and if the size of the record
    * ArrayList is not 0. If this if statement is false, an if statement checks if the database is empty and if it is,
    * then an error message is shown.
    * 
    * Else, if the method is true, an if statement checks whether or not any unadded changes are present and if the
    * database is not empty, in which case a JFileChooser instance opens to allow the user to choose their file name,
    * which is stored in a File object named file.
    * 
    * An if statement checks if the file object is not null and if the file already exists on the computer. If both
    * conditions are true, then a JOptionPane is shown which informs the user the file already exists, and if they want to
    * overwrite it. If they choose the yes option, currentFile is re-assigned, and the method saveFile () is
    * called, and the value from that method is returned.
    * 
    * Else, a nested if statement checks if the choice is no, in which case the method saveAsFile is called again to
    * allow the user to re-enter their file name. Else, the method returns false, as the save did not occur.
    * 
    * Else, if the file was null or the file did not exist, an if statement checks if the file is null, and if it isn't
    * currentFile is reassigned and the result of the method saveFile () is returned. Else, if the file was null, then 
    * the method returns false as the save was not successful.
    */
  
  public boolean saveAsFile (){
    if (modifyPrompt () && records.size () != 0){
      File file = getChosenFile (false);
      
      if (file != null && file.exists ()) {
        int choice = JOptionPane.showConfirmDialog (null, "A file with the chosen name already exists. Would you like to overwrite the file?", "File Already Exists", JOptionPane.YES_NO_CANCEL_OPTION);
        if (choice == JOptionPane.YES_OPTION){
          currentFile = addExtension (file.toString ());
          return saveFile ();
        }
        else {
          if (choice == JOptionPane.NO_OPTION)
            saveAsFile (); 
          else
            return false;
        }
      }
      else {
        if (file != null){
          currentFile = file.toString ();
          return saveFile ();
        }
        return false;
      }
    }
    else {
      if (records.size () == 0) {
        JOptionPane.showMessageDialog (null, "You cannot save an empty file.", "Error - Empty File", JOptionPane.ERROR_MESSAGE);
        return false;
      }
    }
    return true;
  }
  
  public void quitProgram (){
    if (modifyPrompt () && savePrompt ())
      System.exit (0);
  }
  
  public void addActionListeners (){
    
    addEntry.addActionListener(new ActionListener(){
      
      public void actionPerformed(ActionEvent ae){
        
        if (addEntry.getText ().equals ("New Entry")){
          records.add (new PersonRecord ());
          PersonRecord.numOfRecords = records.size ();
          lastRecord = currentRecord;
          currentRecord = PersonRecord.numOfRecords - 1;
          
          updateButtons (1);
          loadEntry ();
          setFieldActivity (true);
          firstNameInput.requestFocus ();
        }
        else {
          if (addEntry.getText ().equals ("Cancel New Entry")){
            records.remove (currentRecord);
            PersonRecord.numOfRecords = records.size ();
            currentRecord = lastRecord;
          }
          updateButtons (0);
          loadEntry ();
          setFieldActivity (false);
        }
      }});
    
    updateEntry.addActionListener(new ActionListener(){
      
      public void actionPerformed(ActionEvent ae){
        if (updateEntry.getText ().equals ("Edit Entry")) {
          updateButtons (2);
          setFieldActivity (true);
          loadEntry ();
          firstNameInput.requestFocus ();
        }
        else {
          if (isEntryValid ()){
            updateButtons (0);
            updateRecord ();
            loadEntry ();
            setFieldActivity (false);
          }
        }
      }});
    
    deleteEntry.addActionListener(new ActionListener(){
      
      public void actionPerformed(ActionEvent ae){
        records.remove (currentRecord);
        PersonRecord.numOfRecords = records.size ();
        currentRecord = (currentRecord >= records.size () ? currentRecord - 1 : currentRecord);
        loadEntry ();
        setFieldActivity (false);
        updateButtons (0);
        isSaved = false;
      }});
    
    back.addActionListener(new ActionListener(){
      
      public void actionPerformed(ActionEvent ae){
        currentRecord = (currentRecord == 0 ? PersonRecord.numOfRecords - 1 : currentRecord - 1);
        loadEntry ();
        setFieldActivity (false);
        updateButtons (0);
      }});
    
    forward.addActionListener(new ActionListener(){
      
      public void actionPerformed(ActionEvent ae){
        currentRecord = (currentRecord == PersonRecord.numOfRecords - 1 ? 0 : currentRecord + 1);
        loadEntry ();
        setFieldActivity (false);
        updateButtons (0);
      }});
  }
  
  private void updateButtons (int view){
    if (view == 0){
      addEntry.setText ("New Entry");
      updateEntry.setText ("Edit Entry");
    }
    else if (view == 1){
      addEntry.setText ("Cancel New Entry");
      updateEntry.setText ("Save New Entry");
    }
    else {
      addEntry.setText ("Cancel Edit");
      updateEntry.setText ("Save Edit");
    }
    updateEntry.setEnabled (records.size () == 0 ? false : true);
    forward.setEnabled (records.size () < 2 || view != 0 ? false : true);
    back.setEnabled (records.size () < 2 || view != 0 ? false : true);
    addEntry.setEnabled (true);
    deleteEntry.setEnabled (records.size () == 0 || view != 0 ? false : true);
  }
  
  private void loadEntry (){
    firstNameInput.setText (records.size () == 0 ? "" : records.get (currentRecord).getFirstName());
    lastNameInput.setText (records.size () == 0 ? "" :records.get (currentRecord).getLastName());
    emailInput.setText (records.size () == 0 ? "" :records.get (currentRecord).getEmail());
    phoneNumInput.setText (records.size () == 0 ? "" :records.get (currentRecord).getPhoneNum());
    recordCounter.setText (records.size () == 0 ? "0/0 (No entries have been created yet.)" : "Entry " + (currentRecord + 1) + "/" + PersonRecord.numOfRecords);
  }
  
  private void setFieldActivity (boolean isActive){
    firstNameInput.setEnabled (isActive);
    lastNameInput.setEnabled (isActive);
    emailInput.setEnabled (isActive);
    phoneNumInput.setEnabled (isActive);
  }
  
  private void updateRecord (){
    isSaved = false;    
    records.get (currentRecord).setFirstName (firstNameInput.getText ());
    records.get (currentRecord).setLastName (lastNameInput.getText ());
    records.get (currentRecord).setEmail (emailInput.getText ());
    records.get (currentRecord).setPhoneNum (phoneNumInput.getText ());
  }
  
  private boolean isEntryValid (){
    
    if (isEntryEmpty ()){
      JOptionPane.showMessageDialog (null, "At least one field must be filled in.", "Error - Empty Entry", JOptionPane.ERROR_MESSAGE);
      firstNameInput.requestFocus ();
      return false;
    }
    else {
      if (!isEmpty (emailInput.getText ()) && !DataCheck.isValidEmail (emailInput.getText ()) || !isEmpty (phoneNumInput.getText ()) && !DataCheck.isValidPhoneNum (phoneNumInput.getText ())){
        
        if (!isEmpty (phoneNumInput.getText ()) && !DataCheck.isValidPhoneNum (phoneNumInput.getText ())){
          JOptionPane.showMessageDialog (null, "The phone number entered is not valid. Please refer to the Valid Input section under the Help menu for assistance entering a phone number.", "Error - Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
          phoneNumInput.setText (null);
          phoneNumInput.requestFocus ();
        }
        
        if (!isEmpty (emailInput.getText ()) && !DataCheck.isValidEmail (emailInput.getText ())) {
          JOptionPane.showMessageDialog (null, "The email entered is not valid. Please refer to the Valid Input section under the Help menu for assistance entering an email.", "Error - Invalid Email", JOptionPane.ERROR_MESSAGE);
          emailInput.setText (null);
          emailInput.requestFocus ();
        }
        return false;
      }
      return true;
    }
  }
  
  private boolean isEmpty (String text) {
    return text == null || text.trim ().length () == 0;
  }
  
  private boolean isEntryEmpty (){
    return isEmpty (firstNameInput.getText ()) && isEmpty (lastNameInput.getText ()) && isEmpty (emailInput.getText ()) && isEmpty (phoneNumInput.getText ());
  }
  
  private String addExtension (String fileName){
    return fileName.length () < 4 || !fileName.toLowerCase ().endsWith (".ysl") ? fileName + ".ysl":
      fileName.substring (0, fileName.length () - 4) + ".ysl"; 
  }
  
  private boolean isRecordModified () {
    return !firstNameInput.getText ().equals (records.get (currentRecord) /*[currentRecord]*/.getFirstName ()) || !lastNameInput.getText ().equals (records.get (currentRecord) /*[currentRecord]*/.getLastName ()) || !emailInput.getText ().equals (records.get (currentRecord) /*[currentRecord]*/.getEmail ()) || !phoneNumInput.getText ().equals (records.get (currentRecord) /*[currentRecord]*/.getPhoneNum ());
  }
  
  private boolean modifyPrompt () {
    int choice = -1;
    
    if (firstNameInput.isEnabled ()) {
      choice = JOptionPane.showConfirmDialog (null, "Your changes were not made to the record. Would you like to add the changes and update the record before continuing?","Unadded Changes", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
      
      if (choice == JOptionPane.YES_OPTION || choice == JOptionPane.NO_OPTION) {
        if (choice == JOptionPane.YES_OPTION){
          if (isEntryValid ())
            updateRecord ();
          else
            return false;
        }
        else {
          if (addEntry.getText ().equals ("Cancel New Entry")){
            records.remove (currentRecord);
            PersonRecord.numOfRecords = records.size ();
            currentRecord = lastRecord;
          }
        }
        updateButtons (0);
        loadEntry ();
        setFieldActivity (false);
        return true;
      }
      else
        return false;
    }
    else
      return true;
  }
  
  private boolean savePrompt (){
    int choice = -1;
    
    if (!isSaved)
      choice = JOptionPane.showConfirmDialog (null, "Your data may not be saved if you continue. Do you wish to save first?", "Unsaved File", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
    
    if (isSaved || !isSaved && (choice == JOptionPane.YES_OPTION || choice == JOptionPane.NO_OPTION)){
      if (!isSaved && choice == JOptionPane.YES_OPTION){
        return saveFile ();
      }
      return true;
    }
    return false;
  }
  
  private File getChosenFile (boolean isOpenDialog){
    JFileChooser f = new JFileChooser ("..//Files");
    
    f.setMultiSelectionEnabled (false);
    f.setFileFilter (new FileNameExtensionFilter ("Record Storage Files","ysl"));
    int choice = isOpenDialog ? f.showOpenDialog (null) : f.showSaveDialog (null);
    
    if (choice == JFileChooser.CANCEL_OPTION || choice == JFileChooser.ERROR_OPTION)
      return null;
    return new File (addExtension (f.getSelectedFile ().toString ()));
  }
  
  private void setupScreen (){
    JPanel center = new JPanel ();
    JPanel bottom = new JPanel ();
    
    GroupLayout g = new GroupLayout (center);
    center.setLayout (g);
    
    g.setAutoCreateGaps (true);
    g.setAutoCreateContainerGaps (true);
    
    g.setHorizontalGroup(g.createSequentialGroup()
                           .addGroup(g.createParallelGroup(GroupLayout.Alignment.LEADING)
                                       .addComponent (firstName)
                                       .addComponent (lastName)
                                       .addComponent (email)
                                       .addComponent (phoneNum))
                           .addGroup(g.createParallelGroup(GroupLayout.Alignment.LEADING)
                                       .addComponent (firstNameInput)
                                       .addComponent (lastNameInput)
                                       .addComponent (emailInput)
                                       .addComponent (phoneNumInput)));
    g.setVerticalGroup(g.createSequentialGroup ()
                         .addGroup(g.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                     .addComponent (firstName)
                                     .addComponent (firstNameInput))
                         .addGroup(g.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                     .addComponent (lastName)
                                     .addComponent (lastNameInput))
                         .addGroup(g.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                     .addComponent (email)
                                     .addComponent (emailInput))
                         .addGroup(g.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                     .addComponent (phoneNum)
                                     .addComponent (phoneNumInput)));
    bottom.add (back);
    bottom.add (addEntry);
    bottom.add (updateEntry);
    bottom.add (deleteEntry);
    bottom.add (forward);
    
    center.setPreferredSize (new Dimension (550, 150));
    
    add (recordCounter, BorderLayout.NORTH);
    add (center, BorderLayout.WEST);
    add (bottom, BorderLayout.SOUTH);
  }
  
  public AddressBook () {
    
    updateButtons (0);
    setupScreen ();
    setFieldActivity (false);
    addActionListeners ();
  }
}