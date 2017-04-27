import java.awt.*;
import java.util.*;
import java.applet.*;
import java.awt.event.MouseEvent;
import javax.swing.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.JSONParser; 
import org.json.simple.parser.ParseException; 


/*<applet code="Main" height=400 width=400></applet>*/


public class Main extends Applet implements Runnable
{

/* Configuration arguments. These should be initialized with the values read from the config.JSON file*/					
    private int numBalls;
/*end of config arguments*/

    private int refreshrate = 15;	           //Refresh rate for the applet screen. Do not change this value. 
	private boolean isStoped = true;		     
    Font f = new Font ("Arial", Font.BOLD, 18);
	
	private Player player;			           //Player instance.
	private ShrinkBall redball;                      //Ball instance. You need to replace this with an array of balls.     
	//storing balls in the array
	private Ball[] BallList;
	Thread th;						           //The applet thread. 
	  
    Cursor c;				
    private GameWindow gwindow;                 // Defines the borders of the applet screen. A ball is considered "out" when it moves out of these borders.
	private Image dbImage;
	private Graphics dbg;

	
	class HandleMouse extends MouseInputAdapter 
	{
		
		Dictionary dict = new Hashtable();

    	public HandleMouse() 
    	{
            addMouseListener(this);
        }
		
    	public void mouseClicked(MouseEvent e) 
    	{
    		double rate = player.t_clickhit / player.t_clicks;
    		player.t_clicksuccess = rate;
    		player.t_clicks += 1;
        	if (!isStoped) {
				   for (Ball ball : BallList){
						if (ball.userHit (e.getX(), e.getY())) {
							ball.ballWasHit ();
							player.t_clickhit += 1;
							
							 
			        	}
					   }
				   


			}
			else if (isStoped && e.getClickCount() == 2) {
				isStoped = false;
				init ();
			}
    		
    	}

    	public void mouseReleased(MouseEvent e) 
    	{
           
    	}
        
    	public void RegisterHandler() 
    	{

    	}
    }
	
	HandleMouse hm = new HandleMouse();
    
