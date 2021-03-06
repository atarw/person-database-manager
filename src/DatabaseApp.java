import java.awt.event.*;
import javax.swing.*;

/**
 * The DatabaseApp class is the class which creates the JFrame window, menu bar, menus and menu items. The menu items are
 * all added to the menus, which are then added to the menu bar. Action listeners are added to make the menu items perform
 * an action when clicked. The window is set to be non-sizable, visible and have a size of 500x300. The window is made
 * to be able to close if the user presses the 'x' button.
 * 
 * @author Atharva Washimkar
 * @version 1.0 03.12.15
 * 
 * <p>
 * <b>Instance Variable Dictionary:</b>
 * <p>
 * <b>a</b> This is an object of the AddressBook class which is added to the screen.
 */
public class DatabaseApp extends JFrame implements ActionListener{
  
  AddressBook a = new AddressBook ();
  
  /** Description of actionPerformed (ActionEvent ae)
    * This method is used to listen for any action events and perform the appropriate action based on the menu item
    * pressed.
    *
    * @param ae is an object of the class ActionEvent which is taken as a parameter when the method is
    * invoked, by a componenent defined action.
    * 
    * An if statement is used to perform the appropriate action based on what the user pressed. If the actionCommand of
    * the action performed was "New Record", the method newFile () from the class AddressBook is performed onto the AddressBook object a.
    * Else if the actionCommand of the action performed was "Open Record", the method openFile () from the class AddressBook is performed onto the
    * AddressBook object a.
    * Else if the actionCommand of the action performed was "Save Record", the method saveFile () from the class AddressBook is performed onto the
    * AddressBook object a.
    * Else if the actionCommand of the action performed was "Save Record As", the method saveAsFile () from the class AddressBook is performed onto the
    * AddressBook object a.
    * Else if the actionCommand of the action performed was "Quit", the method quitProgram () from the class AddressBook is performed onto the
    * AddressBook object a.
    * Else if the actionCommand of the action performed was "Valid Input", the method showValidInputHelp () is performed.
    * A nested if statement is kept within the else statement to call the method aboutProgram () if the actionCommand of the action performed
    * was "About".
    **/
  
  public void actionPerformed (ActionEvent ae){
    if (ae.getActionCommand ().equals ("New Record"))
      a.newFile ();
    else if (ae.getActionCommand ().equals ("Open Record"))
      a.openFile ();
    else if (ae.getActionCommand ().equals ("Save Record"))
      a.saveFile ();
    else if (ae.getActionCommand ().equals ("Save Record As"))
      a.saveAsFile ();
    else if (ae.getActionCommand ().equals ("Quit"))
      a.quitProgram ();
    else if (ae.getActionCommand ().equals ("Valid Input"))
      showValidInputHelp ();
    else {
      if (ae.getActionCommand ().equals ("About"))
        aboutProgram ();
    }
  }
  
  /**Description of showValidInputHelp ()
    * This method is called when the user chooses the Valid Input option from the Help Menu. It opens a JOptionPane dialog box
    * which contains information on what is considered valid input by the program for various fields.
    * 
    * <p>
    * <b>Local Variable Dictionary:</b>
    * <p>
    * <b>msg</b> A String variable which stores the message outputted to the user. Note that the String's content is formatted
    * to increase code readability.
    */
  
  public void showValidInputHelp () {
    String msg = "<html><b>Valid Input:</b><br><br><u>First Name/Last Name:</u> The first and last name can be inputted normally with no restrictions.<br><br>" + 
      "<u>Email:</u> The email must be inputted in the format <i>name@example.com</i><br>The only special characters allowed within the name are periods, underscores and dashes, while none are allowed in the domain.<br><br>" +
      "<u>Phone Number:</u> The phone number must be inputted in one of these three formats (with X representing each digit):<br><br>" +
      "> <i>XXXXXXXXXX</i><br>" + 
      "> <i>XXX-XXX-XXXX</i><br>" + 
      "> <i>XXX XXX XXXX</i><br></html>";
    JOptionPane.showMessageDialog (null, msg, "Valid Input Information", JOptionPane.INFORMATION_MESSAGE);
  }
  
  /**Description of aboutProgram ()
    * This method is called when the user chooses the About item from the Help Menu. It opens a JOptionPane dialog box which
    * displays the name of the programmer responsible for the program.
    */
  
  public void aboutProgram () {
    JOptionPane.showMessageDialog (null, "This program was designed by Atharva Washimkar.", "About the Programmer", JOptionPane.INFORMATION_MESSAGE);
  }
  
