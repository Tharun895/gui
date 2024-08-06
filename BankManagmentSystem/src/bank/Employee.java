package bank;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Employee extends JFrame implements ActionListener {
    JLabel l1, l2;
    JTextField tfFormno, tfName, tfEmail, tfPhone;
    JButton bSearch, bUpdate, bDelete, bClear;
    JTextArea taUserInfo;

    public Employee() {
        setTitle("Employee Portal - Manage Users");

        l1 = new JLabel("Enter Form No:");
        l1.setFont(new Font("Arial", Font.BOLD, 18));
        l1.setBounds(50, 30, 150, 30);
        add(l1);

        tfFormno = new JTextField();
        tfFormno.setFont(new Font("Arial", Font.PLAIN, 18));
        tfFormno.setBounds(200, 30, 200, 30);
        add(tfFormno);

        bSearch = new JButton("Search");
        bSearch.setFont(new Font("Arial", Font.BOLD, 18));
        bSearch.setBounds(420, 30, 100, 30);
        bSearch.addActionListener(this);
        add(bSearch);

        taUserInfo = new JTextArea();
        taUserInfo.setFont(new Font("Arial", Font.PLAIN, 16));
        taUserInfo.setBounds(50, 80, 500, 200);
        taUserInfo.setEditable(false);
        add(taUserInfo);

        l2 = new JLabel("Update Information:");
        l2.setFont(new Font("Arial", Font.BOLD, 18));
        l2.setBounds(50, 300, 200, 30);
        add(l2);

        tfName = new JTextField();
        tfName.setFont(new Font("Arial", Font.PLAIN, 18));
        tfName.setBounds(50, 340, 200, 30);
        tfName.setToolTipText("Name");
        add(tfName);

        tfEmail = new JTextField();
        tfEmail.setFont(new Font("Arial", Font.PLAIN, 18));
        tfEmail.setBounds(270, 340, 200, 30);
        tfEmail.setToolTipText("Email");
        add(tfEmail);

        tfPhone = new JTextField();
        tfPhone.setFont(new Font("Arial", Font.PLAIN, 18));
        tfPhone.setBounds(490, 340, 200, 30);
        tfPhone.setToolTipText("Phone");
        add(tfPhone);

        bUpdate = new JButton("Update");
        bUpdate.setFont(new Font("Arial", Font.BOLD, 18));
        bUpdate.setBounds(50, 390, 100, 30);
        bUpdate.addActionListener(this);
        add(bUpdate);

        bDelete = new JButton("Delete");
        bDelete.setFont(new Font("Arial", Font.BOLD, 18));
        bDelete.setBounds(170, 390, 100, 30);
        bDelete.addActionListener(this);
        add(bDelete);

        bClear = new JButton("Clear");
        bClear.setFont(new Font("Arial", Font.BOLD, 18));
        bClear.setBounds(290, 390, 100, 30);
        bClear.addActionListener(this);
        add(bClear);

        setLayout(null);
        setSize(800, 500);
        setLocation(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Employee();
    }

    public void actionPerformed(ActionEvent ae) {
        String formno = tfFormno.getText();
        if (ae.getSource() == bSearch) {
            if (formno.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Form No cannot be empty");
                return;
            }
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagment?useSSL=false&requireSSL=false", "root", "Tanjirokamado#8");
                String query = "SELECT s.name, s.email, s.phone, st.field1, st.field2, sb.field3 "
                        + "FROM signup s "
                		+"FROM signuptwo s"
                        + "JOIN signupTwo st ON s.formno = st.formno "
                        + "JOIN signupThree sb ON s.formno = sb.formno "
                        + "WHERE s.formno = ?";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, formno);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    String userInfo = "Name: " + rs.getString("name") + "\n"
                            + "Email: " + rs.getString("email") + "\n"
                            + "Phone: " + rs.getString("phone") + "\n"
                            + "Field1: " + rs.getString("field1") + "\n"
                            + "Field2: " + rs.getString("field2") + "\n"
                            + "Field3: " + rs.getString("field3");
                    taUserInfo.setText(userInfo);
                    tfName.setText(rs.getString("name"));
                    tfEmail.setText(rs.getString("email"));
                    tfPhone.setText(rs.getString("phone"));
                } else {
                    JOptionPane.showMessageDialog(null, "User not found");
                }
                rs.close();
                pstmt.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == bUpdate) {
            String name = tfName.getText();
            String email = tfEmail.getText();
            String phone = tfPhone.getText();
            if (formno.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fields cannot be empty");
                return;
            }
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagment?useSSL=false&requireSSL=false", "root", "Tanjirokamado#8");
                String query1 = "UPDATE signup SET name = ?, email = ? WHERE formno = ?";
                String query2 = "UPDATE signupTwo SET phone = ? WHERE formno = ?";
                PreparedStatement pstmt1 = con.prepareStatement(query1);
                PreparedStatement pstmt2 = con.prepareStatement(query2);
                pstmt1.setString(1, name);
                pstmt1.setString(2, email);
                pstmt1.setString(3, formno);
                pstmt2.setString(1, phone);
                pstmt2.setString(2, formno);
                pstmt1.executeUpdate();
                pstmt2.executeUpdate();
                JOptionPane.showMessageDialog(null, "User details updated successfully");
                pstmt1.close();
                pstmt2.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == bDelete) {
            if (formno.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Form No cannot be empty");
                return;
            }
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagment?useSSL=false&requireSSL=false", "root", "Tanjirokamado#8");
                String query1 = "DELETE FROM signup WHERE formno = ?";
                String query2 = "DELETE FROM signupTwo WHERE formno = ?";
                String query3 = "DELETE FROM signupThree WHERE formno = ?";
                PreparedStatement pstmt1 = con.prepareStatement(query1);
                PreparedStatement pstmt2 = con.prepareStatement(query2);
                PreparedStatement pstmt3 = con.prepareStatement(query3);
                pstmt1.setString(1, formno);
                pstmt2.setString(1, formno);
                pstmt3.setString(1, formno);
                pstmt1.executeUpdate();
                pstmt2.executeUpdate();
                pstmt3.executeUpdate();
                JOptionPane.showMessageDialog(null, "User deleted successfully");
                pstmt1.close();
                pstmt2.close();
                pstmt3.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == bClear) {
            tfFormno.setText("");
            tfName.setText("");
            tfEmail.setText("");
            tfPhone.setText("");
            taUserInfo.setText("");
        }
    }
}
