/**
 * Created by DBR2MCQ on 11/27/2017.
 */
public class BowlingGameRunner {

    public static void main(String[] args){

        BowlingGame game = new BowlingGame();
        System.out.println(game.getScore());
        System.out.println(game.isFinished());
        for (int i = 0; i < 12; i++) {
            game.roll(10);
        } System.out.println(game.getScore());
        System.out.println(game.isFinished());

    }
}
