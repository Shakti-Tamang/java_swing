package com.nextstep.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.nextstep.model.UserModel;
import com.nextstep.service.SaveUser;
import com.nextstep.service.SaveUserImpl;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class UserJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTextField name;
	public JTextField address;
	public JTextField age;
	public JComboBox comboBox;
	public JRadioButton rdbtnNewRadioButton;
	public JRadioButton rdbtnFemale;
	public JRadioButton rdbtnOthers;
	public JLabel id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserJFrame frame = new UserJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserJFrame() {
		
		ImageIcon icon = new ImageIcon(
			    getClass().getResource("/images/com/np/wallpaperflare.com_wallpaper (1).jpg"));
			Image img = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
			setIconImage(img);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		


		
		JLabel lblNewLabel = new JLabel("SignUp");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(139, 22, 110, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(50, 81, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Address");
		lblNewLabel_1_1.setBounds(50, 106, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		name = new JTextField();
		name.setBounds(117, 78, 103, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(117, 103, 103, 20);
		contentPane.add(address);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Gender");
		lblNewLabel_1_1_1.setBounds(50, 145, 46, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		rdbtnNewRadioButton = new JRadioButton("Male");
		rdbtnNewRadioButton.setBounds(139, 141, 54, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		 rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(215, 141, 67, 23);
		contentPane.add(rdbtnFemale);
		
		 rdbtnOthers = new JRadioButton("Others");
		rdbtnOthers.setBounds(289, 141, 77, 23);
		contentPane.add(rdbtnOthers);
		
		 comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"JAVA", ".Net"}));
		comboBox.setBounds(106, 176, 160, 22);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Course");
		lblNewLabel_2.setBounds(50, 180, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Sumit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String uname=name.getText();
				
				String uaddress=address.getText();
				
				int uage=Integer.parseInt(age.getText());
				
				String course=(String) comboBox.getSelectedItem();
				
				String gender="";
				if(rdbtnNewRadioButton.isSelected()) {
					
					gender=rdbtnNewRadioButton .getText();
						}
				
				else if(rdbtnFemale.isSelected()) {
					gender=rdbtnFemale.getText();
					
				}
				
				else if(rdbtnOthers .isSelected()) {
					
					gender=rdbtnOthers .getText();
					
				}
				
				UserModel model=new  UserModel();
				
				model.setName(uname);
				model.setAdress(uaddress);
				
				model.setGender(gender);
				
				model.setCourse(course);
				model.setAge(uage);
				
				SaveUser saveUser=new SaveUserImpl();
				
				saveUser.saveUser(model);
				
				
				dispose();
				
				Deatils deatils=new Deatils();
				
				deatils.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(139, 238, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2_1 = new JLabel("Age");
		lblNewLabel_2_1.setBounds(50, 219, 46, 14);
		contentPane.add(lblNewLabel_2_1);
		
		age = new JTextField();
		age.setColumns(10);
		age.setBounds(93, 216, 103, 20);
		contentPane.add(age);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(264, 118, 2, 2);
		contentPane.add(scrollPane);
		
	     id = new JLabel("UserId");
		id.setBounds(330, 11, 46, 14);
		contentPane.add(id);
	}
}
