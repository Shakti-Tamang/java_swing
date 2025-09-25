package com.nextstep.ui;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.nextstep.model.UserModel;
import com.nextstep.service.SaveUser;
import com.nextstep.service.SaveUserImpl;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
    private JLabel lblImagePreview;   
    private File selectedImageFile;   // store selected image file

    private static final String IMAGE_FOLDER = "src/images/com/np"; // folder to save images

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UserJFrame frame = new UserJFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public UserJFrame() {
        // Frame icon
        ImageIcon icon = new ImageIcon(
                getClass().getResource("/images/com/np/wallpaperflare.com_wallpaper (1).jpg"));
        Image img = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        setIconImage(img);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);  
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

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rdbtnNewRadioButton);
        genderGroup.add(rdbtnFemale);
        genderGroup.add(rdbtnOthers);

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"JAVA", ".Net"}));
        comboBox.setBounds(106, 176, 160, 22);
        contentPane.add(comboBox);

        JLabel lblNewLabel_2 = new JLabel("Course");
        lblNewLabel_2.setBounds(50, 180, 46, 14);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_2_1 = new JLabel("Age");
        lblNewLabel_2_1.setBounds(50, 219, 46, 14);
        contentPane.add(lblNewLabel_2_1);

        age = new JTextField();
        age.setColumns(10);
        age.setBounds(93, 216, 103, 20);
        contentPane.add(age);

        id = new JLabel("UserId");
        id.setBounds(330, 11, 46, 14);
        contentPane.add(id);

        // Image preview
        lblImagePreview = new JLabel();
        lblImagePreview.setBounds(330, 225, 77, 54);
        lblImagePreview.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
        contentPane.add(lblImagePreview);

        // File chooser icon button using system folder icon
        Icon folderIcon = UIManager.getIcon("FileView.directoryIcon");
        JButton btnChooseImage = new JButton(folderIcon);
        btnChooseImage.setBounds(430, 243, 40, 40);
        contentPane.add(btnChooseImage);

        btnChooseImage.addActionListener(e -> chooseImage());

        // Submit button
        JButton btnNewButton = new JButton("Submit");
        btnNewButton.addActionListener(e -> submitData());
        btnNewButton.setBounds(139, 300, 89, 23);
        contentPane.add(btnNewButton);
    }

    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an image");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(
                new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "png", "jpeg", "gif")
        );

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedImageFile = fileChooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(selectedImageFile.getAbsolutePath());
            Image scaled = icon.getImage().getScaledInstance(
                    lblImagePreview.getWidth(),
                    lblImagePreview.getHeight(),
                    Image.SCALE_SMOOTH
            );
            lblImagePreview.setIcon(new ImageIcon(scaled));
        }
    }

    private void submitData() {
        try {
            String uname = name.getText();
            String uaddress = address.getText();
            int uage = Integer.parseInt(age.getText());
            String course = (String) comboBox.getSelectedItem();

            String gender = "";
            if (rdbtnNewRadioButton.isSelected()) gender = rdbtnNewRadioButton.getText();
            else if (rdbtnFemale.isSelected()) gender = rdbtnFemale.getText();
            else if (rdbtnOthers.isSelected()) gender = rdbtnOthers.getText();

            if (uname.isEmpty() || uaddress.isEmpty() || gender.isEmpty() || selectedImageFile == null) {
                JOptionPane.showMessageDialog(this, "Please fill all fields and select an image");
                return;
            }

            // Ensure image folder exists
            File folder = new File(IMAGE_FOLDER);
            if (!folder.exists()) folder.mkdirs();

            // Copy selected image to folder
            String imageFileName = System.currentTimeMillis() + "_" + selectedImageFile.getName();
            File destination = new File(folder, imageFileName);
            Files.copy(selectedImageFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Save user data
            UserModel model = new UserModel();
            model.setName(uname);
            model.setAdress(uaddress);
            model.setGender(gender);
            model.setCourse(course);
            model.setAge(uage);
            model.setImagePath(destination.getAbsolutePath()); // save path to DB

            SaveUser saveUser = new SaveUserImpl();
            saveUser.saveUser(model);

            JOptionPane.showMessageDialog(this, "User saved successfully!");
            dispose();
            Deatils deatils = new Deatils();
            deatils.setVisible(true);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid age");
        } catch (IOException ioEx) {
            JOptionPane.showMessageDialog(this, "Failed to save image");
            ioEx.printStackTrace();
        }
    }
}
