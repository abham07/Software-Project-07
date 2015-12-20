import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.util.Random;

public class NumGuess extends JFrame {
	
	private JTextField guess;
	private JButton newGame;
	private JLabel info1, info2, result;
	
	private Random generator;
	private int number, guessedNum, lastDist, currentDist;
	
	public NumGuess() {
		
		super("Guessing Game");
		super.setLocation(500, 300);
		setLayout(new FlowLayout());
								
		generator = new Random();
		number = generator.nextInt(1001);
		lastDist = 1000; //initial values (first guess should be warm within 1000 range)
		currentDist = 0;
				
		info1 = new JLabel("I have a number between 1 and 1000. ");
		info2 = new JLabel("Can you guess my number? Please enter your guess: ");
		add(info1);
		add(info2);
		
		guess = new JTextField(10);
		add(guess);
		
		result = new JLabel("Guess result appears here.");
		add(result);
		
		newGame = new JButton("New Game");
		add(newGame);
		
		ActionHandler handler = new ActionHandler();
		guess.addActionListener(handler);
		newGame.addActionListener(handler);
	}
	
	private class ActionHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
						
			if (event.getSource() == guess) {
				guessedNum = Integer.parseInt(guess.getText());
				currentDist = Math.abs(number - guessedNum);
				
				if(currentDist > lastDist)
					getContentPane().setBackground(Color.BLUE);
				else
					getContentPane().setBackground(Color.RED);
				
				lastDist = currentDist;
				
				if(guessedNum > number)
					result.setText("Too High. Try a Lower Number");
				else if(guessedNum < number)
					result.setText("Too Low. Try a Higher Number");
				else {
					result.setText("Correct!");
					guess.setEditable(false);
					getContentPane().setBackground(Color.GREEN);
				}
			}
			else if (event.getSource() == newGame) {
				getContentPane().setBackground(Color.LIGHT_GRAY);
				guess.setEditable(true);
				number = generator.nextInt(1001);
				System.out.println("The number is: " + number); //for testing
			}
		}
	}
	
	public static void main(String[] args) {
		NumGuess guessFrame = new NumGuess();
		guessFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
		guessFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guessFrame.setSize(350, 150);
		guessFrame.setVisible(true);
	}
}