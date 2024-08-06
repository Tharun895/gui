package bank;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class MiniStatement extends JFrame {

    public MiniStatement(String pin) {
        setTitle("Mini Statement");
        setLayout(new BorderLayout());

        // Header label
        JLabel headerLabel = new JLabel("Bank of XYZ", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        headerLabel.setBackground(new Color(0, 102, 204)); // Dark blue background
        headerLabel.setForeground(Color.WHITE); // White text
        headerLabel.setOpaque(true); // Make the background color visible
        add(headerLabel, BorderLayout.NORTH);

        // Panel for transactions
        JPanel statementPanel = new JPanel();
        statementPanel.setLayout(new BoxLayout(statementPanel, BoxLayout.Y_AXIS));
        statementPanel.setBackground(new Color(245, 245, 245)); // Light grey background

        // Scroll pane for transactions
        JScrollPane scrollPane = new JScrollPane(statementPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // Balance label
        JLabel balanceLabel = new JLabel();
        balanceLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        balanceLabel.setForeground(new Color(0, 153, 0)); // Dark green color
        balanceLabel.setHorizontalAlignment(SwingConstants.LEFT);
        balanceLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        balanceLabel.setBackground(Color.WHITE); // White background for the balance
        balanceLabel.setOpaque(true); // Make the background color visible
        add(balanceLabel, BorderLayout.SOUTH);

        // Fetch and display statement information
        fetchStatement(pin, statementPanel, balanceLabel);

        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    // Method for fetching statement information
    private void fetchStatement(String pin, JPanel statementPanel, JLabel balanceLabel) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rsBalance = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagment?useSSL=false&requireSSL=false", "root", "Tanjirokamado#8");
            stmt = con.createStatement();

            // Fetch transaction history
            rs = stmt.executeQuery("SELECT transaction_date, amount FROM bank WHERE pin = '" + pin + "'");
            while (rs.next()) {
                String transactionDate = rs.getTimestamp("transaction_date").toString();
                double amount = rs.getDouble("amount");
                JLabel transactionLabel = new JLabel(String.format("<html>%s: Rs %.2f</html>", transactionDate, amount));
                transactionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
                transactionLabel.setForeground(new Color(0, 0, 0)); // Black color for text
                statementPanel.add(transactionLabel);
            }

            // Fetch current balance
            rsBalance = stmt.executeQuery("SELECT amount AS balance FROM bank WHERE pin = '" + pin + "' ORDER BY transaction_date DESC LIMIT 1");
            if (rsBalance.next()) {
                balanceLabel.setText("Your current balance is Rs " + rsBalance.getDouble("balance"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Close resources in the finally block
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
        new MiniStatement(" ").setVisible(true); // Test with a sample PIN
    }
}
