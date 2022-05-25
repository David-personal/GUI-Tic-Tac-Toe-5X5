

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//este JPanel esta dentro del jframe
//nuestra clase va ser subclass de JPanel

public class TicTacToe extends JPanel{
	
	//siempre primero empieza por x 
	char playerMark = 'x';
	JButton[] buttons = new JButton[25]; 
	
	//constructor
	public TicTacToe(){
		setLayout(new GridLayout(5,5)); 
		initializeButtons();
	}
	
	public void initializeButtons()
	{
		for(int i=0; i<=24; i++)  
		{
			buttons[i] = new JButton();
			buttons[i].setText("-");
			buttons[i].setBackground(Color.white);
			buttons[i].addActionListener(new ActionListener() {  
			
				public void actionPerformed(ActionEvent e) {
					JButton buttonClicked = (JButton) e.getSource(); //get the button that was clicked
					buttonClicked.setText(String.valueOf(playerMark));
			//change the text of that button - to X
		    //this is when we press the button the "-" changes to an X
				
					if(playerMark == 'x') //cambiamos X a O xk queremos k el otro jugador juege
					{
						playerMark = 'o';
						buttonClicked.setBackground(Color.red);
					}
					else  //si el playermark era O, lo cambiamos a X
					{
						playerMark='x';
						buttonClicked.setBackground(Color.cyan);
					}
				displayVictor();
				
				}
			
			});
			
			add(buttons[i]);   //add this buttons to JPanel
	
	}
}
	//desplega el jugador ganador
	public void displayVictor()
	{
		if(checkForWinner()==true)
		{
			//reverse the player marks
			//cus after we put x and we win, the game changes it to o
			//but x is the winner
			
			if(playerMark =='x') playerMark ='o';
			else playerMark ='x';
			
			JOptionPane pane = new JOptionPane();
			int dialogResult = JOptionPane.showConfirmDialog(pane, "Game Over. " + playerMark + " wins. Would you like to play again?", "Game over.",
					JOptionPane.YES_NO_OPTION);
			//if press YES
			if(dialogResult == JOptionPane.YES_OPTION)resetTheButtons();
			else System.exit(0);
		}
		
		else if(checkDraw())
		{
			JOptionPane pane = new JOptionPane();
			int dialogResult = JOptionPane.showConfirmDialog(pane, "Draw. Play again?", "Game over.", JOptionPane.YES_NO_OPTION);
		    if(dialogResult == JOptionPane.YES_OPTION) resetTheButtons(); //si dice que si, reseteamos los botones
		    else System.exit(0);
		}	
	}
	
	//reset the board
	//para jugar d nuevo
		private void resetTheButtons() {
			playerMark = 'x';
			for(int i=0;i<24;i++) {
				buttons[i].setText("-");
				buttons[i].setBackground(Color.white);
			}
		}
		
		//check for draw
		public boolean checkDraw() {
			boolean full =true;   //asumimos que el borde esta lleno de X y O
			for(int i =0;i<24;i++) {    //revisar todos los botones si son iguales a -
				if(buttons[i].getText().charAt(0)=='-') {
					full = false; //si no esta lleno el borde
				}
			}
			return full;
		}
		
		
	//check for a winner
		public boolean checkForWinner() {
			if(checkRows()==true || checkColumns()==true || checkDiagonals()==true) return true;
			else return false;
		}

		
		
		
		// 0   1    2    3    4
		// 5   6    7    8    9
		// 10  11   12   13   14
		// 15  16   17   18   10
		// 20  21   22   23   24
		
		
		public boolean checkRows() {
			
			int i=0;
			for(int j=0;j<5;j++)  
			{  //take the first button, we get the text, then check it with the 2nd button
				if(buttons[i].getText().equals(buttons[i+1].getText()) && buttons[i].getText().equals(buttons[i+2].getText()) && buttons[i].getText().equals(buttons[i+3].getText()) && buttons[i].getText().equals(buttons[i+4].getText())
				&& buttons[i].getText().charAt(0) != '-'){ 
				//when we take the text from button and convert it to a character
				//it should not be a -
					return true;
				}
				i = i+5;
			}

			return false;

		}
		
		public boolean checkColumns() {
			
			
			// 0   1    2    3    4
			// 5   6    7    8    9
			// 10  11   12   13   14
			// 15  16   17   18   10
			// 20  21   22   23   24
			
			int i=0;
			for(int j=0;j<5;j++) { 
				if(buttons[i].getText().equals(buttons[i+5].getText()) && buttons[i].getText().equals(buttons[i+10].getText()) && buttons[i].getText().equals(buttons[i+15].getText()) && buttons[i].getText().equals(buttons[i+20].getText())
						&& buttons[i].getText().charAt(0) != '-')
				{
					return true;
				}
				i++;
			}
			return false;
		}
		
		//check diagonals for a win 
		public boolean checkDiagonals() {
			
			
			            // 0   1    2    3    4
			 			// 5   6    7    8    9
						// 10  11   12   13   14
						// 15  16   17   18   10
						// 20  21   22   23   24
			
			
			if(buttons[0].getText().equals(buttons[6].getText()) && buttons[0].getText().equals(buttons[12].getText()) && buttons[0].getText().equals(buttons[18].getText()) && buttons[0].getText().equals(buttons[24].getText())
					&& buttons[0].getText().charAt(0) != '-')
			return true;
			else if(buttons[4].getText().equals(buttons[8].getText()) && buttons[4].getText().equals(buttons[12].getText()) && buttons[4].getText().equals(buttons[16].getText()) && buttons[4].getText().equals(buttons[20].getText())
					&& buttons[4].getText().charAt(0) != '-') return true;
			
			else return false;
		}
	
	
	
	public static void main(String[] args) {
//	    System.out.println("Throw a coin to decide who plays first");
//		System.out.println("If it's head, you start to play");
//		System.out.println("If it's tail, the computer starts to play");
//		System.out.println("Now type,(throw) to throw the coin");
//		Scanner scanner=new Scanner(System.in);
//		boolean valid = false;
//		
//		while(!valid)
//		{
//			String num=scanner.next();
//			
//			if(num.equals("throw"))
//			{
//				valid = true;
//				if (Math.random() < 0.5)
//				{
//					System.out.println("Heads, you start the game");
//					
//				}
//					else
//					{
//						System.out.println("Tails, the computer starts the game");	
//					}
//			}
//			else
//			{
//				System.out.println("please type (throw) to start the game");
//			}
//
//		}
		//frames hold all your components
		JFrame window = new JFrame("Welcome to Tic Tac Toe");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(new TicTacToe()); //adds the data
		window.setBounds(500,500,500,500);
		window.setVisible(true); //show the window on the screen
		window.setLocationRelativeTo(null); //aparece en el centro de la pantalla
	}

}