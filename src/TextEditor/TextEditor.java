package TextEditor;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TextEditor extends JFrame implements ActionListener 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
    private JButton openButton;
    private JButton newButton;
    private JButton saveButton;
    private JButton exitButton;

    public TextEditor()
    {
        setTitle("Text Editor");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        openButton = new JButton("Open");
        newButton = new JButton("New");
        saveButton = new JButton("Save");
        exitButton = new JButton("Exit");

        openButton.addActionListener(this);
        newButton.addActionListener(this);
        saveButton.addActionListener(this);
        exitButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(newButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(exitButton);

        Container container = getContentPane();
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == openButton)
        {
            openFile();
        } 
        else if (e.getSource() == newButton) 
        {
            newFile();
        }
        else if (e.getSource() == saveButton)
        {
            saveFile();
        } 
        else if (e.getSource() == exitButton) 
        {
            exit();
        }
    }

    private void openFile() 
    {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION)
        {
            try 
            {
                File file = fileChooser.getSelectedFile();
                BufferedReader reader = new BufferedReader(new FileReader(file));
                textArea.read(reader, null);
                reader.close();
            } catch (IOException e) 
            {
                JOptionPane.showMessageDialog(this, "Error opening file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void newFile() 
    {
        textArea.setText("");
    }

    private void saveFile() 
    {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) 
        {
            try 
            {
                File file = fileChooser.getSelectedFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                textArea.write(writer);
                writer.close();
            } 
            catch (IOException e) 
            {
                JOptionPane.showMessageDialog(this, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void exit() 
    {
        System.exit(0);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            TextEditor textEditor = new TextEditor();
            textEditor.setVisible(true);
        });
    }
}
