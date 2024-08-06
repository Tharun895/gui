package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SignupTwo extends JFrame implements ActionListener {
    JLabel l2, l3, l4, l5, l6, l9, l10, l11, l12, l13, l14, lPhone;
    JTextField tfPan, tfAadhar, tfPhone;
    JRadioButton rbnMale, rbnFemale, rbnAccountYes, rbnAccountNo, rbnCitizenYes, rbnCitizenNo;
    JComboBox<String> jcbReligion, jcbCategory, jcbIncome, jcbEducation, jcbOccupation;
    JButton btnNext, btnCancel;
    String formno = "";

    public SignupTwo(String formno) {
        this.formno = formno;
        setTitle("New Accounts Application Form Page-2");
        setLayout(null);

        l2 = new JLabel("Additional Details of Customer");
        l2.setFont(new Font("Arial", Font.BOLD, 25));
        l2.setBounds(200, 80, 600, 30);
        add(l2);

        l3 = new JLabel("Religion:");
        l3.setFont(new Font("Arial", Font.BOLD, 22));
        l3.setBounds(100, 130, 200, 30);
        add(l3);

        String[] religion = {"Hindu", "Muslim", "Sikh", "Christian", "Others"};
        jcbReligion = new JComboBox<String>(religion);
        jcbReligion.setBackground(Color.WHITE);
        jcbReligion.setFont(new Font("Arial", Font.BOLD, 22));
        jcbReligion.setBounds(270, 130, 350, 30);
        add(jcbReligion);

        l4 = new JLabel("Category:");
        l4.setFont(new Font("Arial", Font.BOLD, 22));
        l4.setBounds(100, 180, 200, 30);
        add(l4);

        String[] category = {"General", "OBC", "SC", "ST", "Other"};
        jcbCategory = new JComboBox<String>(category);
        jcbCategory.setBackground(Color.WHITE);
        jcbCategory.setFont(new Font("Arial", Font.BOLD, 22));
        jcbCategory.setBounds(270, 180, 350, 30);
        add(jcbCategory);

        l5 = new JLabel("Income:");
        l5.setFont(new Font("Arial", Font.BOLD, 22));
        l5.setBounds(100, 230, 200, 30);
        add(l5);

        String[] income = {"Null", "<1,50,000", "<2,50,000", "<5,00,000", "Above 10,00,000"};
        jcbIncome = new JComboBox<String>(income);
        jcbIncome.setBackground(Color.WHITE);
        jcbIncome.setFont(new Font("Arial", Font.BOLD, 22));
        jcbIncome.setBounds(270, 230, 350, 30);
        add(jcbIncome);

        l6 = new JLabel("Educational:");
        l6.setFont(new Font("Arial", Font.BOLD, 22));
        l6.setBounds(100, 280, 200, 30);
        add(l6);

        String[] education = {"Non-Graduate", "Graduate", "Post-Graduate", "Doctorate", "Others"};
        jcbEducation = new JComboBox<String>(education);
        jcbEducation.setBackground(Color.WHITE);
        jcbEducation.setFont(new Font("Arial", Font.BOLD, 22));
        jcbEducation.setBounds(270, 280, 350, 30);
        add(jcbEducation);

        l10 = new JLabel("Occupation:");
        l10.setFont(new Font("Arial", Font.BOLD, 22));
        l10.setBounds(100, 330, 200, 30);
        add(l10);

        String[] occupation = {"Salaried", "Self-Employed", "Business", "Student", "Retired", "Others"};
        jcbOccupation = new JComboBox<String>(occupation);
        jcbOccupation.setBackground(Color.WHITE);
        jcbOccupation.setFont(new Font("Arial", Font.BOLD, 22));
        jcbOccupation.setBounds(270, 330, 350, 30);
        add(jcbOccupation);

        l11 = new JLabel("PAN Number:");
        l11.setFont(new Font("Arial", Font.BOLD, 22));
        l11.setBounds(100, 380, 200, 30);
        add(l11);

        tfPan = new JTextField();
        tfPan.setBounds(270, 380, 350, 30);
        tfPan.setFont(new Font("Arial", Font.BOLD, 22));
        add(tfPan);

        l12 = new JLabel("Aadhar Number:");
        l12.setFont(new Font("Arial", Font.BOLD, 22));
        l12.setBounds(100, 430, 200, 30);
        add(l12);

        tfAadhar = new JTextField();
        tfAadhar.setBounds(270, 430, 350, 30);
        tfAadhar.setFont(new Font("Arial", Font.BOLD, 22));
        add(tfAadhar);

        lPhone = new JLabel("Phone Number:");
        lPhone.setFont(new Font("Arial", Font.BOLD, 22));
        lPhone.setBounds(100, 480, 200, 30);
        add(lPhone);

        tfPhone = new JTextField();
        tfPhone.setBounds(270, 480, 350, 30);
        tfPhone.setFont(new Font("Arial", Font.BOLD, 22));
        add(tfPhone);

        l13 = new JLabel("Senior Citizen:");
        l13.setFont(new Font("Arial", Font.BOLD, 22));
        l13.setBounds(100, 530, 200, 30);
        add(l13);

        rbnCitizenYes = new JRadioButton("Yes");
        rbnCitizenYes.setFont(new Font("Arial", Font.BOLD, 22));
        rbnCitizenYes.setBackground(Color.WHITE);
        rbnCitizenYes.setBounds(270, 530, 100, 30);
        add(rbnCitizenYes);

        rbnCitizenNo = new JRadioButton("No");
        rbnCitizenNo.setFont(new Font("Arial", Font.BOLD, 22));
        rbnCitizenNo.setBackground(Color.WHITE);
        rbnCitizenNo.setBounds(370, 530, 100, 30);
        add(rbnCitizenNo);

        ButtonGroup bgCitizen = new ButtonGroup();
        bgCitizen.add(rbnCitizenYes);
        bgCitizen.add(rbnCitizenNo);

        l14 = new JLabel("Existing Account:");
        l14.setFont(new Font("Arial", Font.BOLD, 22));
        l14.setBounds(100, 580, 200, 30);
        add(l14);

        rbnAccountYes = new JRadioButton("Yes");
        rbnAccountYes.setFont(new Font("Arial", Font.BOLD, 22));
        rbnAccountYes.setBackground(Color.WHITE);
        rbnAccountYes.setBounds(300, 580, 100, 30);
        add(rbnAccountYes);

        rbnAccountNo = new JRadioButton("No");
        rbnAccountNo.setFont(new Font("Arial", Font.BOLD, 22));
        rbnAccountNo.setBackground(Color.WHITE);
        rbnAccountNo.setBounds(400, 580, 100, 30);
        add(rbnAccountNo);

        ButtonGroup bgAccount = new ButtonGroup();
        bgAccount.add(rbnAccountYes);
        bgAccount.add(rbnAccountNo);

        btnNext = new JButton("Next");
        btnNext.setFont(new Font("Arial", Font.BOLD, 14));
        btnNext.setBackground(Color.BLACK);
        btnNext.setForeground(Color.WHITE);
        btnNext.setBounds(250, 650, 100, 30);
        add(btnNext);
        btnNext.addActionListener(this);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancel.setBackground(Color.BLACK);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setBounds(420, 650, 100, 30);
        add(btnCancel);
        btnCancel.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
        setSize(800, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SignupTwo obj = new SignupTwo("");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNext) {
            String religion = (String) jcbReligion.getSelectedItem();
            String category = (String) jcbCategory.getSelectedItem();
            String income = (String) jcbIncome.getSelectedItem();
            String education = (String) jcbEducation.getSelectedItem();
            String occupation = (String) jcbOccupation.getSelectedItem();
            String pan = tfPan.getText();
            String aadhar = tfAadhar.getText();
            String phone = tfPhone.getText();
            String seniorCitizen = rbnCitizenYes.isSelected() ? "Yes" : "No";
            String existingAccount = rbnAccountYes.isSelected() ? "Yes" : "No";

            try {
                if (pan.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Enter your PAN Number");
                } else if (aadhar.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Enter your Aadhar Number");
                } else if (phone.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Enter your Phone Number");
                } else {
                    ConnectionFactory s = new ConnectionFactory();
                    String query = "insert into signuptwo values('" + formno + "','" + religion + "','" + category + "','" + income + "','" + education + "','" + occupation + "','" + pan + "','" + aadhar + "','" + phone + "','" + seniorCitizen + "','" + existingAccount + "')";
                    s.stmt.executeUpdate(query);
                    new SignupThree(formno).setVisible(true);
                    setVisible(false);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == btnCancel) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }
}
