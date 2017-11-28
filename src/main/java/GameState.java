import java.util.ArrayList;
import java.util.List;
//This class contains the entire state of the bowling game.

 public class GameState {


    public List<Frame> gameFrames;
    private int frameIndex  = -1;//keeping track of the current frame
    private static final int MAX_FRAMES = 10;
    protected GameState()
    {
        this.gameFrames = new ArrayList<Frame>();
    }




     protected void roll(int num)
     {
         // rolls do not matter. game is over;
         if(isFinished())
             return;

         //create new frame if the frame is complete or if new game
         if(getCurrentFrame() == null || getCurrentFrame().frameComlete)
         {
             boolean lastFrame = false;
             if(++frameIndex == MAX_FRAMES  - 1)
                 lastFrame = true;
             this.gameFrames.add(new Frame(lastFrame));

         }
         this.getCurrentFrame().attempt(new Roll(num));

     }
     //just check if we are at the last index and the frame is complete
     protected boolean isFinished()
     {
         if(frameIndex == MAX_FRAMES - 1 && getCurrentFrame().frameComlete )
             return true;

         return false;
     }


     /*The frame objects know about the score minus the bonus.
     This method applies the logic for bonus points
     However, the last frame logic regarding extra rolls, belongs to
     the frame.
     */
     protected int getScore()
    {
        //take care of some special cases
        int score = 0;
        if(gameFrames.size() == 0)
            return 0;
        if(gameFrames.size() == 1)
            return getCurrentFrame().totalScore;

        /*
         For each frame, we will call a method called framescore
         This includes the base score which is sum of total pins
         knocked , plus the bonus logic from carryover strikes
         and spares
        * */
        for(Frame f : gameFrames)
        {
            score += frameScore(f);
        }

        return score;

    }
//********************scoring helpers**************************
    private int frameScore(Frame f)
    {
        //f.totalScore is just the sum of the two roles
        int total = f.totalScore + bonus(f);
        return total;
    }

    /*This takes care of three cases
        1. When the frame is a spare -
        2.When the frame is a strike
        3. When there are conseq strikes
     */
    private int bonus(Frame f)
    {
        int total = 0;
        //only add the first roll of the next turn
        //if a spare, just add the next frames first roll
        if(f.spare)
        {
            Frame next = this.nextFrame(f);
            if(next != null)
                total = next.try1.getNumPins();
        }
        //has it stands, i seperated the strike case into two parts
        // this may have a more elegant approach
        if(f.strike)
        {

            Frame next = this.nextFrame(f);
            if(next != null)
            {
                //special complicated case where the next one is a strike too
                if(next.strike)
                {

                    Frame nextNext = this.nextFrame(next);
                    //conseq strike case.
                    if(nextNext != null && !nextNext.lastFrame)
                    {
                        total  = next.try1.getNumPins() + nextNext.try1.getNumPins();
                    }
                    //needed a special case to handle the summing of the strike chaining with the last frame
                    //if the next next is the last frame, just get the sum total of that entire frame
                    if(nextNext != null && nextNext.lastFrame)
                    {
                        total  = next.try1.getNumPins() + nextNext.totalScore;
                    }

                }
                else
                    total = next.totalScore;
            }
        }
        return total;
    }

//helper to calc bonus
    private Frame nextFrame(Frame f)
    {
        int pos = this.gameFrames.indexOf(f);
        if(pos + 1 > this.gameFrames.size() -1)
            return null;

        return gameFrames.get(pos + 1);
    }




    private Frame getCurrentFrame()
    {
        if(frameIndex == -1)
            return null;
        return gameFrames.get(frameIndex);
    }










}
