package Main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class TicTacToe extends JPanel {
	
	
	JButton[][] buttons = new JButton[3][3];
	
	
	String playerX = "X";
	String playerO = "O";
	
	String currentPlayer = playerX;
	
	boolean gameOver = false;
	
	int turns = 0;
	
	TicTacToe(){
		
		setBackground(new Color(75, 75, 75));
        
        
		setBounds(100, 100, 300, 300);
		
		setLayout(new GridLayout(3,3));
       
       
        for(int r = 0; r<3; r++) {
        	for(int c = 0; c<3; c++) {
        		JButton tile = new JButton();
        		buttons[r][c] = tile;
        		add(tile);
        		
        		tile.setBackground(Color.darkGray);
        		tile.setForeground(Color.white);
        		tile.setFont(new Font("Arial", Font.BOLD, 120));
        		tile.setFocusable(false);
        		
        		//tile.setText(currentPlayer);
        		
        		tile.addActionListener(new ActionListener(){ 
        			public void actionPerformed(ActionEvent e) {
        				if (gameOver) return;
        				JButton tile = (JButton) e.getSource();
        				if(tile.getText() == "") {
        					tile.setText(currentPlayer);
        					turns++;
            				checkWinner();
        					if(!gameOver) {
        						currentPlayer = currentPlayer == playerX ? playerO : playerX;
        						Main.updateTextField(currentPlayer + "'s turn.");
            				
        					}
        				}
        				
        			}
        		});
        	}
        }
		
       
        
}
	//horizontal Winner
	 void checkWinner() {
     	for(int r = 0; r<3; r++) {
     		if(buttons[r][0].getText() == "")continue;
     		if(buttons[r][0].getText() == buttons[r][1].getText()&& buttons[r][1].getText() == buttons[r][2].getText()) {
     			for(int i = 0; i<3; i++) {
     				setWinner(buttons[r][i]);
     			}
     			gameOver = true;
     			return;
     		}
     	}
     //vertical Winner
     	for(int c = 0; c<3; c++) {
     		if(buttons[0][c].getText()== "")continue;
     		if(buttons[0][c].getText() == buttons[1][c].getText()&& buttons[1][c].getText() == buttons[2][c].getText()) {
     			for(int i = 0; i<3; i++) {
     				setWinner(buttons[i][c]);
     			}
     			gameOver = true;
     			return;
     		}
     	}
     
     //diagonally
     	if(buttons[0][0].getText() == buttons[1][1].getText() && buttons[1][1].getText() == buttons[2][2].getText() 
     			&& buttons[0][0].getText() != "") {
     		for(int i = 0; i<3; i++) {
     			setWinner(buttons[i][i]);
     		}
     		gameOver = true;
 			return;
     	}
     //anti-diagnolly
     	if(buttons[0][2].getText() == buttons[1][1].getText() && buttons[1][1].getText() == buttons[2][0].getText() 
     			&& buttons[0][2].getText() != "") {
     		for(int i = 0; i<3; i++) {
     			setWinner(buttons[0][2]);
     			setWinner(buttons[1][1]);
     			setWinner(buttons[2][0]);
     		}
     		gameOver = true;
 			return;
     	}
     	if(turns == 9) {
     		for(int r = 0; r<3; r++) {
     			for(int c = 0; c<3; c++) {
     				setTie(buttons[r][c]);
     			}
     		}
     	}
	}
		
	 void setWinner(JButton tile) {
     	tile.setForeground(Color.green);
     	tile.setBackground(Color.gray);
     	Main.updateTextField(currentPlayer + " Wins!");
     	
     	 this.repaint();
     }
	 void setTie(JButton buttons) {
		 buttons.setForeground(Color.orange);
		 buttons.setBackground(Color.gray);
		 Main.updateTextField("Tie!");
	 }
	 public void restartGame() {
		    // Reset game state
		    turns = 0;
		    gameOver = false;
		    currentPlayer = playerX;
		    
		   
		    // Clear button text and reset colors
		    for (int r = 0; r < 3; r++) {
		        for (int c = 0; c < 3; c++) {
		            buttons[r][c].setText("");
		            buttons[r][c].setForeground(Color.white);
		            buttons[r][c].setBackground(Color.darkGray);
		        }
		    }

		    // Update status text
		    Main.updateTextField(currentPlayer + "'s turn.");
		}

		    
}