	//JSON reader; you need to complete this function
	public JSONObject JSONReader()
	{
		JSONParser parser = new JSONParser();
		   try {
			   
	            Object obj = parser.parse(new FileReader(
	                    "config.JSON"));
	 
	            JSONObject jsonObject = (JSONObject) obj;
	 
	            String name = (String) ((JSONObject) jsonObject.get("GameWindow")).get("x_rightout");

	            return jsonObject;
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return null;
	}
	
	/*initialize the game*/
	public void init ()
	{	
		//reads info from JSON doc
		JSONObject jsonObject = this.JSONReader();
		
		int lives = Integer.parseInt((String) ((JSONObject) jsonObject.get("Player")).get("numLives"));
		int score2EarnLife = Integer.parseInt((String) ((JSONObject) jsonObject.get("Player")).get("score2EarnLife"));

		
		
		

		int x_rightout = Integer.parseInt((String) ((JSONObject) jsonObject.get("GameWindow")).get("x_rightout"));
		int x_leftout = Integer.parseInt((String) ((JSONObject) jsonObject.get("GameWindow")).get("x_leftout"));
		int y_upout = Integer.parseInt((String) ((JSONObject) jsonObject.get("GameWindow")).get("y_upout"));
		int y_downout = Integer.parseInt((String) ((JSONObject) jsonObject.get("GameWindow")).get("y_downout"));

		
		JSONArray m_ball = ((JSONArray) jsonObject.get("Ball"));
		BallList = new Ball[m_ball.size()];
	
		c = new Cursor (Cursor.CROSSHAIR_CURSOR);
		this.setCursor (c);	
				
		setBackground (Color.black);
		setFont (f);

		if (getParameter ("refreshrate") != null) {
			refreshrate = Integer.parseInt(getParameter("refreshrate"));
		}
		else refreshrate = 15;

		
		player = new Player ();
		/* The parameters for the GameWindow constructor (x_leftout, x_rightout, y_upout, y_downout) 
		should be initialized with the values read from the config.JSON file*/	
		gwindow = new GameWindow(x_leftout,x_rightout,y_upout,y_downout);
		this.setSize(gwindow.x_rightout, gwindow.y_downout); //set the size of the applet window.
		
		/*The skeleton code creates a single basic ball. Your game should support arbitrary number of balls. 
		* The number of balls and the types of those balls are specified in the config.JSON file.
		* The ball instances will be stores in an Array or Arraylist.  */
		/* The parameters for the Ball constructor (radius, initXpos, initYpos, speedX, speedY, maxBallSpeed, color) 
		should be initialized with the values read from the config.JSON file. Note that the "color" need to be initialized using the RGB values provided in the config.JSON file*/
		
		player.lives = lives;
		player.score2EarnLife = score2EarnLife;
//		Ball constructor (radius, initXpos, initYpos, speedX, speedY, maxBallSpeed, color) 
		for (int i = 0; i < m_ball.size(); i++) {
			JSONObject t1 = (JSONObject) m_ball.get(i);
			
			int id =  Integer.parseInt((String) (t1.get("id")));
			int radius =  Integer.parseInt((String) (t1.get("radius")));
			int initXpos =  Integer.parseInt((String) (t1.get("initXpos")));
			int initYpos =  Integer.parseInt((String) (t1.get("initYpos")));
			int speedX =  Integer.parseInt((String) (t1.get("speedX")));
			int speedY =  Integer.parseInt((String) (t1.get("speedY")));
			int maxBallSpeed =  Integer.parseInt((String) (t1.get("maxBallSpeed")));
			String type = (String) (t1.get("type"));
			
			ArrayList<String> colorlist = (ArrayList<String>) (t1.get("color"));
			
			int r = Integer.parseInt(colorlist.get(0));
			int g = Integer.parseInt(colorlist.get(1));
			int b = Integer.parseInt(colorlist.get(2));
			
			
			
			Color bcolor = new Color(r, g, b);
			
			
			
			
			if(type.equals("basicball"))
			{
				Ball mball = new Ball(radius, initXpos, initYpos, speedX, speedY, maxBallSpeed, bcolor, player, gwindow);
				mball.type = type;
				BallList[i] = mball;
			}//bounceball
			else if(type.equals("bounceball"))
			{
				int bounceCount =  Integer.parseInt((String) (t1.get("bounceCount")));
				
				BounceBall mball = new BounceBall(radius, initXpos, initYpos, speedX, speedY, maxBallSpeed, bcolor, player, gwindow);
				mball.BallCount = bounceCount;
				mball.type = type;
				BallList[i] = mball;
			}
			else if (type.equals("shrinkball"))
			{
				ShrinkBall mball = new ShrinkBall(radius, initXpos, initYpos, speedX, speedY, maxBallSpeed, bcolor, player, gwindow);
				mball.type = type;
				mball.shrinkrate =   Double.parseDouble((String) (t1.get("shrinkRate"))) / 100;
				
				BallList[i] = mball;
			}

			}

		numBalls = 1;
		
		
	}
	
	/*start the applet thread and start animating*/
	public void start ()
	{		
		if (th==null){
			th = new Thread (this);
		}
		th.start ();
	}
	
	public String GetType ()
	{
		   int max = 0;
		   String type = "";
		   for (Ball ball : BallList){
			   	if(ball.timehit > max)
			   	{
			   		max = ball.timehit;
			   		type = ball.type;
			   	}
			   }
		
		return type;
	}
	
	/*stop the thread*/
	public void stop ()
	{
		th=null;
	}

    
	public void run ()
	{	
		/*Lower this thread's priority so it won't interfere with other processing going on*/
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

        /*This is the animation loop. It continues until the user stops or closes the applet*/
		while (true) {
			if (!isStoped) {
				   for (Ball ball : BallList){
					   ball.move();
					   }
				
			}
            /*Display it*/
			repaint();
            
			try {
				
				Thread.sleep (refreshrate);
			}
			catch (InterruptedException ex) {
				
			}			
		}
	}

	
	public void paint (Graphics g)
	{
		/*if the game is still active draw the ball and display the player's score. If the game is active but stopped, ask player to double click to start the game*/ 
		if (!player.isGameOver()) {
			g.setColor (Color.yellow);
			
			g.drawString ("Score: " + player.getScore(), 10, 40);
			g.drawString("Lives: " + player.lives , 10, 70); // The player lives need to be displayed
			g.drawString("success rate: " + player.t_clicksuccess , 10, 110);
			
			for (Ball ball : BallList){
				ball.DrawBall(g);
			}
			
			
			if (isStoped) {
				g.setColor (Color.yellow);
				g.drawString ("Doubleclick on Applet to start Game!", 40, 200);
			}
		}
		/*if the game is over (i.e., the ball is out) display player's score*/
		else {
			g.setColor (Color.yellow);
			
			g.drawString ("Game over!", 130, 100);
			g.drawString ("You scored " + player.getScore() + " Points!", 90, 140);
			g.drawString("Statistics: ", 400, 160);
			g.drawString("Number of Clicks: " + player.t_clicks, 400, 180); // The number of clicks need to be displayed
			g.drawString("% of Successful Clicks: " + (int) (player.t_clicksuccess * 100) +"%",400,200); // The % of successful clicks need to be displayed
			g.drawString("Ball most hit: " + GetType(), 400, 240); // The nball that was hit the most need to be displayed
				
			g.drawString ("Doubleclick on the Applet, to play again!", 20, 220);

			isStoped = true;	
		}
	}

	
	public void update (Graphics g)
	{
		
		if (dbImage == null)
		{
			dbImage = createImage (this.getSize().width, this.getSize().height);
			dbg = dbImage.getGraphics ();
		}

		
		dbg.setColor (getBackground ());
		dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);

		
		dbg.setColor (getForeground());
		paint (dbg);

		
		g.drawImage (dbImage, 0, 0, this);
	}
}


