package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AmountTransfer extends JFrame implements ActionListener {

    String pin;
    JLabel lblSourceAccount, lblTargetAccount, lblAmount;
    JTextField tfSourceAccount, tfTargetAccount, tfAmount;
    JButton btnTransfer, btnCancel;

    public AmountTransfer(String pin) {
        this.pin = pin;
        setTitle("Amount Transfer");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        lblSourceAccount = new JLabel("Source Account Number:");
        lblSourceAccount.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblSourceAccount.setBounds(50, 50, 250, 30);
        add(lblSourceAccount);

        tfSourceAccount = new JTextField();
        tfSourceAccount.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tfSourceAccount.setBounds(300, 50, 150, 30);
        add(tfSourceAccount);

        lblTargetAccount = new JLabel("Target Account Number:");
        lblTargetAccount.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTargetAccount.setBounds(50, 100, 250, 30);
        add(lblTargetAccount);

        tfTargetAccount = new JTextField();
        tfTargetAccount.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tfTargetAccount.setBounds(300, 100, 150, 30);
        add(tfTargetAccount);

        lblAmount = new JLabel("Amount:");
        lblAmount.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblAmount.setBounds(50, 150, 250, 30);
        add(lblAmount);

        tfAmount = new JTextField();
        tfAmount.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tfAmount.setBounds(300, 150, 150, 30);
        add(tfAmount);

        btnTransfer = new JButton("Transfer");
        btnTransfer.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnTransfer.setBounds(50, 200, 150, 40);
        add(btnTransfer);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnCancel.setBounds(250, 200, 150, 40);
        add(btnCancel);

        btnTransfer.addActionListener(this);
        btnCancel.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window on exit
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnTransfer) {
            String sourceAccount = tfSourceAccount.getText();
            String targetAccount = tfTargetAccount.getText();
            String amountText = tfAmount.getText();

            try {
                double amount = Double.parseDouble(amountText);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(null, "Amount must be greater than zero.");
                    return;
                }

                performTransfer(sourceAccount, targetAccount, amount);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid amount format.");
            }
        } else if (ae.getSource() == btnCancel) {
            dispose(); // Close the transfer window
        }
    }

    private void performTransfer(String source, String target, double amount) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagment?useSSL=false&requireSSL=false", "root", "Tanjirokamado#8");

            // Check amount in source account
            String checkAmountQuery = "SELECT amount FROM bank WHERE cardno = ?";
            pst = conn.prepareStatement(checkAmountQuery);
            pst.setString(1, source);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                double sourceAmount = rs.getDouble("amount");
                if (sourceAmount < amount) {
                    JOptionPane.showMessageDialog(null, "Insufficient amount in source account.");
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Source account not found.");
                return;
            }

            // Check if target account exists
            String checkTargetQuery = "SELECT cardno FROM bank WHERE cardno = ?";
            pst = conn.prepareStatement(checkTargetQuery);
            pst.setString(1, target);
            rs = pst.executeQuery();
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Target account not found.");
                return;
            }

            // Update amounts
            conn.setAutoCommit(false);
            String updateSourceQuery = "UPDATE bank SET amount = amount - ? WHERE cardno = ?";
            pst = conn.prepareStatement(updateSourceQuery);
            pst.setDouble(1, amount);
            pst.setString(2, source);
            pst.executeUpdate();

            String updateTargetQuery = "UPDATE bank SET amount = amount + ? WHERE cardno = ?";
            pst = conn.prepareStatement(updateTargetQuery);
            pst.setDouble(1, amount);
            pst.setString(2, target);
            pst.executeUpdate();

            // Commit transaction
            conn.commit();
            JOptionPane.showMessageDialog(null, "Transfer successful!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Transfer failed: " + ex.getMessage());
            ex.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            try {
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new AmountTransfer(""); // Initialize with a dummy pin for testing
    }
}
