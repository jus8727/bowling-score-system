public class BowlingGame {

    private GameState state;

    public BowlingGame()
    {
        this.state = new GameState();

    }
//include some basic validation on the roll amount.
    public void roll(int num)
    {
        if(num < 0 || num > 10)
        {
            throw new IllegalArgumentException("Any given turn can only result in knocking down n pins, 0 <= n <= 10");
        }
        state.roll(num);

    }
    public boolean isFinished()
    {
        return state.isFinished();
    }

    public int getScore()
    {
        return state.getScore();
    }
}
