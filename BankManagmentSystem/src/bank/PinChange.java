package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PinChange extends JFrame implements ActionListener {

    String pin;
    JLabel lblOldPin, lblNewPin, lblConfirmPin;
    JPasswordField pfOldPin, pfNewPin, pfConfirmPin;
    JButton b5, btnCancel;

    public PinChange(String pin) {
        this.pin = pin;
        setTitle("Change PIN");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        lblOldPin = new JLabel("Enter Old PIN:");
        lblOldPin.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblOldPin.setBounds(50, 50, 300, 30);
        add(lblOldPin);

        pfOldPin = new JPasswordField();
        pfOldPin.setFont(new Font("Tahoma", Font.PLAIN, 16));
        pfOldPin.setBounds(250, 50, 150, 30);
        add(pfOldPin);

        lblNewPin = new JLabel("Enter New PIN:");
        lblNewPin.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewPin.setBounds(50, 100, 300, 30);
        add(lblNewPin);

        pfNewPin = new JPasswordField();
        pfNewPin.setFont(new Font("Tahoma", Font.PLAIN, 16));
        pfNewPin.setBounds(250, 100, 150, 30);
        add(pfNewPin);

        lblConfirmPin = new JLabel("Confirm New PIN:");
        lblConfirmPin.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblConfirmPin.setBounds(50, 150, 300, 30);
        add(lblConfirmPin);

        pfConfirmPin = new JPasswordField();
        pfConfirmPin.setFont(new Font("Tahoma", Font.PLAIN, 16));
        pfConfirmPin.setBounds(250, 150, 150, 30);
        add(pfConfirmPin);

        b5 = new JButton("Change PIN");
        b5.setFont(new Font("Tahoma", Font.BOLD, 16));
        b5.setBounds(50, 200, 150, 40);
        add(b5);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnCancel.setBounds(250, 200, 150, 40);
        add(btnCancel);

        b5.addActionListener(this);
        btnCancel.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window on exit
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b5) {
            String oldPin = new String(pfOldPin.getPassword());
            String newPin = new String(pfNewPin.getPassword());
            String confirmPin = new String(pfConfirmPin.getPassword());

            if (!newPin.equals(confirmPin)) {
                JOptionPane.showMessageDialog(null, "New PIN and Confirm PIN do not match.");
                return;
            }

            Connection con = null;
            PreparedStatement pstmt = null;

            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagment?useSSL=false&requireSSL=false", "root", "Tanjirokamado#8");
                pstmt = con.prepareStatement("UPDATE bank SET pin = ? WHERE pin = ?");
                pstmt.setString(1, newPin);
                pstmt.setString(2, oldPin);

                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "PIN change successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Old PIN is incorrect.");
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Database issue.");
            } finally {
                // Close resources in the finally block
                try {
                    if (pstmt != null) pstmt.close();
                    if (con != null) con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (ae.getSource() == btnCancel) {
            dispose(); // Close the PIN change window
        }
    }

    public static void main(String[] args) {
        new PinChange(""); // Replace with a PIN if needed for testing
    }
}
