import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class ArtSmartPlanner extends JFrame
      implements ActionListener, java.io.Serializable {

   private static final long serialVersionUID = -4751598647816467380L;
   private JPanel contentPane; //Top-level frame of window 
   
   // Dimensions of the window
   private static final int FRAME_WIDTH = 840;
   private static final int FRAME_HEIGHT = 600;
   private final String PADDING = "----------";

   // GUI Components
   // North
   private JLabel nameLabel;
   private JLabel ageLabel = new JLabel("Age:");
   private JLabel daysLabel;
   private JButton addStudent;
   private JTextField nameField;
   private JTextField ageField = new JTextField("1", 3);
   private JCheckBox roboCheckBox;
   private JCheckBox artCheckBox;
   private JCheckBox halfDayCheckBox;
   private JLabel status;
   private JLabel dayViewingLabel;
   private JButton generatePairsButton = new JButton("Generate Pairs");
   private JButton load = new JButton("Load a file");
   private JButton save = new JButton("Save this file");
   private JFileChooser fc = new JFileChooser();
   private JPanel generatePairsPanel = new JPanel();

   // Center
   private JTextArea textArea1 = new JTextArea();
   private JTextArea textArea2 = new JTextArea();
   private JList list;
   private JScrollPane scroll1 = new JScrollPane(textArea1);
   private JScrollPane scroll2 = new JScrollPane(textArea2);

   // South
   private JTextField daysField;
   private JTextField calendarField;
   private JButton previousDay;
   private JButton nextDay;
   private JLabel calendarLabel;
   private JButton goDayButton;
   JScrollPane scroll4 = new JScrollPane();

   // JPanels
   private JPanel northPanel;
   private JPanel namePanel;
   private JPanel daysPanel_1;
   private JPanel southPanel;
   private JPanel activitiesPanel;
   private JPanel calendarPanel;
   private JPanel addStudentPanel;
   private JPanel centerPanel;

   // Student ArrayList
   private ArrayList<Student> students = new ArrayList<Student>();
   private int date = 1;
   private File file; // The current saved file
   private JList list_1;
   private JLabel lblArt;
   private JLabel lblRobotics;
   private DefaultListModel listModel;
   private JPanel panel;
   private JLabel lblAllStudents;
   private JButton btnRemoveSelectedStudent;
   private JButton btnViewSelectedStudent;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               ArtSmartPlanner frame = new ArtSmartPlanner();
               frame.setTitle("ArtSmart Planner");
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   @SuppressWarnings({ "unchecked", "rawtypes" })
   public ArtSmartPlanner() {
      textArea1.setEditable(false);
      textArea2.setEditable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(FRAME_WIDTH, FRAME_HEIGHT);
      contentPane = new JPanel();
      setContentPane(contentPane);

      nameLabel = new JLabel("Student name:");
      calendarLabel = new JLabel("View date:");
      daysLabel = new JLabel("Dates enrolled:");
      status = new JLabel("Enter new student");
      Font font = status.getFont();
      Font boldFont = new Font(font.getFontName(), Font.BOLD, 20);
      status.setFont(boldFont);
      JPanel statusPanel = new JPanel();
      statusPanel.add(status);

      dayViewingLabel = new JLabel("Viewing date " + date);
      Font boldFont2 = new Font(font.getFontName(), Font.BOLD, 16);
      dayViewingLabel.setFont(boldFont2);
      JPanel dayViewingPanel = new JPanel();
      dayViewingPanel.add(dayViewingLabel);

      // Initialize buttons/fields/check boxes
      nameField = new JTextField("Enter name", 30);
      roboCheckBox = new JCheckBox("Robotics", true);
      artCheckBox = new JCheckBox("Art", true);
      halfDayCheckBox = new JCheckBox("Half Day", false);
      daysField = new JTextField("1", 30);
      previousDay = new JButton("Previous Date");
      nextDay = new JButton("Next Date");
      goDayButton = new JButton("Go");
      calendarField = new JTextField("" + date, 3);
      addStudent = new JButton("Add student");

      // Initialize JPanels
      northPanel = new JPanel(new GridLayout(6, 1)); // 4 high, 1 wide
      centerPanel = new JPanel(new GridLayout(1, 3)); // 1 high, 3 wide
      southPanel = new JPanel(new GridLayout(2, 1));
      namePanel = new JPanel();
      activitiesPanel = new JPanel();
      daysPanel_1 = new JPanel();
      calendarPanel = new JPanel();
      addStudentPanel = new JPanel();

      // Add components to panels
      namePanel.add(nameLabel);
      namePanel.add(nameField);
      namePanel.add(ageLabel);
      namePanel.add(ageField);
      
      daysPanel_1.add(daysLabel);
      daysPanel_1.add(daysField);
      
      activitiesPanel.add(roboCheckBox);
      activitiesPanel.add(artCheckBox);
      
      daysPanel_1.add(daysLabel);
      daysPanel_1.add(daysField);
      daysPanel_1.add(halfDayCheckBox);
      
      calendarPanel.add(previousDay);
      calendarPanel.add(calendarLabel);
      calendarPanel.add(calendarField);
      calendarPanel.add(goDayButton);
      calendarPanel.add(nextDay);
      
      generatePairsPanel.add(generatePairsButton);
      generatePairsPanel.add(load);
      generatePairsPanel.add(save);
      addStudentPanel.add(addStudent);

      // Build top-level JPanels
      northPanel.add(statusPanel);
      northPanel.add(namePanel);
      northPanel.add(daysPanel_1);
      northPanel.add(activitiesPanel);
      northPanel.add(daysPanel_1);
      northPanel.add(addStudentPanel);
      northPanel.add(dayViewingPanel);
      southPanel.add(calendarPanel);
      southPanel.add(generatePairsPanel);
      centerPanel.add(scroll1);

      lblRobotics = new JLabel("ROBOTICS STUDENTS");
      lblRobotics.setHorizontalAlignment(SwingConstants.CENTER);
      scroll1.setColumnHeaderView(lblRobotics);
      centerPanel.add(scroll2);

      lblArt = new JLabel("ART STUDENTS");
      lblArt.setBackground(Color.WHITE);
      lblArt.setHorizontalAlignment(SwingConstants.CENTER);
      scroll2.setColumnHeaderView(lblArt);
      centerPanel.add(scroll4);

      listModel = new DefaultListModel();
      list_1 = new JList(listModel);
      list_1.setVisibleRowCount(-1);
      list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      scroll4.setViewportView(list_1);

      panel = new JPanel();
      panel.setBackground(Color.WHITE);
      scroll4.setColumnHeaderView(panel);
      panel.setLayout(new GridLayout(3, 0, 0, 0));

      lblAllStudents = new JLabel("ALL STUDENTS");
      lblAllStudents.setHorizontalAlignment(SwingConstants.CENTER);
      panel.add(lblAllStudents);

      btnRemoveSelectedStudent = new JButton("Remove selected student");
      btnRemoveSelectedStudent.setBackground(Color.WHITE);
      panel.add(btnRemoveSelectedStudent);

      btnViewSelectedStudent = new JButton("View selected student details");
      panel.add(btnViewSelectedStudent);

      contentPane.setLayout(new BorderLayout());
      contentPane.add(northPanel, BorderLayout.NORTH);
      contentPane.add(centerPanel, BorderLayout.CENTER);
      contentPane.add(southPanel, BorderLayout.SOUTH);

      // Bind buttons to event handler
      addStudent.addActionListener(this);
      previousDay.addActionListener(this);
      nextDay.addActionListener(this);
      goDayButton.addActionListener(this);
      generatePairsButton.addActionListener(this);
      load.addActionListener(this);
      save.addActionListener(this);
      btnRemoveSelectedStudent.addActionListener(this);
      btnViewSelectedStudent.addActionListener(this);
      this.requestFocus();
      System.out.println("Done");
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      String command = e.getActionCommand();

      // Add new student
      if (command.equals("Add student")) {
         addNewStudent();
         updateCalendar();
      }

      // Display next date
      if (command.equals("Next Date") && date < 31) {
         date++;
         calendarField.setText("" + date);
         updateCalendar();

      }

      // Display previous date
      if (command.equals("Previous Date") && date > 1) {
         date--;
         calendarField.setText("" + date);
         updateCalendar();
      }

      // View date go
      if (command.equals("Go")) {
         updateCalendar();
      }

      // Remove student
      if (e.getSource() == btnRemoveSelectedStudent) {
         removeStudent();
         updateCalendar();
      }

      // View student info
      if (e.getSource() == btnViewSelectedStudent) {
         viewStudentInfo();
      }

      // Pairing up students based on age
      if (command.equals("Generate Pairs")) {
         generatePairs();
         dayViewingLabel.setText("Pairs generated for date " + date);
      }

      // Loading a save file
      if (e.getSource() == load) {
         loadFile();
      }

      // Saving to a file
      if (e.getSource() == save) {
         saveFile();
      }

   }

   private void viewStudentInfo() {
      int i = list_1.getSelectedIndex();
      String info = null;
      String header = null;
      if (i == -1) {
         status.setText("Error: No student selected.");
      } else {
         try {
            header = students.get(i).getName() + "'s information";
            // Build
            info = students.get(i).getName() + "\n";
            info += "\t\t\t\t\t\t\t\tRobotics: " + students.get(i).isRobo()
                  + "\n";
            info += "\t\t\t\t\t\t\t\tArt: " + students.get(i).isArt() + "\n";
            info += "\t\t\t\t\t\t\t\tEnrolled days: "
                  + Arrays.toString(students.get(i).getDaysEnrolled().toArray())
                  + "\n";
            info += "\t\t\t\t\t\t\t\tHalf day: " + students.get(i).isHalf()
                  + "\n";

            // Display info in pop-up dialog box
            JOptionPane.showMessageDialog(contentPane, info, header,
                  JOptionPane.PLAIN_MESSAGE);
         } catch (Exception e) {
            status.setText("Error: Failed to view student. Try again.");
         }
      }

   }

   private boolean removeStudent() {

      int i = list_1.getSelectedIndex();
      if (i == -1) {
         status.setText("Error: No student selected.");
      } else {
         try {
            status.setText(
                  "Student " + students.get(i).getName() + " removed.");
            students.remove(i);
         } catch (Exception e) {
            status.setText("Error: Unable to remove that student. Try again.");
         }
      }
      return true;

   }

   private void saveFile() {
      int retrival = fc.showSaveDialog(null);
      if (retrival == JFileChooser.APPROVE_OPTION) {
         try {
            // Write to disk with FileOutputStream
            FileOutputStream f_out = new FileOutputStream(
                  fc.getSelectedFile() + ".artsmart");

            // Write object with ObjectOutputStream
            ObjectOutputStream obj_out = new ObjectOutputStream(f_out);

            // Write object out to disk
            obj_out.writeObject(students);
            obj_out.flush();
            obj_out.close();

         } catch (Exception ex) {
            ex.printStackTrace();
         }
      }
      updateCalendar();

   }

   private void loadFile() {
      int returnVal = fc.showOpenDialog(this);

      if (returnVal == JFileChooser.APPROVE_OPTION) {
         fc.setCurrentDirectory(new File("~/Desktop"));
         file = fc.getSelectedFile();

         // Read from disk using FileInputStream
         FileInputStream f_in;
         try {
            f_in = new FileInputStream(file);

            // Read object using ObjectInputStream
            ObjectInputStream obj_in = new ObjectInputStream(f_in);

            // Store the object
            Object obj = obj_in.readObject();

            if (obj instanceof ArrayList<?>) {
               // Cast object to an ArrayList<Student>
               // and store as current students list
               students = (ArrayList<Student>) obj;

            } else {
               status.setText("Error: Unable to load file " + file.getName());
            }

            obj_in.close();
         } catch (ClassNotFoundException | IOException e1) {
            e1.printStackTrace();
         }

         status.setText(file.getName() + " loaded!");
         System.out.println("Opening: " + file.getName() + ".");
      } else {
         System.out.println("Open command cancelled by user.");
      }
      updateCalendar();

   }

   private boolean updateCalendar() {
      try {
         date = Integer.parseInt(calendarField.getText());
         if (date < 1 || date > 31) {
            dayViewingLabel.setText(
                  "Error: Date out of bounds. Must be between 1 and 31.");
            return false;
         }
      } catch (NumberFormatException e) {
         dayViewingLabel.setText(
               "Error: Not a valid date value. Must be between 1 and 31.");
         return false;
      }

      ArrayList<Student> dayStudents = new ArrayList<Student>();
      for (int i = 0; i < students.size(); i++) {
         if (students.get(i).getDaysEnrolled().contains(date)) {
            dayStudents.add(students.get(i));
         }

      }

      // Reset textAreas
      textArea1.setText("");
      textArea2.setText("");

      // Add kids to appropriate textAreas
      for (int i = 0; i < dayStudents.size(); i++) {
         Student temp = dayStudents.get(i);

         if (temp.isRobo()) {
            textArea1.setText(textArea1.getText() + temp.toString() + "\n");
         }
         if (temp.isArt()) {
            textArea2.setText(textArea2.getText() + temp.toString() + "\n");
         }

      }

      dayViewingLabel.setText("Viewing date " + date);
      printAllStudents();
      return true;
   }

   private void printAllStudents() {
      listModel.removeAllElements();
      for (int i = 0; i < students.size(); i++) {
         listModel.addElement(students.get(i));
      }
   }

   public void addNewStudent() {
      try {
         ArrayList<Integer> daysEnrolled = parseDaysEnrolled();
         System.out.println(nameField.getText());
         Student s = new Student(nameField.getText());
         s.setArt(artCheckBox.isSelected());
         s.setRobo(roboCheckBox.isSelected());
         s.setHalf(halfDayCheckBox.isSelected());
         s.setDaysEnrolled(daysEnrolled);
         s.setAge(Integer.parseInt(ageField.getText()));
         students.add(s);
         status.setText("Student " + s.getName() + " added!");
      } catch (ArithmeticException e) {
         status.setText(
               "Error: Enrolled dates out of bounds. Must be between 1 and 31.");
      } catch (Exception e) {
         status.setText("Error: Improper number input.");
         e.printStackTrace();
      }

   }

   private ArrayList<Integer> parseDaysEnrolled() throws Exception {
      // Remove whitespace, convert to int array
      String input = daysField.getText().replaceAll("\\s", "");
      String[] split = input.split(",");

      System.out.println(Arrays.toString(split));
      Arrays.sort(split);

      ArrayList<Integer> converted = new ArrayList<Integer>();

      // Convert strings to integers
      for (int i = 0; i < split.length; i++) {
         int num = Integer.parseInt(split[i]);
         System.out.println(num);
         if (num < 1 || num > 31) {
            status.setText("Error: Enrolled dates out of bounds.");
            throw new ArithmeticException();
         }
         converted.add(new Integer(num));
      }

      return converted;
   }

   @SuppressWarnings("unchecked")
   private void generatePairs() {
      List temp = new ArrayList<Student>();
      for (int i = 0; i < students.size(); i++) {
         if (students.get(i).getDaysEnrolled().contains(date)) {
            temp.add(students.get(i));
         }
      }
      // Sort students by date
      Collections.sort(temp);
      System.out.println("sorted");

      textArea1.setText("");
      textArea2.setText("");

      for (int i = 0, j = 0; i < temp.size(); i++) {
         Student temp1 = (Student) temp.get(i);

         if (temp1.isRobo()) {
            // Newline every two entries
            if (j == 2) {
               textArea1.setText(textArea1.getText() + "\n");
               j = 0;
            }
            
            textArea1.setText(textArea1.getText() + temp1.toString() + "\n");
            j++;

         }
         if (temp1.isArt()) {
            // Line break between campers aged 6 and 7
            if (((Student) temp.get(i)).getAge() >= 7
                  && ((Student) temp.get(i - 1)).getAge() <= 6) {
               textArea2.setText(textArea2.getText() + PADDING + "\n");
            }

            textArea2.setText(textArea2.getText() + temp1.toString() + "\n");

         }

      }

   }

}
