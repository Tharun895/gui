package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Withdrawl extends JFrame implements ActionListener {
    private String pin;
    private JLabel l2;
    private JTextField tfWithdrawal;
    private JButton btnWithdraw, btnCancel;

    public Withdrawl(String pin) {
        this.pin = pin;
        setTitle("Withdrawal Page");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(null);
        
        l2 = new JLabel("Enter Amount to Withdraw:");
        l2.setFont(new Font("Tahoma", Font.BOLD, 18));
        l2.setBounds(50, 50, 300, 30);
        add(l2);

        tfWithdrawal = new JTextField();
        tfWithdrawal.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tfWithdrawal.setBounds(50, 100, 300, 30);
        add(tfWithdrawal);
        
        btnWithdraw = new JButton("Withdraw");
        btnWithdraw.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnWithdraw.setBounds(50, 150, 120, 40);
        add(btnWithdraw);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnCancel.setBounds(230, 150, 120, 40);
        add(btnCancel);

        btnWithdraw.addActionListener(this);
        btnCancel.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnCancel) {
            dispose();
        } else if (ae.getSource() == btnWithdraw) {
            String amountStr = tfWithdrawal.getText().trim();
            if (amountStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter the amount to withdraw.");
                return;
            }

            Connection con = null;
            PreparedStatement pstmtBalance = null;
            PreparedStatement pstmtUpdate = null;
            ResultSet rs = null;

            try {
                double withdrawalAmount = Double.parseDouble(amountStr);

                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagment", "root", "Tanjirokamado#8");

                pstmtBalance = con.prepareStatement("SELECT amount FROM bank WHERE pin = ?");
                pstmtBalance.setString(1, pin);
                rs = pstmtBalance.executeQuery();

                if (rs.next()) {
                    double currentBalance = rs.getDouble("amount");

                    if (withdrawalAmount > currentBalance) {
                        JOptionPane.showMessageDialog(null, "Low Balance. You do not have sufficient funds.");
                    } else {
                        pstmtUpdate = con.prepareStatement("UPDATE bank SET amount = amount - ? WHERE pin = ?");
                        pstmtUpdate.setDouble(1, withdrawalAmount);
                        pstmtUpdate.setString(2, pin);

                        int rowsAffected = pstmtUpdate.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Withdrawal of Rs " + withdrawalAmount + " successful.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: Unable to process withdrawal.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid PIN.");
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Database connection issue.");
            } finally {
                // Close resources in finally block
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (pstmtBalance != null) {
                    try {
                        pstmtBalance.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (pstmtUpdate != null) {
                    try {
                        pstmtUpdate.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Withdrawl frame = new Withdrawl("");  // Replace with actual PIN if needed
        frame.setVisible(true);
    }
}
