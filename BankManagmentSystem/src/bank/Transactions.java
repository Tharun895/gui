package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transactions extends JFrame implements ActionListener {
   
	String pin;
    JLabel l1;
    JButton b1, b2, b3, b4, b5, b6, btnExit,b7;

    public Transactions(String pin) {
        setTitle("Transaction Page");
        this.pin = pin;
        setSize(960, 1000);
        setLocation(300, 0);
        setVisible(true);
        setLayout(null); 

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/realistic-a-atm-machine-vector-29665834.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 1100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel lblImage = new JLabel(i3);
        lblImage.setBounds(0, 0, 960, 1000);
        add(lblImage);

        l1 = new JLabel("Please select your Transaction");
        l1.setForeground(Color.white);
        l1.setFont(new Font("Tahoma", Font.BOLD, 18));
        l1.setBounds(260, 340, 700, 35);
        lblImage.add(l1);

        b1 = new JButton("Deposit");
        b2 = new JButton("Withdraw");
        b3 = new JButton("Fast Cash");
        b4 = new JButton("Mini Statement");
        b5 = new JButton("Pin Change");
        b6 = new JButton("Balance Check");
        b7=new JButton("amount Transfer");
        btnExit = new JButton("Exit");

        b1.setBounds(240, 415, 150, 35);
        b2.setBounds(420, 415, 150, 35);
        b3.setBounds(240, 460, 150, 35);
        b4.setBounds(420, 460, 150, 35);
        b5.setBounds(240, 505, 150, 35);
        b6.setBounds(420, 505, 150, 35);
        b7.setBounds(240, 550, 150, 35);
        btnExit.setBounds(420, 550, 150, 35);

        lblImage.add(b1);
        lblImage.add(b2);
        lblImage.add(b3);
        lblImage.add(b4);
        lblImage.add(b5);
        lblImage.add(b6);
        lblImage.add(b7);
        lblImage.add(btnExit);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        btnExit.addActionListener(this);
    }

    public static void main(String[] args) {
        new Transactions("");
    }

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==btnExit) {
			System.exit(0);
		}
		else if(ae.getSource()==b1) {
			new Deposit(pin).setVisible(true);
		}
		else if(ae.getSource()==b2) {
			new Withdrawl(pin).setVisible(true);
		}
		else if(ae.getSource()==b5) {
			new PinChange(pin).setVisible(true);
		}
		else if(ae.getSource()==b4) {
			new MiniStatement(pin).setVisible(true);
		}
		else if(ae.getSource()==b6) {
			new BalanceCheck(pin).setVisible(true);
		}
		else if(ae.getSource()==b7) {
			new AmountTransfer(pin).setVisible(true);
		}
		else if(ae.getSource()==b3)
		{
			setVisible(false);
			new FastCash(pin).setVisible(true);
		}
		// TODO Auto-generated method stub
		
	}
}
