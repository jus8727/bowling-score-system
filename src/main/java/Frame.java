// This class contains all the logic to score a frame but does not consider bonus from
//previous. That is handled in the gameState score method.
//
public class Frame {
    public boolean frameComlete = false;
    public boolean strike = false;
    public boolean spare = false;
    public boolean lastFrame;
    public Roll try1;
    public Roll try2;
    public Roll bonus;
    public int totalScore;
    //keeping track of how many times we went in this frame
    private boolean try1Complete = false;
    private boolean try2Complete = false;
    //make sure we keep track of the lastFrame and possible bonus
    private boolean bonusAward = false;

    //The frame should know if it is the last frame as the last frame has special rules
    public Frame(boolean lastFrame) {
        this.lastFrame = lastFrame;
    }

    // all validation of the roll numbers will be handled upstream
    public void attempt(Roll r) {
        //if we went once, got a strike and its not the last frame, then this frame is complete
        if (try1Complete && strike && !lastFrame)
            frameComlete = true;

        //just return if frame is done already
        if (frameComlete)
            return;

        //this will be hit during the first roll in this frame
        if (!try1Complete) {
            try1 = r;
            try1Complete = true;
            if (try1.getNumPins() == 10) {
                strike = true;
                if (!lastFrame) {
                    //dont mark as complete yet.
                    frameComlete = true;
                } else // got a strike on the last round
                {
                    bonusAward = true;
                }
            }
            totalScore += try1.getNumPins();
        }
        //second turn. make sure record a spare if total is 10
        else if (!try2Complete) {
            // here will we mark as complete and also do scoring and checking.
            try2 = r;
            try2Complete = true;
            totalScore += try2.getNumPins();
            if (totalScore == 10) {
                spare = true;
            }
            if (spare && lastFrame) {
                bonusAward = true;
            }
            //if last frame and no bonus or not lastframe then second roll is the last
            if (!lastFrame || (lastFrame && !bonusAward))
                frameComlete = true;

            //protecting against something like Turn1 = 9, Turn2 = 2
            //but this will not work on the bonus frame
            if (!bonusAward && try1.getNumPins() + try2.getNumPins() > 10)
                throw new IllegalArgumentException("Frame score cannot be more than 10 if not last frame not counted bonus");

        }
        //this will only be hit in the final frame, if nessary
        else if (bonusAward) {
            bonus = r;
            totalScore += bonus.getNumPins();
            frameComlete = true;
        }


    }


}
