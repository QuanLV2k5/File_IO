import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Notepad_demo extends JFrame {
	private JTextArea textArea;
	private JFileChooser fileChooser;
	private File currentFile;

	public Notepad_demo() {
		setTitle("Notepad");
		setSize(670, 770);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open");
		JMenuItem saveItem = new JMenuItem("Save");
		JMenuItem saveAsItem = new JMenuItem("Save As");

		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(saveAsItem);
		menuBar.add(fileMenu);

		setJMenuBar(menuBar);

		fileChooser = new JFileChooser();

		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					readFile(selectedFile);
				}
			}
		});

		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentFile != null) {
					saveFile(currentFile);
				} else {
					saveAs();
				}
			}
		});

		saveAsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAs();
			}
		});
	}

	private void readFile(File file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
			textArea.setText(sb.toString());
			currentFile = file;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void saveFile(File file) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(textArea.getText());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void saveAs() {
		int returnValue = fileChooser.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			saveFile(selectedFile);
			currentFile = selectedFile;
		}
	}
	
	public static void main(String[] args) {
		Notepad_demo notepad = new Notepad_demo();
		notepad.setVisible(true);
		notepad.setLocationRelativeTo(null);
	}
}
