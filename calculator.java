import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    // Create UI components
    private JTextField textField;
    private String operator;
    private double num1, num2, result;

    public Calculator() {
        // Set up the frame
        setTitle("Simple Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create text field
        textField = new JTextField();
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        // Create buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("0123456789".contains(command)) {
            textField.setText(textField.getText() + command);
        } else if (command.equals("C")) {
            textField.setText("");
            num1 = num2 = result = 0;
            operator = "";
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case "+" -> result = num1 + num2;
                case "-" -> result = num1 - num2;
                case "*" -> result = num1 * num2;
                case "/" -> result = num1 / num2;
            }
            textField.setText(String.valueOf(result));
        } else {
            if (!operator.isEmpty()) {
                return; // Prevent multiple operators
            }
            operator = command;
            num1 = Double.parseDouble(textField.getText());
            textField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}
