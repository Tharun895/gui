package bank;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class BalanceCheck extends JFrame {

    public BalanceCheck(String pin) {
        setTitle("Balance Enquiry");
        setLayout(new BorderLayout());

        // Header Label
        JLabel headerLabel = new JLabel("Balance Enquiry", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        headerLabel.setBackground(new Color(0, 102, 204)); // Dark blue
        headerLabel.setForeground(Color.WHITE); // White text
        headerLabel.setOpaque(true); // Make the background color visible
        add(headerLabel, BorderLayout.NORTH);

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(Color.WHITE);

        // Balance Label
        JLabel balanceLabel = new JLabel("Loading balance...", SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        balanceLabel.setForeground(new Color(0, 102, 0)); // Dark green
        contentPanel.add(balanceLabel, BorderLayout.CENTER);

        // Fetch and display balance
        fetchBalance(pin, balanceLabel);

        // Add content panel to frame
        add(contentPanel, BorderLayout.CENTER);

        // Set frame properties
        setSize(400, 200);
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    // Method for fetching balance
    private void fetchBalance(String pin, JLabel balanceLabel) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagment?useSSL=false&requireSSL=false", "root", "Tanjirokamado#8");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT amount FROM bank WHERE pin = '" + pin + "'");

            if (rs.next()) {
                balanceLabel.setText("Your current balance is Rs " + rs.getDouble("amount"));
            } else {
                balanceLabel.setText("No account found for the provided PIN.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            balanceLabel.setText("Error fetching balance.");
        } finally {
            // Close resources
            closeResources(rs, stmt, con);
        }
    }

    // Method for closing resources
    private void closeResources(ResultSet rs, Statement stmt, Connection con) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BalanceCheck("").setVisible(true); // Test with a sample PIN
    }
}
