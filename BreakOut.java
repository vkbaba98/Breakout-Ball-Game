import javax.swing.*;
public class BreakOut {
 public static void main(String args[]) 
	 {
	    GamePlay game=new GamePlay();
		JFrame f= new JFrame("Breakout Ball Game");
	  	f.add(game);
		f.setSize(800,800);
		f.setResizable(false);
		f.setVisible(true);;
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 } 	
}
