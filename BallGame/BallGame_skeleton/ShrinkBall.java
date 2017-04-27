import java.awt.Color;
import java.awt.Graphics;

public class ShrinkBall extends Ball{

	public int orad;
	double bonus = player.scoreConstant;
	public double shrinkrate;
	public ShrinkBall(int radius, int initXpos, int initYpos, int speedX, int speedY, int maxBallSpeed, Color color,
			Player player, GameWindow gameW) {
		super(radius, initXpos, initYpos, speedX, speedY, maxBallSpeed, color, player, gameW);
		
	orad = radius;
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
		int temp = orad;
		if((temp*shrinkrate) > radius)
		{
			radius = orad;
			bonus = player.scoreConstant;
		}
		else
		{
		radius = (int) ((radius) - (radius * 0.3));
		}
		super.ballWasHit();
	}

	@Override
	public boolean userHit(int maus_x, int maus_y) {
		bonus = bonus * 2;
		double x = maus_x - pos_x;
		double y = maus_y - pos_y;

		double distance = Math.sqrt ((x*x) + (y*y));
		
		if (Double.compare(distance-this.radius , player.scoreConstant + Math.abs(x_speed)) <= 0)  {
			player.addScore ((int)(player.scoreConstant * Math.abs(x_speed) + bonus));
			
			ptolife += (int)(player.scoreConstant * Math.abs(x_speed) + player.scoreConstant);
			if(ptolife >= player.score2EarnLife)
			{
				player.lives +=1;
				ptolife = ptolife - player.score2EarnLife;
			}
			
			return true;
		}
		else return false;
	}

	@Override
	protected void resetBallPosition() {
		// TODO Auto-generated method stub
		super.resetBallPosition();
	}

	@Override
	protected boolean isOut() {
		// TODO Auto-generated method stub
		return super.isOut();
	}

	@Override
	public void DrawBall(Graphics g) {
		// TODO Auto-generated method stub
		super.DrawBall(g);
	}

}
