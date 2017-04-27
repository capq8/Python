import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball
{
    /*Properties of the basic ball. These are initialized in the constructor using the values read from the config.xml file*/
	protected  int pos_x;			
	protected int pos_y; 				
	protected int radius;
	protected int first_x;			
	protected int first_y;					
	protected int x_speed;			
	protected int y_speed;			
	protected int maxspeed;
	public int ptolife = 0;
	public int timehit = 0;
	public String type;
	
	Color color;
	
    GameWindow gameW;
	Player player;
	
	/*constructor*/
	public Ball (int radius, int initXpos, int initYpos, int speedX, int speedY, int maxBallSpeed, Color color, Player player,  GameWindow gameW)
	{	
		this.radius = radius;

		pos_x = initXpos;
		pos_y = initYpos;

		first_x = initXpos;
		first_y = initYpos;

		x_speed = speedX;
		y_speed = speedY;

		maxspeed = maxBallSpeed;

		this.color = color;

		this.player = player;
		this.gameW = gameW;

	}

	/*update ball's location based on it's speed*/
	public void move ()
	{
		pos_x += x_speed;
		pos_y += y_speed;
		isOut();
	}

	/*when the ball is hit, reset the ball location to its initial starting location*/
	public void ballWasHit ()
	{	
		timehit +=1;
		
		Random random=new Random();
		Random random2=new Random();
		resetBallPosition();
		int mran = (random.nextInt(4));


		
		if (mran == 0 )
		{
			x_speed = 1;
			y_speed = 0;
			
		}
		else if(mran == 1)
		{
			x_speed = -1;
			y_speed = 0;
		}
		else if(mran == 2)
		{
			x_speed = 0;
			y_speed = 1;
		}
		else
		{
			x_speed = 0;
			y_speed = -1;
		}
		
		
/*	if (mran % 2 == 1 )
		{
			x_speed = mran;
			y_speed = -1;
		}
		else
		{
			x_speed = 1;
			y_speed = mran;
		}*/
		

	}

	/*check whether the player hit the ball. If so, update the player score based on the current ball speed. */	
	public boolean userHit (int maus_x, int maus_y)
	{
		
		double x = maus_x - pos_x;
		double y = maus_y - pos_y;

		double distance = Math.sqrt ((x*x) + (y*y));
		
		if (Double.compare(distance-this.radius , player.scoreConstant + Math.abs(x_speed)) <= 0)  {
			player.addScore ((int)(player.scoreConstant * Math.abs(x_speed) + player.scoreConstant));
			
			ptolife += (int)(player.scoreConstant * Math.abs(x_speed) + player.scoreConstant);
			if(ptolife >= player.score2EarnLife)
			{
				player.lives += (int) (ptolife / player.score2EarnLife);
				ptolife = ptolife - ( (int) (ptolife / player.score2EarnLife) * player.score2EarnLife);
			}
			
			return true;
		}
		else return false;
	}

    /*reset the ball position to its initial starting location*/
	protected void resetBallPosition()
	{
		
		pos_x = first_x;
		pos_y = first_y;
	}
	
	/*check if the ball is out of the game borders. if so, game is over!*/ 
	protected boolean isOut ()
	{
		if ((pos_x < gameW.x_leftout) || (pos_x > gameW.x_rightout) || (pos_y < gameW.y_upout) || (pos_y > gameW.y_downout)) {	
			resetBallPosition();	
			
			player.lives --;
			if(player.lives <= 0)
			{
			player.gameIsOver();
			
			}
		
			return true;
		}	
		else return false;
	}

	/*draw ball*/
	public void DrawBall (Graphics g)
	{
		g.setColor (color);
		g.fillOval (pos_x - radius, pos_y - radius, 2 * radius, 2 * radius);
	}

}
