import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LibraryManagement extends JFrame {

    public LibraryManagement() {
        setTitle("Library Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));

        // Manage Reader Button
        JButton manageReaderButton = new JButton("Manage Reader");
        manageReaderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showReaderManagementWindow();
            }
        });

        // Manage Book Button
        JButton manageBookButton = new JButton("Manage Book");
        manageBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBookManagementWindow();
            }
        });

        // Manage Issue Button
        JButton manageIssueButton = new JButton("Manage Issue");
        manageIssueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showIssueManagementWindow();
            }
        });

        // Add buttons to main panel
        mainPanel.add(manageReaderButton);
        mainPanel.add(manageBookButton);
        mainPanel.add(manageIssueButton);

        // Add main panel to the frame
        add(mainPanel);

        // Set frame visibility
        setVisible(true);
    }

    private void showReaderManagementWindow() {
        // Create reader management window
        JFrame readerFrame = new JFrame("Manage Readers");
        readerFrame.setSize(400, 300);

        // Create main panel for reader window
        JPanel readerPanel = new JPanel();
        readerPanel.setLayout(new GridLayout(4, 2));

        // Reader management components
        JLabel readerIdLabel = new JLabel("Reader ID:");
        JTextField readerIdTextField = new JTextField();

        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameTextField = new JTextField();

        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameTextField = new JTextField();

        JLabel addressLabel = new JLabel("Address:");
        JTextField addressTextField = new JTextField();

        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneTextField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailTextField = new JTextField();

        // Reader management buttons
        JButton addButton = new JButton("Add Reader");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeQuery("INSERT INTO reader_details VALUES (" + readerIdTextField.getText() + ", '" +
                        firstNameTextField.getText() + "', '" +
                        lastNameTextField.getText() + "', '" +
                        addressTextField.getText() + "', '" +
                        phoneTextField.getText() + "', '" +
                        emailTextField.getText() + "')" );
            }
        });

        JButton deleteButton = new JButton("Delete Reader");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeQuery("DELETE FROM reader_details WHERE reader_id = '" + readerIdTextField.getText() + "'");
            }
        });

        JButton updateButton = new JButton("Update Reader");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeQuery("UPDATE reader_details SET first_name = '" + firstNameTextField.getText() + "', " +
                        "last_name = '" + lastNameTextField.getText() + "', " +
                        "address = '" + addressTextField.getText() + "', " +
                        "phone_number = '" + phoneTextField.getText() + "', " +
                        "email = '" + emailTextField.getText() + "' " +
                        "WHERE reader_id = '" + readerIdTextField.getText() + "'");
            }
        });

        // Add components to the reader panel
        readerPanel.add(readerIdLabel);
        readerPanel.add(readerIdTextField);
        readerPanel.add(firstNameLabel);
        readerPanel.add(firstNameTextField);
        readerPanel.add(lastNameLabel);
        readerPanel.add(lastNameTextField);
        readerPanel.add(addressLabel);
        readerPanel.add(addressTextField);
        readerPanel.add(phoneLabel);
        readerPanel.add(phoneTextField);
        readerPanel.add(emailLabel);
        readerPanel.add(emailTextField);
        readerPanel.add(addButton);
        readerPanel.add(deleteButton);
        readerPanel.add(updateButton);

        // Add the reader panel to the reader frame
        readerFrame.add(readerPanel);

        // Set reader frame visibility
        readerFrame.setVisible(true);
    }

    private void showBookManagementWindow() {
        // Implement the code to display the book management window here
    }

    private void showIssueManagementWindow() {
        // Implement the code to display the issue management window here
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LibraryManagement();
            }
        });
    }

    // Method to execute SQL queries
    private void executeQuery(String query) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521/XE";
            String user = "adhilsalim";
            String password = "password";

            try (Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Query executed successfully.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error executing the query: " + e.getMessage());
        }
    }
}
