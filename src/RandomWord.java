import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;

public class RandomWord {

	private JFrame frmRandomWord;
	private JTextField txtAddWord;
	private Words words;
	private ArrayList<String> list;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RandomWord window = new RandomWord();
					window.frmRandomWord.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public RandomWord() throws IOException {
		words = new Words();
		list = words.getWords();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frmRandomWord = new JFrame();
		frmRandomWord.setResizable(false);
		frmRandomWord.setTitle("Wordlist Randomizer by KoderM");
		frmRandomWord.setBounds(100, 100, 561, 312);
		frmRandomWord.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRandomWord.getContentPane().setLayout(null);
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		for(String item: list) {
			listModel.addElement(item);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(434, 64, 101, 152);
		frmRandomWord.getContentPane().add(scrollPane);
		
		JList<String> list_1 = new JList<String>(listModel);
		scrollPane.setViewportView(list_1);
		
		
		JLabel wordLabel = new JLabel("-");
		wordLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		wordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		wordLabel.setBounds(10, 99, 414, 35);
		frmRandomWord.getContentPane().add(wordLabel);
		
		JButton removeWordB = new JButton("Remove Word");
		removeWordB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				words.removeWord(list_1.getSelectedValue());
				listModel.removeElementAt(list_1.getSelectedIndex());
				words.getWords();
			}
		});
		removeWordB.setBounds(434, 227, 101, 23);
		frmRandomWord.getContentPane().add(removeWordB);
		
		
		
		//Get a randomly generated word
		JButton getWordButton = new JButton("Get Word");
		getWordButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		getWordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		         wordLabel.setText(words.getRandomWord());
			}
		});
		getWordButton.setBounds(10, 181, 414, 69);
		frmRandomWord.getContentPane().add(getWordButton);
		
		txtAddWord = new JTextField();
		txtAddWord.setBounds(434, 11, 101, 20);
		frmRandomWord.getContentPane().add(txtAddWord);
		txtAddWord.setColumns(10);
		
		JButton btnAddWord = new JButton("Add Word");
		btnAddWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				words.addWord(txtAddWord.getText());
				if(!txtAddWord.getText().isEmpty()) {
					listModel.addElement(txtAddWord.getText());
					txtAddWord.setText("");
				}
			}
		});
		btnAddWord.setBounds(434, 31, 101, 23);
		frmRandomWord.getContentPane().add(btnAddWord);
	}
}
