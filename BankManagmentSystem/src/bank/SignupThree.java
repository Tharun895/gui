package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class SignupThree extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7;
    JRadioButton r1, r2, r3, r4;
    JCheckBox c1, c2, c3, c4, c5, c6, c7;
    JButton b1, b2;
    String formno;

    public SignupThree(String formno) {
        this.formno = formno;

        setTitle("New Account Application Form-Page-3");
        setLayout(null);

        l1 = new JLabel("Page-3: Account Details");
        l1.setFont(new Font("Arial", Font.BOLD, 25));
        l1.setBounds(280, 40, 400, 40);
        add(l1);

        l2 = new JLabel("Account Type:");
        l2.setFont(new Font("Arial", Font.BOLD, 20));
        l2.setBounds(100, 120, 200, 30);
        add(l2);

        r1 = new JRadioButton("Saving Account");
        r1.setFont(new Font("Arial", Font.BOLD, 16));
        r1.setBackground(Color.WHITE);
        r1.setBounds(100, 160, 200, 30);
        add(r1);

        r2 = new JRadioButton("Fixed Deposit Account");
        r2.setFont(new Font("Arial", Font.BOLD, 16));
        r2.setBackground(Color.WHITE);
        r2.setBounds(350, 160, 250, 30);
        add(r2);

        r3 = new JRadioButton("Current Account");
        r3.setFont(new Font("Arial", Font.BOLD, 16));
        r3.setBackground(Color.WHITE);
        r3.setBounds(100, 200, 200, 30);
        add(r3);

        r4 = new JRadioButton("Recurring Deposit Account");
        r4.setFont(new Font("Arial", Font.BOLD, 16));
        r4.setBackground(Color.WHITE);
        r4.setBounds(350, 200, 250, 30);
        add(r4);

        ButtonGroup groupAccountType = new ButtonGroup();
        groupAccountType.add(r1);
        groupAccountType.add(r2);
        groupAccountType.add(r3);
        groupAccountType.add(r4);

        l3 = new JLabel("Card Number:");
        l3.setFont(new Font("Arial", Font.BOLD, 20));
        l3.setBounds(100, 300, 200, 30);
        add(l3);

        l4 = new JLabel("XXXX-XXXX-XXXX-4184");
        l4.setFont(new Font("Arial", Font.BOLD, 20));
        l4.setBounds(350, 300, 300, 30);
        add(l4);

        l5 = new JLabel("PIN:");
        l5.setFont(new Font("Arial", Font.BOLD, 20));
        l5.setBounds(100, 350, 200, 30);
        add(l5);

        l6 = new JLabel("XXXX");
        l6.setFont(new Font("Arial", Font.BOLD, 20));
        l6.setBounds(350, 350, 200, 30);
        add(l6);

        l7 = new JLabel("Services Required:");
        l7.setFont(new Font("Arial", Font.BOLD, 20));
        l7.setBounds(100, 400, 200, 30);
        add(l7);

        c1 = new JCheckBox("ATM Card");
        c1.setFont(new Font("Arial", Font.BOLD, 16));
        c1.setBackground(Color.WHITE);
        c1.setBounds(100, 450, 200, 30);
        add(c1);

        c2 = new JCheckBox("Internet Banking");
        c2.setFont(new Font("Arial", Font.BOLD, 16));
        c2.setBackground(Color.WHITE);
        c2.setBounds(350, 450, 200, 30);
        add(c2);

        c3 = new JCheckBox("Mobile Banking");
        c3.setFont(new Font("Arial", Font.BOLD, 16));
        c3.setBackground(Color.WHITE);
        c3.setBounds(100, 500, 200, 30);
        add(c3);

        c4 = new JCheckBox("EMAIL Alerts");
        c4.setFont(new Font("Arial", Font.BOLD, 16));
        c4.setBackground(Color.WHITE);
        c4.setBounds(350, 500, 200, 30);
        add(c4);

        c5 = new JCheckBox("Cheque Book");
        c5.setFont(new Font("Arial", Font.BOLD, 16));
        c5.setBackground(Color.WHITE);
        c5.setBounds(100, 550, 200, 30);
        add(c5);

        c6 = new JCheckBox("E-Statement");
        c6.setFont(new Font("Arial", Font.BOLD, 16));
        c6.setBackground(Color.WHITE);
        c6.setBounds(350, 550, 200, 30);
        add(c6);

        c7 = new JCheckBox("I hereby declare that the above entered details are correct to the best of my knowledge.");
        c7.setFont(new Font("Arial", Font.BOLD, 12));
        c7.setBackground(Color.WHITE);
        c7.setBounds(100, 600, 600, 30);
        add(c7);

        b1 = new JButton("Submit");
        b1.setFont(new Font("Arial", Font.BOLD, 14));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(250, 650, 100, 30);
        add(b1);
        b1.addActionListener(this);

        b2 = new JButton("Cancel");
        b2.setFont(new Font("Arial", Font.BOLD, 14));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setBounds(420, 650, 100, 30);
        add(b2);
        b2.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
        setSize(850, 800);
        setLocation(350, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            String accountType = null;
            if (r1.isSelected()) {
                accountType = "Saving Account";
            } else if (r2.isSelected()) {
                accountType = "Fixed Deposit Account";
            } else if (r3.isSelected()) {
                accountType = "Current Account";
            } else if (r4.isSelected()) {
                accountType = "Recurring Deposit Account";
            }

            String cardNumber = generateCardNumber();
            String pin = generatePin();

            l4.setText(cardNumber);
            l6.setText(pin);

            String services = "";
            if (c1.isSelected()) {
                services += "ATM Card ";
            }
            if (c2.isSelected()) {
                services += "Internet Banking ";
            }
            if (c3.isSelected()) {
                services += "Mobile Banking ";
            }
            if (c4.isSelected()) {
                services += "EMAIL Alerts ";
            }
            if (c5.isSelected()) {
                services += "Cheque Book ";
            }
            if (c6.isSelected()) {
                services += "E-Statement ";
            }

            if (!c7.isSelected()) {
                JOptionPane.showMessageDialog(null, "Please confirm the declaration.");
            } else {
                try {
                	ConnectionFactory cf = new ConnectionFactory();
                    String query = "INSERT INTO SignupThree (formno, accountType, cardno, pin, facility) VALUES ('"
                            + formno + "', '" + accountType + "', '" + cardNumber + "', '" + pin + "', '" + services + "')";
                    String query2 = "INSERT INTO bank (formno, cardno, pin) VALUES ('" + formno + "','" + cardNumber + "','" + pin + "')";
                    cf.stmt.executeUpdate(query);
                    cf.stmt.executeUpdate(query2);
                    JOptionPane.showMessageDialog(null, "Card Number: " + cardNumber + "\n Pin: " + pin);

                    // Open the login window after successful signup
                    setVisible(false);
                    new Login().setVisible(true);

                    cf.stmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: Database connection or query execution issue.");
                }
            }
        } else if (ae.getSource() == b2) {
            setVisible(false);
        }
    }

    private String generateCardNumber() {
        Random rand = new Random();
        StringBuilder cardno = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            cardno.append(String.format("%04d", rand.nextInt(10000)));
            if (i < 3) {
                cardno.append("-");
            }
        }
        return cardno.toString();
    }

    private String generatePin() {
        Random rand = new Random();
        return String.format("%04d", rand.nextInt(10000));
    }

    public static void main(String[] args) {
        new SignupThree("");
    }
}
