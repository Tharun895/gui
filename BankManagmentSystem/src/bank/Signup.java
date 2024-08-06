// Signup.java

package bank;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

public class Signup extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l9, l10, l11, l12, l13, l14, l8;
    JTextField tfName, tfFatherName, tfEmail, tfAddress, tfCity, tfPin, tfState;
    JRadioButton rbnMale, rbnFemale, rbnMarried, rbnUnmarried, rbnOthers;
    JButton btnNext;
    JDateChooser dateChooser;

    Random r = new Random();
    long randNum = r.nextLong() % 9000 + 1000;
    String number = "" + Math.abs(randNum);

    public Signup() {
        setLayout(null);
        setTitle("New Accounts Application Form");

        l1 = new JLabel("Application Form: " + number);
        l1.setFont(new Font("Raleway", Font.BOLD, 40));
        l1.setBounds(150, 20, 530, 40);
        add(l1);

        l2 = new JLabel("Personal Details of Customer");
        l2.setFont(new Font("Arial", Font.BOLD, 25));
        l2.setBounds(200, 80, 600, 30);
        add(l2);

        l3 = new JLabel("Name:");
        l3.setFont(new Font("Arial", Font.BOLD, 22));
        l3.setBounds(100, 130, 200, 30);
        add(l3);

        tfName = new JTextField();
        tfName.setBounds(270, 130, 350, 30);
        tfName.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(tfName);

        l4 = new JLabel("Father's Name:");
        l4.setFont(new Font("Arial", Font.BOLD, 22));
        l4.setBounds(100, 180, 200, 30);
        add(l4);

        tfFatherName = new JTextField();
        tfFatherName.setBounds(270, 180, 350, 30);
        tfFatherName.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(tfFatherName);

        l5 = new JLabel("Date of Birth:");
        l5.setFont(new Font("Arial", Font.BOLD, 22));
        l5.setBounds(100, 230, 200, 30);
        add(l5);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(270, 230, 200, 30);
        dateChooser.setForeground(new Color(200, 0, 0));
        add(dateChooser);

        l6 = new JLabel("Gender:");
        l6.setFont(new Font("Arial", Font.BOLD, 22));
        l6.setBounds(100, 280, 200, 30);
        add(l6);

        rbnMale = new JRadioButton("Male");
        rbnMale.setFont(new Font("Tahoma", Font.BOLD, 14));
        rbnMale.setBackground(Color.white);
        rbnMale.setBounds(270, 280, 100, 30);
        add(rbnMale);

        rbnFemale = new JRadioButton("Female");
        rbnFemale.setFont(new Font("Tahoma", Font.BOLD, 14));
        rbnFemale.setBackground(Color.white);
        rbnFemale.setBounds(370, 280, 100, 30);
        add(rbnFemale);

        ButtonGroup bgGender = new ButtonGroup();
        bgGender.add(rbnMale);
        bgGender.add(rbnFemale);

        l9 = new JLabel("Email Address:");
        l9.setFont(new Font("Arial", Font.BOLD, 22));
        l9.setBounds(100, 330, 200, 30);
        add(l9);

        tfEmail = new JTextField();
        tfEmail.setBounds(270, 330, 350, 30);
        tfEmail.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(tfEmail);

        l10 = new JLabel("Marital Status:");
        l10.setFont(new Font("Arial", Font.BOLD, 22));
        l10.setBounds(100, 380, 200, 30);
        add(l10);

        rbnMarried = new JRadioButton("Married");
        rbnMarried.setFont(new Font("Tahoma", Font.BOLD, 14));
        rbnMarried.setBackground(Color.white);
        rbnMarried.setBounds(270, 380, 100, 30);
        add(rbnMarried);

        rbnUnmarried = new JRadioButton("Unmarried");
        rbnUnmarried.setFont(new Font("Tahoma", Font.BOLD, 14));
        rbnUnmarried.setBackground(Color.white);
        rbnUnmarried.setBounds(370, 380, 120, 30);
        add(rbnUnmarried);

        rbnOthers = new JRadioButton("Others");
        rbnOthers.setFont(new Font("Tahoma", Font.BOLD, 14));
        rbnOthers.setBackground(Color.white);
        rbnOthers.setBounds(490, 380, 120, 30);
        add(rbnOthers);

        ButtonGroup bgMaritalStatus = new ButtonGroup();
        bgMaritalStatus.add(rbnMarried);
        bgMaritalStatus.add(rbnUnmarried);
        bgMaritalStatus.add(rbnOthers);

        l11 = new JLabel("Address:");
        l11.setFont(new Font("Arial", Font.BOLD, 22));
        l11.setBounds(100, 430, 200, 30);
        add(l11);

        tfAddress = new JTextField();
        tfAddress.setBounds(270, 430, 350, 30);
        tfAddress.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(tfAddress);

        l12 = new JLabel("City:");
        l12.setFont(new Font("Arial", Font.BOLD, 22));
        l12.setBounds(100, 480, 200, 30);
        add(l12);

        tfCity = new JTextField();
        tfCity.setBounds(270, 480, 350, 30);
        tfCity.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(tfCity);

        l13 = new JLabel("Pin Code:");
        l13.setFont(new Font("Arial", Font.BOLD, 22));
        l13.setBounds(100, 530, 200, 30);
        add(l13);

        tfPin = new JTextField();
        tfPin.setBounds(270, 530, 350, 30);
        tfPin.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(tfPin);

        l14 = new JLabel("State:");
        l14.setFont(new Font("Arial", Font.BOLD, 22));
        l14.setBounds(100, 580, 200, 30);
        add(l14);

        tfState = new JTextField();
        tfState.setBounds(270, 580, 350, 30);
        tfState.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(tfState);

        l8 = new JLabel("Date");
        l8.setFont(new Font("Raleway", Font.BOLD, 14));
        l8.setBounds(100, 630, 200, 30);
        add(l8);

        btnNext = new JButton("Next");
        btnNext.setFont(new Font("Arial", Font.BOLD, 14));
        btnNext.setBackground(Color.black);
        btnNext.setForeground(Color.white);
        btnNext.setBounds(600, 630, 100, 30); // Set the bounds for btnNext
        add(btnNext);
        btnNext.addActionListener(this);

        getContentPane().setBackground(Color.white);
        setSize(800, 750); // Increase the size of the frame to accommodate the new elements
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        Signup obj = new Signup();
    }

    public void actionPerformed(ActionEvent e) {
        String formno = number;
        String name = tfName.getText();
        String fname = tfFatherName.getText();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if (rbnMale.isSelected()) {
            gender = "Male";
        } else if (rbnFemale.isSelected()) {
            gender = "Female";
        }

        String email = tfEmail.getText();
        String marital = null;
        if (rbnMarried.isSelected()) {
            marital = "Married";
        } else if (rbnUnmarried.isSelected()) {
            marital = "Unmarried";
        } else if (rbnOthers.isSelected()) {
            marital = "Others";
        }

        String address = tfAddress.getText();
        String city = tfCity.getText();
        String pin = tfPin.getText();
        String state = tfState.getText();

        try {
            if (tfName.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Fill all the required fields");
            } else {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagment", "root", "Tanjirokamado#8");
                Statement stm = con.createStatement();
                String query = "insert into signup values('" + formno + "', '" + name + "', '" + fname + "', '" + dob + "', '" + gender + "', '" + email + "', '" + marital + "', '" + address + "', '" + city + "', '" + pin + "', '" + state + "')";
                stm.executeUpdate(query);

                new SignupTwo(formno).setVisible(true); // Pass the formno to SignupTwo
                setVisible(false);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
