import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener,ActionListener{
	
	private boolean play=false;
	private int HighestScore=0;
	private int score=0;
	private int totalBricks=32;
	private Timer timer;
	private int delay=10;
	private int playerX=310;
	private int ballposX=120;
	private int ballposY=450;
	private int ballXdir=-1;
	private int ballYdir=-2;
	 private MapGenerator map;
public GamePlay()	{
	map=new MapGenerator(4,8);
	addKeyListener(this);
	setFocusable(true);
	 setFocusTraversalKeysEnabled(false);
	 timer=new Timer(delay,this);
	   timer.start();
	  

}
	 public void paintComponent(Graphics g){
		 super.paintComponent(g);
		 Graphics2D g2=(Graphics2D) g;
		    setBackground(Color.black);
		    
		    map.draw((Graphics2D) g);
		    
		    g2.setColor(Color.yellow);
		     g2.fillOval(ballposX,ballposY,20,20);
		     
		     g2.setColor(Color.green);
		 	g2.fillRect(playerX,750,100,5);
		 	
		 	g2.setColor(Color.white);
		 g2.setFont(new Font("arial",Font.BOLD,25));
		 g2.drawString(""+score, 720, 30);
		 	
		 	g2.setColor(Color.yellow);
		 	g2.fillRect(0,0,800,5);
		 	g2.fillRect(0,0,5,800);
		 	g2.fillRect(790,0,5,800);
		 	
		 	if(ballposY >750)
		 	{
		 		play=false;
		 		ballXdir=0;
		 		ballYdir=0;
		 		g2.setColor(Color.red);
		 		g2.setFont(new Font("serif",Font.BOLD,30));
		 		g2.drawString("Game Over! Score "+score,310,350);
		 		

		 		g2.setColor(Color.red);
		 		g2.setFont(new Font("serif",Font.BOLD,35));
		 		if(score>HighestScore)
		 		g2.drawString("Highest Score "+ score,310,390);
		 		else
		 			g2.drawString("Highest Score "+ HighestScore,310,390);
		 		
		 		g2.setColor(Color.red);
		 		g2.setFont(new Font("serif",Font.BOLD,30));
		 		g2.drawString("Press Enter to Start",310,490);
		 		
		 		
		 	}
		 	
		 	g2.dispose();
		 	
		 }

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play){
			if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,750,100,5))){
				ballYdir = -ballYdir;
			}
			
		A:	for(int i=0;i<map.map.length;i++){
				for(int j=0;j<map.map[0].length;j++){
					if(map.map[i][j]>0){
						int brickX=j* map.brickWidth + 50;
						int brickY=i*  map.brickHeight + 100;
						int brickWidth= map.brickWidth;
						int brickHeight=map.brickHeight;
						 Rectangle brickRect =new Rectangle(brickX,brickY,brickWidth,brickHeight);
						 Rectangle ballRect=new Rectangle(ballposX,ballposY,20,20);
						 if(ballRect.intersects(brickRect)){
							 map.setBrickValue(0,i,j);
							 totalBricks--;
							 score +=5;
							 if(ballposX + 19<=brickRect.x ||ballposX+1 >=brickRect.x + brickRect.width){
								 ballXdir=-ballXdir;
							 }
							 else
							 {
								 ballYdir=-ballYdir;
							 }
						break A;	 
						 }
								 
							 
						 }
						
							
					}
				}
			
			ballposX +=ballXdir;
		  ballposY += ballYdir;
		  if(ballposX <0){
			  ballXdir = -ballXdir;
		  }
		  if(ballposY <0){
			  ballYdir = -ballYdir;
		  }
		  if(ballposX >700){
			  ballXdir = -ballXdir;
		  }
		}
		repaint();
		
}





	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			if(!play){
				play=true;
				ballposX=120;
				ballposY=250;
				ballXdir=-1;
				ballYdir=-2;
				playerX=310;
				if(score>HighestScore)
				 HighestScore=score;
				score=0;
				totalBricks=21;
				map=new MapGenerator(3,7);
				repaint();
			}
			
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			if(playerX >=700){
				playerX=700;
			}else{
				moveRight();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			if(playerX <10 ){
				playerX=10;
			}else{
				moveLeft();
			}
		}
	}
public void moveRight(){
	play=true;
	playerX +=30;
}
public void moveLeft(){
	play=true;
	playerX -=30;
}
    @Override
	public void keyReleased(KeyEvent e) {}
    @Override
	public void keyTyped(KeyEvent e) {}

}
