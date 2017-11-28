import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BowlingTest {

    private BowlingGame bowling;

    @Test
    public void testStrikeLogic3Frames() {
        BowlingGame game = new BowlingGame();
        game.roll(10);
        game.roll(3);
        game.roll(6);

        assertEquals(game.getScore(), 28);
        assertEquals(game.isFinished(), false);
    }

    @Test
    public void testDoubleStrikeLogic4Frames() {

        BowlingGame game = new BowlingGame();
        game.roll(10);
        game.roll(10);
        game.roll(9);
        game.roll(0);
        assertEquals(game.isFinished(), false);
        assertEquals(game.getScore(), 57);
    }

    @Test
    public void test5ConsecStrikes6Frames() {

        BowlingGame game = new BowlingGame();
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(7);
        game.roll(2);

        assertEquals(game.getScore(), 145);
    }

    @Test
    public void test300Score() {

        BowlingGame game = new BowlingGame();
        for (int i = 0; i < 12; i++)
            game.roll(10);

        assertEquals(game.getScore(), 300);
    }


    @Test
    public void testEmptyScore() {

        BowlingGame game = new BowlingGame();
        for (int i = 0; i < 0; i++)
            game.roll(10);

        assertEquals(game.getScore(), 0);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInputRollGreaterThan10() {

        BowlingGame game = new BowlingGame();
        for (int i = 0; i < 1; i++)
            game.roll(100);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInputFrameTotalGreaterThan10() {

        BowlingGame game = new BowlingGame();

        game.roll(9);
        game.roll(2);

    }

    @Test
    public void testCallToScoreInitial() {

        BowlingGame game = new BowlingGame();
        assertEquals(game.getScore(), 0);

    }

    @Test
    public void testAllZeros() {

        BowlingGame game = new BowlingGame();
        for (int i = 0; i < 20; i++)
            game.roll(0);

        assertEquals(game.getScore(), 0);
        assertEquals(game.isFinished(), true);
    }

    @Test
    public void testAllFours() {

        BowlingGame game = new BowlingGame();
        for (int i = 0; i < 20; i++)
            game.roll(4);

        assertEquals(game.getScore(), 80);
        assertEquals(game.isFinished(), true);
    }

    @Test
    public void testWith8EmptyFramesAtEnd() {

        BowlingGame game = new BowlingGame();
        game.roll(9);
        game.roll(1);
        game.roll(9);
        for (int i = 0; i < 17; i++)
            game.roll(0);

        assertEquals(game.getScore(), 28);
        assertEquals(game.isFinished(), true);
    }

    @Test
    public void testWithBonusStrikeFrame10() {

        BowlingGame game = new BowlingGame();

        for (int i = 0; i < 18; i++)
            game.roll(0);


        game.roll(10);
        game.roll(9);
        game.roll(1);
        assertEquals(game.getScore(), 20);
        assertEquals(game.isFinished(), true);
    }

    @Test
    public void testWithBonusSpareFrame10() {

        BowlingGame game = new BowlingGame();

        for (int i = 0; i < 18; i++)
            game.roll(0);


        game.roll(9);
        game.roll(1);
        game.roll(9);
        assertEquals(game.getScore(), 19);
        assertEquals(game.isFinished(), true);
    }

    @Test
    public void testWith3Strikes() {

        BowlingGame game = new BowlingGame();
        game.roll(10);
        game.roll(10);
        game.roll(10);
        for (int i = 0; i < 14; i++)
            game.roll(0);


        assertEquals(game.getScore(), 60);
        assertEquals(game.isFinished(), true);
    }

    //expecting this to ignore the invalid turn
    @Test
    public void testWith3StrikesWithInvalidRoll() {

        BowlingGame game = new BowlingGame();
        try {
            game.roll(10);
            game.roll(10);
            game.roll(10);
            for (int i = 0; i < 13; i++)
                game.roll(0);

            game.roll(14);
        } catch (IllegalArgumentException ex) {

        }
        game.roll(0);
        assertEquals(game.getScore(), 60);
        assertEquals(game.isFinished(), true);
    }
}