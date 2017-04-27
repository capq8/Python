public class Player
{
	
	private int score;			   //player score
	private boolean gameover=false;	
	public int scoreConstant = 10; //This constant value is used in score calculation. You don't need to change this. 	
	public int lives;	
	public int score2EarnLife;
	public double t_clicks = 1;
	public double t_clickhit = 0;
	public double t_clicksuccess = 0.0;
	public String hit_type;

	
	public Player()
	{
		score = 0; //initialize the score to 0
	}

	/* get player score*/
	public int getScore ()
	{
		return score;
	}

	/*check if the game is over*/
	public boolean isGameOver ()
	{
		return gameover;
	}

	/*update player score*/
	public void addScore (int plus)
	{
		score += plus;
	}

	/*update "game over" status*/
	public void gameIsOver ()
	{
		gameover = true;
	}
	
	public void addLife (int plus)
	{
		lives = plus;
	}
}