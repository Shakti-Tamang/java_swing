package com.nextstep.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

public class Prac1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prac1 frame = new Prac1();
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
	public Prac1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(59, 25, 336, 181);
		contentPane.add(table);
		
	
		String columnNumber[]= {"id","name","address","gender","course","age"};
		DefaultTableModel defaultTableModel=new DefaultTableModel(columnNumber,0);
		
		table.setModel(defaultTableModel);
		
	    JScrollPane pane=new JScrollPane(table);
	    pane.setBounds(46, 69, 823, 335);
	    contentPane.add(pane);
		    
		
	}
}
