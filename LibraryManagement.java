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
        try {
            executeQuery("SELECT * FROM reader_details");
        } catch (ClassNotFoundException | SQLException exception) {
            // handle the exception
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching reader details: " + exception.getMessage());
        }

        JFrame readerFrame = new JFrame("Manage Readers");
        readerFrame.setSize(400, 300);

        // Create main panel for reader window
        JPanel readerPanel = new JPanel();
        readerPanel.setLayout(new GridLayout(4, 2));
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
                    exception.printStackTrace();
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
                    exception.printStackTrace();
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
                    exception.printStackTrace();
                }
            }

        });
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
        readerFrame.add(readerPanel);
        readerFrame.setVisible(true);
    }

    private void showBookManagementWindow() {
    try {
        executeQuery("SELECT * FROM book_details");
    } catch (ClassNotFoundException | SQLException exception) {
        exception.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error fetching book details: " + exception.getMessage());
    }

    JFrame bookFrame = new JFrame("Manage Books");
    bookFrame.setSize(400, 300);

    JPanel bookPanel = new JPanel();
    bookPanel.setLayout(new GridLayout(4, 2));

    JLabel bookIdLabel = new JLabel("Book ID:");
    JTextField bookIdTextField = new JTextField();
    JLabel titleLabel = new JLabel("Title:");
    JTextField titleTextField = new JTextField();
    JLabel authorLabel = new JLabel("Author:");
    JTextField authorTextField = new JTextField();
    JLabel genreLabel = new JLabel("Genre:");
    JTextField genreTextField = new JTextField();
    JLabel yearLabel = new JLabel("Publication Year:");
    JTextField yearTextField = new JTextField();
    JLabel quantityLabel = new JLabel("Available Quantity:");
    JTextField quantityTextField = new JTextField();

    JButton addButton = new JButton("Add Book");
    addButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                executeQuery("INSERT INTO book_details VALUES (" + bookIdTextField.getText() + ", '" +
                        titleTextField.getText() + "', '" +
                        authorTextField.getText() + "', '" +
                        genreTextField.getText() + "', " +
                        yearTextField.getText() + ", " +
                        quantityTextField.getText() + ")");
            } catch (ClassNotFoundException | SQLException exception) {
                exception.printStackTrace();
            }
        }
    });

    JButton deleteButton = new JButton("Delete Book");
    deleteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                executeQuery("DELETE FROM book_details WHERE book_id = '" + bookIdTextField.getText() + "'");
            } catch (ClassNotFoundException | SQLException exception) {
                exception.printStackTrace();
            }
        }
    });

    JButton updateButton = new JButton("Update Book");
    updateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            StringBuilder query = new StringBuilder("UPDATE book_details SET ");
            query.append("title = '").append(titleTextField.getText()).append("', ");
            query.append("author = '").append(authorTextField.getText()).append("', ");
            query.append("genre = '").append(genreTextField.getText()).append("', ");
            query.append("publication_year = ").append(yearTextField.getText()).append(", ");
            query.append("available_quantity = ").append(quantityTextField.getText()).append(" ");
            query.append("WHERE book_id = '").append(bookIdTextField.getText()).append("'");

            try {
                executeQuery(query.toString());
            } catch (ClassNotFoundException | SQLException exception) {
                exception.printStackTrace();
            }
        }
    });

    bookPanel.add(bookIdLabel);
    bookPanel.add(bookIdTextField);
    bookPanel.add(titleLabel);
    bookPanel.add(titleTextField);
    bookPanel.add(authorLabel);
    bookPanel.add(authorTextField);
    bookPanel.add(genreLabel);
    bookPanel.add(genreTextField);
    bookPanel.add(yearLabel);
    bookPanel.add(yearTextField);
    bookPanel.add(quantityLabel);
    bookPanel.add(quantityTextField);
    bookPanel.add(addButton);
    bookPanel.add(deleteButton);
    bookPanel.add(updateButton);

    bookFrame.add(bookPanel);
    bookFrame.setVisible(true);
}


    private void showIssueManagementWindow() {
    try {
        executeQuery("SELECT * FROM issue_details");
    } catch (ClassNotFoundException | SQLException exception) {
        exception.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error fetching issue details: " + exception.getMessage());
    }

    JFrame issueFrame = new JFrame("Manage Issued Books");
    issueFrame.setSize(400, 300);

    JPanel issuePanel = new JPanel();
    issuePanel.setLayout(new GridLayout(4, 2));

    JLabel issueIdLabel = new JLabel("Issue ID:");
    JTextField issueIdTextField = new JTextField();
    JLabel readerIdLabel = new JLabel("Reader ID:");
    JTextField readerIdTextField = new JTextField();
    JLabel bookIdLabel = new JLabel("Book ID:");
    JTextField bookIdTextField = new JTextField();
    JLabel issueDateLabel = new JLabel("Issue Date:");
    JTextField issueDateTextField = new JTextField();
    JLabel dueDateLabel = new JLabel("Due Date:");
    JTextField dueDateTextField = new JTextField();

    JButton addButton = new JButton("Issue Book");
    addButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                executeQuery("INSERT INTO issue_details VALUES (" + issueIdTextField.getText() + ", " +
                        readerIdTextField.getText() + ", " +
                        bookIdTextField.getText() + ", '" +
                        issueDateTextField.getText() + "', '" +
                        dueDateTextField.getText() + "')");
            } catch (ClassNotFoundException | SQLException exception) {
                exception.printStackTrace();
            }
        }
    });

    JButton returnButton = new JButton("Return Book");
    returnButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                executeQuery("DELETE FROM issue_details WHERE issue_id = " + issueIdTextField.getText());
            } catch (ClassNotFoundException | SQLException exception) {
                exception.printStackTrace();
            }
        }
    });

    issuePanel.add(issueIdLabel);
    issuePanel.add(issueIdTextField);
    issuePanel.add(readerIdLabel);
    issuePanel.add(readerIdTextField);
    issuePanel.add(bookIdLabel);
    issuePanel.add(bookIdTextField);
    issuePanel.add(issueDateLabel);
    issuePanel.add(issueDateTextField);
    issuePanel.add(dueDateLabel);
    issuePanel.add(dueDateTextField);
    issuePanel.add(addButton);
    issuePanel.add(returnButton);

    issueFrame.add(issuePanel);
    issueFrame.setVisible(true);
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
