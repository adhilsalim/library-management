import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


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
        // display the reader_details in console
        try {
            executeQuery("SELECT * FROM reader_details");
        } catch (ClassNotFoundException | SQLException exception) {
            // handle the exception
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching reader details: " + exception.getMessage());
        }

        
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
                try {
                    executeQuery("INSERT INTO reader_details VALUES (" + readerIdTextField.getText() + ", '" +
                            firstNameTextField.getText() + "', '" +
                            lastNameTextField.getText() + "', '" +
                            addressTextField.getText() + "', '" +
                            phoneTextField.getText() + "', '" +
                            emailTextField.getText() + "')");
                } catch (ClassNotFoundException | SQLException exception) {
                    // handle the exception
                    exception.printStackTrace();
                    // JOptionPane.showMessageDialog(null, "Error executing the query: " + exception.getMessage());
                }
            }
        });

        JButton deleteButton = new JButton("Delete Reader");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    executeQuery("DELETE FROM reader_details WHERE reader_id = '" + readerIdTextField.getText() + "'");
                } catch (ClassNotFoundException | SQLException exception) {
                    // handle the exception
                    exception.printStackTrace();
                    // JOptionPane.showMessageDialog(null, "Error deleting reader details: " + exception.getMessage());
                }
            }
        });

        JButton updateButton = new JButton("Update Reader");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder query = new StringBuilder("UPDATE reader_details SET ");
                query.append("first_name = '").append(firstNameTextField.getText()).append("', ");
                query.append("last_name = '").append(lastNameTextField.getText()).append("', ");
                query.append("address = '").append(addressTextField.getText()).append("', ");
                query.append("phone_number = '").append(phoneTextField.getText()).append("', ");
                query.append("email = '").append(emailTextField.getText()).append("' ");
                query.append("WHERE reader_id = '").append(readerIdTextField.getText()).append("'");

                try {
                    executeQuery(query.toString());
                } catch (ClassNotFoundException | SQLException exception) {
                    // handle the exception
                    exception.printStackTrace();
                    // JOptionPane.showMessageDialog(null, "Error updating reader details: " + exception.getMessage());
                }
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
    public void executeQuery(String query) throws ClassNotFoundException, SQLException {
        // test code
        System.out.println("TEST: "+query);

        try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521/XE";
        String user = "adhilsalim";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                System.out.print(resultSet.getString(i) + "\t");
            }
                System.out.println();
            }
            JOptionPane.showMessageDialog(null, "Query executed successfully.");
        }
        } catch (ClassNotFoundException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error executing the query: " + e.getMessage());
        }
  }
}
