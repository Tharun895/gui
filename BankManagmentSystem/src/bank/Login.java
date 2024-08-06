package bank;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {
    // instance variables
    JLabel lblWelcome, lblCardno, lblpinno;
    JTextField tfcardno;
    JPasswordField pfpinno;
    JButton btnLogin, btnClear, btnSignup;

    public Login() {
        setTitle("Bank Management System");
        setLayout(null);

        lblWelcome = new JLabel("Welcome to Bank ");
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 35));
        lblWelcome.setBounds(270, 40, 530, 40);
        lblWelcome.setForeground(new Color(34, 139, 34)); // Forest green color
        add(lblWelcome);

        lblCardno = new JLabel("Enter Card No:");
        lblCardno.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblCardno.setBounds(90, 110, 400, 30);
        lblCardno.setForeground(new Color(0, 0, 128)); // Navy blue color
        add(lblCardno);

        tfcardno = new JTextField(20);
        tfcardno.setBounds(300, 110, 350, 30);
        tfcardno.setFont(new Font("Tahoma", Font.BOLD, 25));
        tfcardno.setBackground(new Color(230, 230, 250)); // Lavender color
        add(tfcardno);

        lblpinno = new JLabel("Enter Pin No:");
        lblpinno.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblpinno.setBounds(90, 170, 350, 30);
        lblpinno.setForeground(new Color(0, 0, 128)); // Navy blue color
        add(lblpinno);

        pfpinno = new JPasswordField(20);
        pfpinno.setBounds(300, 170, 350, 30);
        pfpinno.setFont(new Font("Tahoma", Font.BOLD, 25));
        pfpinno.setBackground(new Color(230, 230, 250)); // Lavender color
        add(pfpinno);

        btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(65, 105, 225)); // Royal blue color
        btnLogin.setForeground(Color.WHITE);

        btnClear = new JButton("Clear");
        btnClear.setBackground(new Color(178, 34, 34)); // Firebrick color
        btnClear.setForeground(Color.WHITE);

        btnSignup = new JButton("Sign Up");
        btnSignup.setBackground(new Color(255, 165, 0)); // Orange color
        btnSignup.setForeground(Color.WHITE);

        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 25));
        btnLogin.setBounds(300, 250, 110, 40);
        add(btnLogin);

        btnClear.setFont(new Font("Tahoma", Font.BOLD, 25));
        btnClear.setBounds(420, 250, 120, 40);
        add(btnClear);

        btnSignup.setFont(new Font("Tahoma", Font.BOLD, 25));
        btnSignup.setBounds(550, 250, 140, 40);
        add(btnSignup);

        btnLogin.addActionListener(this);
        btnClear.addActionListener(this);
        btnSignup.addActionListener(this);

        getContentPane().setBackground(new Color(255, 250, 240)); // Floral white color
        setVisible(true);
        setSize(800, 500);
        setLocation(400, 200);
    }

    public static void main(String[] args) {
        new Login();
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == btnLogin) {
                String cardno = tfcardno.getText();
                String pin = new String(pfpinno.getPassword());

                if (cardno.equals("") || pin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Card Number or PIN cannot be empty");
                    return;
                }

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagment", "root", "Tanjirokamado#8");
                String query = "SELECT * FROM bank WHERE cardno = ? AND pin = ?";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, cardno);
                pstmt.setString(2, pin);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Card Number or PIN");
                }
                rs.close();
                pstmt.close();
                con.close();
            } else if (ae.getSource() == btnClear) {
                tfcardno.setText("");
                pfpinno.setText("");
            } else if (ae.getSource() == btnSignup) {
                setVisible(false);
                new Signup().setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