  /**
   * The class constructor firstly creates a title for the window by passing a String to the superclass (JFrame) constructor.
   * It then creates a menu bar, two menus (File and Help), and various menu items (New, Open, Save, Save as, Quit, Valid Input, About).
   * 
   * The menu items are added to specific menus, which are then added to the menu bar. Action listeners are then added to all the menu items
   * in order to make them perform an action when pressed. The object a of the class AddressBook is then added to the window, and the JMenubar
   * is set to the menu bar created in the constructor.
   * 
   * The window size is set to 500x300, and is made to be visible and non-resizeable. The window's location is set to appear in
   * the center of the screen, and the close operation is set to make the application window do nothing once the close button is pressed.
   * 
   * Finally, a windowListener is added to the JFrame window, and a specific listener is added to the close button to make it call the quitProgram ()
   * method.
   * 
   * <p>
   * <b>Local Variables:</b>
   * <p>
   * <b>mainMenuBar</b> This creates an instance of the JMenuBar class which holds the JMenu instances created and is displayed on the screen.
   * <p>
   * <b>fileMenu</b> This creates an instance of the JMenu class called "File" which is displayed on screen.
   * <p>
   * <b>helpMenu</b> This creates an instance of the JMenu class called "Help" which is displayed on screen.
   * <p>
   * <b>newItem</b> This creates an instance of the JMenuItem class called "New Record" which is displayed on screen.
   * <p>
   * <b>openItem</b> This creates an instance of the JMenu class called "Open Record" which is displayed on screen.
   * <p>
   * <b>saveItem</b> This creates an instance of the JMenu class called "Save Record" which is displayed on screen.
   * <p>
   * <b>saveAsItem</b> This creates an instance of the JMenu class called "Save Record As" which is displayed on screen.
   * <p>
   * <b>quitItem</b> This creates an instance of the JMenu class called "Quit" which is displayed on screen.
   * <p>
   * <b>validInputItem</b> This creates an instance of the JMenu class called "Valid Input" which is displayed on screen.
   * <p>
   * <b>aboutItem</b> This creates an instance of the JMenu class called "About" which is displayed on screen.
   * <p>
   * <b>ev</b> A WindowEvent object which is taken as a parameter when the method is
   * invoked, by a window defined action.
   */
  
  public DatabaseApp() {
    super ("Record Keeper - Atharva");
    
    JMenuBar mainMenuBar = new JMenuBar ();
    
    JMenu fileMenu = new JMenu ("File");
    JMenu helpMenu = new JMenu ("Help");
    
    JMenuItem newItem = new JMenuItem ("New Record");
    JMenuItem openItem = new JMenuItem ("Open Record");
    JMenuItem saveItem = new JMenuItem ("Save Record");
    JMenuItem saveAsItem = new JMenuItem ("Save Record As");
    JMenuItem quitItem = new JMenuItem ("Quit");
    JMenuItem validInputItem = new JMenuItem ("Valid Input");
    JMenuItem aboutItem = new JMenuItem ("About");
    
    fileMenu.add (newItem);
    fileMenu.add (openItem);
    fileMenu.add (saveItem);
    fileMenu.add (saveAsItem);
    fileMenu.add (quitItem);
    helpMenu.add (validInputItem);
    helpMenu.add (aboutItem);
    
    mainMenuBar.add (fileMenu);
    mainMenuBar.add (helpMenu);
    
    setJMenuBar (mainMenuBar);
    
    add (a);
    
    newItem.addActionListener (this);
    openItem.addActionListener (this);
    saveItem.addActionListener (this);
    saveAsItem.addActionListener (this);
    quitItem.addActionListener (this);
    validInputItem.addActionListener (this);
    aboutItem.addActionListener (this);
    
    setSize (600, 400);
    setVisible (true);
    setLocationRelativeTo (null);
    setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
    
    this.addWindowListener (new WindowAdapter (){
      public void windowClosing (WindowEvent ev){
        a.quitProgram ();
      }});
  }
  
  /** Description of main(String [] args)
    * This method calls the Atharva2BApp constructor to
    * create the application. It also sets the look and feel of the application to the system look and feel,
    * in order to make the application better blend in with the other programs owned by the user.
    * 
    * @param args []  String array that allows command line
    * parameters to be used when executing the program.
    * 
    * <p>
    * <b>Local Variable Dictionary:</b>
    * <p>
    * <b>args</b> A String array that allows command line parameters to be used when executing the program.
    * <p>
    * <b>e</b> An Exception variable which is used to catch any exceptions which may occur by setting the look and feel
    * of the program.
    * <p>
    * A try block is used to set the look and feel of the program UI to whatever the look and feel used on the user's computer
    * OS is. The catch block catches any exceptions which may occur by doing this.
    */ 
  
  public static void main(String[] args){
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (Exception e){
    }
    new DatabaseApp ();
  }  
}