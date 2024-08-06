package bank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

public class FastCash extends JFrame implements ActionListener {
    JLabel l1, l2;
    JButton b1, b2, b3, b4, b5, b6, b7;
    JTextField tf1;
    String pin;

    public FastCash(String pin) {
        this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/realistic-a-atm-machine-vector-29665834.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 1100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 960, 1000);
        add(l3);

        l1 = new JLabel("Select Withdrawal Amount");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));

        b1 = new JButton("Rs 100");
        b2 = new JButton("Rs 500");
        b3 = new JButton("Rs 1000");
        b4 = new JButton("Rs 2000");
        b5 = new JButton("Rs 5000");
        b6 = new JButton("Rs 10000");
        b7 = new JButton("Back");

        setLayout(null);
        l1.setBounds(290, 370, 700, 35);
        l3.add(l1);

        b1.setBounds(240, 415, 150, 35);
        l3.add(b1);
        b2.setBounds(420, 415, 150, 35);
        l3.add(b2);
        b3.setBounds(240, 460, 150, 35);
        l3.add(b3);
        b4.setBounds(420, 460, 150, 35);
        l3.add(b4);
        b5.setBounds(240, 505, 150, 35);
        l3.add(b5);
        b6.setBounds(420, 505, 150, 35);
        l3.add(b6);
        b7.setBounds(420, 550, 150, 35);
        l3.add(b7);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);

        setSize(960, 1080);
        setLocation(500, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FastCash("").setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == b7) {
                setVisible(false);
                new Transactions(pin).setVisible(true);
                return;
            }

            String amountStr = ((JButton) ae.getSource()).getText().substring(3).trim();
            double amount = Double.parseDouble(amountStr);

            ConnectionFactory cf = new ConnectionFactory();
            ResultSet rs = cf.stmt.executeQuery("SELECT amount FROM bank WHERE pin='" + pin + "'");
            double balance = 0;
            if (rs.next()) {
                balance = rs.getDouble("amount");
            }

            if (balance < amount) {
                JOptionPane.showMessageDialog(null, "Insufficient Balance");
                return;
            }

            double newBalance = balance - amount;
            cf.stmt.executeUpdate("UPDATE bank SET amount='" + newBalance + "' WHERE pin='" + pin + "'");
            JOptionPane.showMessageDialog(null, "Rs." + amount + " Debited successfully");
            setVisible(false);
            new Transactions(pin).setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
