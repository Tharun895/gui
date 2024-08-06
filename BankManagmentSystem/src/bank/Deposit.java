package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Deposit extends JFrame implements ActionListener {

    String pin;
    JLabel lblAmount;
    JTextField tfAmount;
    JButton btnDeposit, btnCancel;

    public Deposit(String pin) {
        this.pin = pin;
        setTitle("Deposit");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        lblAmount = new JLabel("Enter Amount to Deposit:");
        lblAmount.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblAmount.setBounds(50, 50, 300, 30);
        add(lblAmount);

        tfAmount = new JTextField();
        tfAmount.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tfAmount.setBounds(50, 100, 300, 30);
        add(tfAmount);

        btnDeposit = new JButton("Deposit");
        btnDeposit.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnDeposit.setBounds(50, 150, 120, 40);
        add(btnDeposit);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnCancel.setBounds(230, 150, 120, 40);
        add(btnCancel);

        btnDeposit.addActionListener(this);
        btnCancel.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window on exit
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnDeposit) {
            String amountStr = tfAmount.getText();
            try {
                double amount = Double.parseDouble(amountStr);

                // Database connection and query execution
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagment?useSSL=false&requireSSL=false", "root", "Tanjirokamado#8");

                String query = "UPDATE bank SET amount = amount + ? WHERE pin = ?";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setDouble(1, amount);
                pstmt.setString(2, pin);

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Deposit successful!");
                }

                pstmt.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Invalid amount or database issue.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
            }
        } else if (ae.getSource() == btnCancel) {
            dispose(); // Close the deposit window
        }
    }

    public static void main(String[] args) {
        new Deposit(""); // Replace with a PIN if needed for testing
    }
}
