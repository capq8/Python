import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class BounceBall extends Ball {
	
	public int BallCount;
	public int obc = BallCount;
	
	

	public BounceBall(int radius, int initXpos, int initYpos, int speedX, int speedY, int maxBallSpeed, Color color,
			Player player, GameWindow gameW) {
		super(radius, initXpos, initYpos, speedX, speedY, maxBallSpeed, color, player, gameW);
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	@Override
	public void move() {
		// TODO Auto-generated method stub
		super.move();
	}

	@Override
	public void ballWasHit() {
		// TODO Auto-generated method stub
		super.ballWasHit();
	}

	@Override
	public boolean userHit(int maus_x, int maus_y) {
		// TODO Auto-generated method stub
		return super.userHit(maus_x, maus_y);
	}

	@Override
	protected void resetBallPosition() {
		
		// TODO Auto-generated method stub
		super.resetBallPosition();
	}

	@Override
	protected boolean isOut() {
		if(obc < BallCount)
		{
		obc = BallCount;
		}
		
		if ((pos_x < gameW.x_leftout) || (pos_x > gameW.x_rightout) || (pos_y < gameW.y_upout) || (pos_y > gameW.y_downout)) {	
			
			System.out.println("ballcount: " + BallCount);
			if(BallCount > 0)
			{
			
				if((pos_x < gameW.x_leftout) || (pos_x > gameW.x_rightout))
					{
						x_speed *= -1;
						BallCount --;
					}
				else
					{
						y_speed *= -1;
						BallCount --;
					}
			}
			else
			{
				
				resetBallPosition();
				System.out.print("bounce ball got reset");
				BallCount = obc;
				player.lives --;
				if(player.lives <= 0)
				{
				player.gameIsOver();
				
				
				}
				
			}
	


		
			return false;
		}	
		else return false;
	}

	@Override
	public void DrawBall(Graphics g) {
		// TODO Auto-generated method stub
		super.DrawBall(g);
	}

	
}
