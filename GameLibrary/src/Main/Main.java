package Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {

	public static JLabel textfield = new JLabel();
	public static void main(String[] args) {
		
		
		JPanel titlePanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel cardPanel;
		
		JButton[] buttons = new JButton[3];
		JFrame window = new JFrame();
		
		JButton TicTacToe, Snake;
		
		CardLayout cardLayout;
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Game Library");
		window.setPreferredSize(new Dimension(1000,1000));
		window.getContentPane().setBackground(new Color(75, 75, 75));
		window.setLayout(new BorderLayout());
		window.pack();
		
		
		TicTacToe = new JButton("Tic Tac Toe");
		Snake = new JButton("Snake");
		
		
		
		textfield.setBackground(Color.darkGray);
		textfield.setForeground(Color.white);
		textfield.setFont(new Font("Arial", Font.BOLD, 70));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Game Library");
		textfield.setOpaque(true);
		
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBounds(0, 0, 600, 100);
		
		buttonPanel.setLayout(new BorderLayout());
		
		buttonPanel.setLayout(new GridLayout(0, 2));
		buttonPanel.setBackground(new Color(50, 50, 50));
		
		
		TicTacToe.setFont(new Font(null, Font.BOLD, 50));
		
		Snake.setFont(new Font(null, Font.BOLD, 50));
		
		JButton restartButton = new JButton("Restart");

		buttonPanel.add(Snake); 
		
		buttonPanel.add(TicTacToe);
		
		window.add(buttonPanel, BorderLayout.SOUTH);
		titlePanel.add(textfield);
		window.add(titlePanel, BorderLayout.NORTH);
		
		cardPanel = new JPanel();
		cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        cardPanel.setSize(980, 805);
        
        cardPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                // Request focus for the newly shown component
                Component src = (Component) e.getSource();
                if (src instanceof Snake) {
                    src.requestFocusInWindow();
                }
            }
        });
        
        cardPanel.add(new Snake(), "Snake");
        
        cardPanel.add(new TicTacToe(), "TicTacToe");
        // Add other game panels similarly
        
        
        
        window.add(cardPanel, BorderLayout.CENTER);
		
        window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		
		
		TicTacToe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JButton TicTacToe = (JButton) e.getSource();
				cardLayout.show(cardPanel, "TicTacToe");
				textfield.setText("Tic Tac Toe");
				
				if (cardPanel.getComponent(1) instanceof TicTacToe) {
		            TicTacToe ticTacToeGame = (TicTacToe) cardPanel.getComponent(1);
		            ticTacToeGame.restartGame();
		        }
				
		}
			
		});
		
		Snake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton Snake = (JButton) e.getSource();
				cardLayout.show(cardPanel, "Snake");
				textfield.setText("Snake");
				
				if (cardPanel.getComponent(0) instanceof Snake) {
		            Snake snakeGame = (Snake) cardPanel.getComponent(0);
		            snakeGame.restartGame();
		        }
				
				// Request focus for the Snake panel or it wont focus
				Component[] components = cardPanel.getComponents();
			        for (Component component : components) {
			            if (component instanceof Snake) {
			                component.requestFocusInWindow();
			                break;
			            }
			        }
				
		}
			
		});
		
		
		
	}
	public static void updateTextField(String message) {
		textfield.setText(message);
	}
}
