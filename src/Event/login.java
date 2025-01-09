package Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(login::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("login");

        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(new JPanel());
        frame.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Check username and password (dummy check for demonstration)
                if (username.equals("admin") && password.equals("admin")) {
                    frame.dispose(); // Close the login window
                    openManagementPage();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password", "login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }

    private static void openManagementPage() {
        EventManagementSystem ems = new EventManagementSystem();
        // Code to open management window
        // Replace this with code to open management.java window
        management.main(new String[0]); // Assuming management.java has a main method
    }
}
