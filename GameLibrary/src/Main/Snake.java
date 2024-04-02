package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Snake extends JPanel implements ActionListener, KeyListener{
	
	private class Tile{
		int x;
		int y;
		
		Tile(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	int boardHeight = 804;
	int boardWidth = 979;
	int tileSize = 35;
	
	//Snake
	Tile snakeHead;
	ArrayList<Tile> snakeBody;
	
	//food
	Tile food;
	Random rand;
	
	//game logic
	Timer gameLoop;
	int velocityX;
	int velocityY;
	boolean gameOver =false;
	
	Snake(){
		setBackground(Color.BLACK);
		setBounds(100, 100, 300, 300);
		setFocusable(true);
		addKeyListener(this);
		
		
		
		snakeHead = new Tile(5,5);
		snakeBody = new ArrayList<Tile>();
		
		food = new Tile (10, 10);
		rand = new Random();
		placeFood();
		
		velocityX = 0;
		velocityY = 0;
		
		gameLoop = new Timer(100, this);
		gameLoop.start();
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);;
		draw(g);
	}
	
	public void draw(Graphics g) {
		//Grid
		/*for(int i = 0; i< getWidth()/tileSize; i++) {
		
		g.drawLine(i*tileSize, 0, i*tileSize, getHeight());
		g.drawLine(0, i*tileSize, getWidth(), i*tileSize); 
		
		}*/
		//Food
		g.setColor(Color.red);
		g.fill3DRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize, true);
		//Snake Head
		g.setColor(Color.green);
		g.fill3DRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize, true);
		//Snake Body
		for (int i = 0; i< snakeBody.size(); i++) {
			Tile snakePart = snakeBody.get(i);
			g.fill3DRect(snakePart.x *  tileSize, snakePart.y *  tileSize, tileSize, tileSize, true);
		}
		
		//Score
		g.setFont(new Font("Arial", Font.PLAIN, 16));
		if(gameOver) {
			g.setColor(Color.RED);
			g.drawString("Game Over: "+ String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
		} else {
			g.drawString("Score: "+ String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
		}
	}
	
	public void placeFood() {
		food.x = rand.nextInt(getWidth()/tileSize);
		food.y = rand.nextInt(getHeight()/tileSize);
	}
	
	public boolean collision(Tile tile1, Tile tile2) {
		return tile1.x == tile2.x && tile1.y == tile2.y;
	}
	
	public void move() {
		
		if(collision(snakeHead, food)) {
			snakeBody.add(new Tile(food.x, food.y));
			placeFood();
		}
		
		//Snake body
		for(int i = snakeBody.size()-1; i>=0; i--) {
			Tile snakePart = snakeBody.get(i);
			if(i==0) {
				snakePart.x = snakeHead.x;
				snakePart.y = snakeHead.y;
			}else {
				Tile prevSnakePart = snakeBody.get(i-1);
				snakePart.x = prevSnakePart.x;
				snakePart.y = prevSnakePart.y;
			}
		}
		snakeHead.x += velocityX;
		snakeHead.y += velocityY;
		
		for(int i = 0; i < snakeBody.size(); i++) {
			Tile snakePart = snakeBody.get(i);
			//collide with the snake head
			if(collision(snakeHead, snakePart)) {
				gameOver =true;
			}
		}
		if(snakeHead.x *tileSize < 0 || snakeHead.x*tileSize > boardWidth || snakeHead.y *tileSize < 0 || snakeHead.y *tileSize > boardHeight) {
			gameOver = true;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		move();
		repaint();
		if(gameOver) {
			gameLoop.stop();
		}
		
	}
	
	public void restartGame() {
	    // Reset game state
	    snakeHead.x = 5;
	    snakeHead.y = 5;
	    snakeBody.clear();
	    placeFood();
	    velocityX = 0;
	    velocityY = 0;
	    gameOver = false;
	    
	    // Restart the game loop
	    if (!gameLoop.isRunning()) {
	        gameLoop.start();
	    }
	}

	@Override
	public void keyPressed(KeyEvent e) {
		 
		if(e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
			velocityX = 0;
			velocityY = -1;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
			velocityX = 0;
			velocityY = 1;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
			velocityX = -1;
			velocityY = 0;
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
			velocityX = 1;
			velocityY = 0;
		}
	}
	
	//don't need
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
}
