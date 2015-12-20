// BY: ABAD HAMEED
// ENGI3051 LAB ASSIGNMENT #3 (MAKE-UP)
// GAME: CRAPS (GUI-based)

// NOTE: the code and comments for the game logic is from section 6.8 of the Java-How to Program (9e) 

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Craps extends JFrame 
{
	private JTextField diceTxt1, diceTxt2, diceSumTxt, pointTxt;
	private JLabel diceLbl1, diceLbl2, diceSumLbl, pointLbl, result;
	private JButton rollDiceBtn;
	
	// create random number generator for use in method rollDice
	private static final Random randomNumbers = new Random();
	// enumeration with constants that represent the game status
	private enum Status { BEGIN, CONTINUE, WON, LOST };
	// constants that represent common rolls of the dice
	private static final int SNAKE_EYES = 2;
	private static final int TREY = 3;
	private static final int SEVEN = 7;
	private static final int YO_LEVEN = 11;
	private static final int BOX_CARS = 12;
	
	static int die1, die2, sum, myPoint=0;
	Status gameStatus = Status.BEGIN;
	
	public Craps()
	{
		super("Craps");
		super.setLocation(550,200);
		setLayout(new FlowLayout());
		
		diceLbl1 = new JLabel("DIE 1:");
		diceTxt1 = new JTextField(2);
		
		diceLbl2 = new JLabel("DIE 2:");
		diceTxt2 = new JTextField(2);
		
		diceSumLbl = new JLabel("DICE SUM:");
		diceSumTxt = new JTextField(2);
		
		pointLbl = new JLabel("POINT:");
		pointTxt = new JTextField(2);
		
		add(diceLbl1);
		add(diceTxt1);
		diceTxt1.setEditable(false);
		
		add(diceLbl2);
		add(diceTxt2);
		diceTxt2.setEditable(false);
		
		add(diceSumLbl);
		add(diceSumTxt);
		diceSumTxt.setEditable(false);
		
		add(pointLbl);		
		add(pointTxt);
		pointTxt.setEditable(false);
				
		rollDiceBtn = new JButton("Roll Dice");
		add(rollDiceBtn);
		
		result = new JLabel();
		add(result);
		
		ActionHandler handler = new ActionHandler();
		rollDiceBtn.addActionListener(handler);
	}
	
	private class ActionHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent event)
		{
			if (event.getSource() == rollDiceBtn && gameStatus == Status.CONTINUE)
			{
				getContentPane().setBackground(Color.LIGHT_GRAY);
				result.setText("");
				
				die1 = 1 + randomNumbers.nextInt(6); // first die roll
				die2 = 1 + randomNumbers.nextInt(6); // second die roll
				
				sum = die1 + die2; // sum of die values
				
				if (sum == myPoint) // win by making point
					gameStatus = Status.WON;
				else if (sum == SEVEN) // lose by rolling 7 before point
					gameStatus = Status.LOST;
				
				if(gameStatus == Status.WON)
				{
					result.setText("Player Wins!");
					getContentPane().setBackground(Color.GREEN);
					gameStatus = Status.BEGIN;
					
					System.out.println("Player wins");	// for testing
				}
				else if(gameStatus == Status.LOST)
				{
					result.setText("Player Lost!");
					getContentPane().setBackground(Color.RED);
					gameStatus = Status.BEGIN;
					
					System.out.println("Player loses");	// for testing
				}
				
				diceTxt1.setText("" + die1);
				diceTxt2.setText("" + die2);
				diceSumTxt.setText("" + sum);
				
				if(gameStatus == Status.BEGIN)
				{
					pointTxt.setText("");
					rollDiceBtn.setText("New Roll!");
				}
				else
					pointTxt.setText("" + myPoint);
			}
			// this statement would run only for the first roll
			else if (event.getSource() == rollDiceBtn && gameStatus == Status.BEGIN) 
			{
				getContentPane().setBackground(Color.LIGHT_GRAY);
				rollDiceBtn.setText("Roll Dice");
				result.setText("");
				
				die1 = 1 + randomNumbers.nextInt(6); // first die roll
				die2 = 1 + randomNumbers.nextInt(6); // second die roll
				
				sum = die1 + die2; // sum of die values
				System.out.println("Die 1: " + die1 + ", Die2: " + die2 + "\nSum of Dice: " + sum); //for testing
				
				switch (sum)
				{
					case SEVEN: // win with 7 on first roll
					case YO_LEVEN: // win with 11 on first roll
						gameStatus = Status.WON;
						break;
					case SNAKE_EYES: // lose with 2 on first roll
					case TREY: // lose with 3 on first roll
					case BOX_CARS: // lose with 12 on first roll
						gameStatus = Status.LOST;
						break;
					default: // did not win or lose, so remember point
						gameStatus = Status.CONTINUE; // game is not over
						myPoint = sum; // remember the point
						getContentPane().setBackground(Color.CYAN);
						result.setText("Point Set!");
						System.out.printf("Point is %d\n", myPoint );
						break; // optional at end of switch
				}

				// display won or lost message
				if( gameStatus == Status.WON )
				{
					result.setText("Player Wins!");
					getContentPane().setBackground(Color.GREEN);
					gameStatus = Status.BEGIN;
					
					System.out.println("Player wins");	// for testing
				}
				else if( gameStatus == Status.LOST )
				{
					result.setText("Player Lost!");
					getContentPane().setBackground(Color.RED);
					gameStatus = Status.BEGIN;
					
					System.out.println("Player loses");	// for testing
				}
				
				if(gameStatus == Status.BEGIN)
				{
					pointTxt.setText("");
					rollDiceBtn.setText("New Roll!");
				}
				
				diceTxt1.setText("" + die1);
				diceTxt2.setText("" + die2);
				diceSumTxt.setText("" + sum);
				
				if(myPoint==0)
					pointTxt.setText("");
				else
					pointTxt.setText("" + myPoint);
			}
		}
	}
	
	public static void main(String[] args) 
	{
		Craps crapsFrame = new Craps();
		
		crapsFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
		crapsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		crapsFrame.setSize(350, 100);
		crapsFrame.setVisible(true);
		crapsFrame.setResizable(false);
	}
}
