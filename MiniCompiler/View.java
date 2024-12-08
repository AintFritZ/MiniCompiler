package MiniCompiler;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class View {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Code Editor");
        frame.setSize(1300, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        frame.getContentPane().setBackground(new Color(230, 230, 250));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(0, 0, 250, 700);
        buttonPanel.setBackground(new Color(200, 200, 250));
        buttonPanel.setOpaque(true);
        frame.add(buttonPanel);

        JButton button1 = new JButton("Open File");
        button1.setBounds(30, 30, 200, 75);
        buttonPanel.add(button1);

        JButton button2 = new JButton("Lexical Analysis");
        button2.setBounds(30, 120, 200, 75);
        button2.setEnabled(false);
        buttonPanel.add(button2);

        JButton button3 = new JButton("Syntax Analysis");
        button3.setBounds(30, 210, 200, 75);
        button3.setEnabled(false);
        buttonPanel.add(button3);

        JButton button4 = new JButton("Semantic Analysis");
        button4.setBounds(30, 300, 200, 75);
        button4.setEnabled(false);
        buttonPanel.add(button4);

        JButton button5 = new JButton("Clear");
        button5.setBounds(30, 390, 200, 75);
        buttonPanel.add(button5);

        button1.setBackground(new Color(167, 199, 231));
        button2.setBackground(new Color(167, 199, 231));
        button3.setBackground(new Color(167, 199, 231));
        button4.setBackground(new Color(167, 199, 231));
        button5.setBackground(new Color(167, 199, 231));

        // Updated codePanel background color and JScrollPane
        JPanel codePanel = new JPanel();
        codePanel.setLayout(null);
        codePanel.setBounds(250, 0, 1050, 700);
        codePanel.setBackground(new Color(240, 240, 240)); // Set background color of code area
        codePanel.setOpaque(true);
        frame.add(codePanel);

        JTextField resultBar = new JTextField("Result: Ready");
        resultBar.setEditable(false);
        resultBar.setHorizontalAlignment(JTextField.LEFT);
        resultBar.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultBar.setBackground(new Color(240, 240, 240));
        resultBar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        resultBar.setBounds(10, 10, 1000, 30);
        codePanel.add(resultBar);

        JTextArea codeTextArea = new JTextArea();
        codeTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        codeTextArea.setLineWrap(true);
        codeTextArea.setWrapStyleWord(true);
        codeTextArea.setMargin(new Insets(10, 10, 10, 10));
        codeTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        codeTextArea.setEditable(false);
        codeTextArea.setBackground(new Color(211, 211, 211));

        JScrollPane scrollPane = new JScrollPane(codeTextArea);
        scrollPane.setBounds(10, 50, 1000, 590);
        scrollPane.getViewport().setBackground(new Color(211, 211, 211));
        scrollPane.setBackground(new Color(211, 211, 211));
        scrollPane.setOpaque(true);
        codePanel.add(scrollPane);

        frame.setVisible(true);

        button1.addActionListener(e -> {
            String fileContent = OpenFile.openFile(frame);
            if (fileContent != null) {
                codeTextArea.setText(fileContent);
                resultBar.setText("File Opened: " + fileContent.substring(0, Math.min(20, fileContent.length())) + "...");
                button2.setEnabled(true);
            } else {
                resultBar.setText("No file selected");
            }
        });

        button2.addActionListener(e -> {
            String code = codeTextArea.getText();
            boolean isValid = LexicalAnalysis.performLexicalAnalysis(code);
            resultBar.setText(isValid ? "Passed Lexical Analysis" : "Failed Lexical Analysis");
            if (isValid) {
                button3.setEnabled(true);
            } else {
                highlightClearButton(button5);
            }
        });

        button3.addActionListener(e -> {
            String code = codeTextArea.getText();
            List<String> codeLines = List.of(code.split("\\n"));
            boolean isSyntaxValid = SyntaxAnalyzer.performSyntaxAnalysis(codeLines);
            resultBar.setText(isSyntaxValid ? "Passed Syntax Analysis" : "Failed Syntax Analysis");
            if (isSyntaxValid) {
                button4.setEnabled(true);
            } else {
                highlightClearButton(button5);
            }
        });

        button4.addActionListener(e -> {
            String code = codeTextArea.getText();
            List<String> codeLines = List.of(code.split("\\n"));
            boolean isSemanticValid = SemanticAnalysis.performSemanticAnalysis(codeLines);
            resultBar.setText(isSemanticValid ? "Passed Semantic Analysis" : "Failed Semantic Analysis");
            if (!isSemanticValid) {
                highlightClearButton(button5);
            }
        });

        button5.addActionListener(e -> {
            codeTextArea.setText("");
            resultBar.setText("Result: Ready");
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
            button5.setBackground(null);
        });
    }

    private static void highlightClearButton(JButton button) {
        button.setBackground(Color.RED);
    }
}
