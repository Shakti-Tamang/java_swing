package com.nextstep.ui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.nextstep.model.UserModel;
import com.nextstep.service.SaveUser;
import com.nextstep.service.SaveUserImpl;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Deatils extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JTable table;
	private JTextField textField;
	
	SaveUser save=new SaveUserImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deatils frame = new Deatils();
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
	public Deatils() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(46, 69, 831, 341);
		contentPane.add(table);
		
		String columnname[]=  {"id","name","address","gender","course","age"};
		DefaultTableModel defaultTableModel=new DefaultTableModel(columnname,0);
		table.setModel(defaultTableModel);
		
		display();
	    JScrollPane pane=new JScrollPane(table);
	    pane.setBounds(46, 69, 823, 335);
	    contentPane.add(pane);
	    
	    JLabel lblNewLabel = new JLabel("User Deatils");
	    lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 42));
	    lblNewLabel.setForeground(new Color(0, 255, 64));
	    lblNewLabel.setBounds(157, 11, 440, 44);
	    contentPane.add(lblNewLabel);
	    
	    textField = new JTextField();
	    textField.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {
	    		
	    		String name=textField.getText();
	    		
	    		if(name==null || name.isEmpty()) {
	    			
	    			display();
	    		}
	    		
	    		displayByName(name);
	    	}
	    });
	    textField.setBounds(671, 21, 193, 37);
	    contentPane.add(textField);
	    textField.setColumns(10);
	    
	    JButton btnNewButton = new JButton("Delete");
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	DefaultTableModel defaultTableModel=(DefaultTableModel) table.getModel();
	    	
	    	int row=table.getSelectedRow();
	    	
	    	Object id=defaultTableModel.getValueAt(row, 0);
	    	
	    	int delete=JOptionPane.showConfirmDialog(Deatils.this, "Do yo want to delete","delete",JOptionPane.YES_NO_OPTION);
	    	
	    	if(delete==0) {
	    	save.deleteById(Integer.parseInt(id.toString()));	
	    		display();
	    	}
	    	
	    	
	    		
	    	}
	    });
	    btnNewButton.setBounds(46, 467, 168, 44);
	    contentPane.add(btnNewButton);
	    
	    JButton btnUpdate = new JButton("Update");
	    btnUpdate.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		DefaultTableModel defaultTableModel=(DefaultTableModel) table.getModel();
	    		
	    		int row=table.getSelectedRow();
	    		
	    		Object id=defaultTableModel.getValueAt(row, 0);
	    		Object name=defaultTableModel.getValueAt(row, 1);
	    		Object address=defaultTableModel.getValueAt(row, 2);
	    		Object gender=defaultTableModel.getValueAt(row, 3);
	    		Object course=defaultTableModel.getValueAt(row, 4);
	    		Object age=defaultTableModel.getValueAt(row, 5);
	    		
	    		UserJFrame obj=new UserJFrame();
	    		obj.id.setText(id.toString());
	    		obj.name.setText(name.toString());
	    		obj.address.setText(address.toString());
	    		
	    		String genders=gender.toString();
	    		
	    		System.out.println(gender);
	    		
	    
	    		if (genders.equalsIgnoreCase("male")) {
	    		    obj.rdbtnNewRadioButton.setSelected(true);
	    		} else if (genders.equalsIgnoreCase("female")) {
	    		    obj.rdbtnFemale.setSelected(true);
	    		} else if (genders.equalsIgnoreCase("others")) {
	    		    obj.rdbtnOthers.setSelected(true);
	    		}

	    		obj.comboBox.setSelectedItem(course.toString());
	    		
	    		obj.age.setText(age.toString());
	    		dispose();
	    		obj.setVisible(true);
	    		
	    	}
	    });
	    btnUpdate.setBounds(270, 467, 168, 44);
	    contentPane.add(btnUpdate);
		
		
	}
	
	public void display() {
		
		SaveUser saveUser=new SaveUserImpl();
		
		List<UserModel>list=saveUser.getAll();
		
		DefaultTableModel defaultTableModel=(DefaultTableModel) table.getModel();
		
		defaultTableModel.setRowCount(0);
		
		for(UserModel model:list) {
			
//			row has multiple coluns
			defaultTableModel.addRow(new Object[] {
					model.getId(),model.getName(),model.getAdress(),model.getGender(),
					model.getCourse(),model.getAge()
			});
		}
		
		
	}
	
	
	public void displayByName(String name) {
		
		SaveUser saveUser=new SaveUserImpl();
		
		List<UserModel>list=saveUser.serchByName(name);
		
		DefaultTableModel defaultTableModel=(DefaultTableModel) table.getModel();
		
		defaultTableModel.setRowCount(0);
		
		for(UserModel model:list) {
			
//			row has multiple coluns
			defaultTableModel.addRow(new Object[] {
					model.getId(),model.getName(),model.getAdress(),model.getGender(),
					model.getCourse(),model.getAge()
			});    
		}
	
}
}

