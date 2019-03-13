package Kata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class YatzyScoreTest {

    @Test
    public void testDiceValueError() {
        try {
            YatzyScore yatzyScore = new YatzyScore(10,3,4,5,1);
        } catch (Exception e) {
            assertEquals("dice values must be between 1 inclusive and 6 inclusive", e.getMessage());
        }
    }

    @Test
    public void testChance() throws Exception {
        YatzyScore yatzyScore = new YatzyScore(2,3,4,5,1);
        assertEquals(15, yatzyScore.scores(YatzyScore.Category.CHANCE));
    }


    @Test public void testYatzy50() throws Exception {
        YatzyScore yatzyScore = new YatzyScore(4,4,4,4,4);
        assertEquals(50, yatzyScore.scores(YatzyScore.Category.YATZY));
    }

    @Test public void testYatzy0() throws Exception {
        YatzyScore yatzyScore = new YatzyScore(1, 4, 4, 4, 4);
        assertEquals(0, yatzyScore.scores(YatzyScore.Category.YATZY));
    }


    @Test public void testOnes() throws Exception {
        YatzyScore yatzyScore = new YatzyScore(1, 2, 1, 4, 3);
        assertEquals(2, yatzyScore.scores(YatzyScore.Category.ONES));
    }

    @Test
    public void testTwos() throws Exception {
        YatzyScore yatzyScore = new YatzyScore(1, 2, 2, 4, 2);
        assertEquals(6, yatzyScore.scores(YatzyScore.Category.TWOS));
    }

    @Test
    public void testThrees() throws Exception {
        YatzyScore yatzyScore = new YatzyScore(3, 2, 3, 3, 2);
        assertEquals(9, yatzyScore.scores(YatzyScore.Category.THREES));
    }

    @Test
    public void testFours() throws Exception {
        YatzyScore yatzyScore = new YatzyScore(1, 2, 2, 4, 2);
        assertEquals(4, yatzyScore.scores(YatzyScore.Category.FOURS));
    }

    @Test
    public void testFives() throws Exception {
        YatzyScore yatzyScore = new YatzyScore(1, 2, 3, 4, 5);
        assertEquals(5, yatzyScore.scores(YatzyScore.Category.FIVES));
    }

    @Test
    public void testSixes() throws Exception {
        YatzyScore yatzyScore = new YatzyScore(1, 2, 6, 6, 6);
        assertEquals(18, yatzyScore.scores(YatzyScore.Category.SIXES));
    }

    @Test
    public void testPair() throws Exception {
        YatzyScore yatzyScore = new YatzyScore(3,4,3,5,6);
        assertEquals(6, yatzyScore.scores(YatzyScore.Category.PAIR));
    }

    @Test
    public void testTwoPairs() throws Exception {
        YatzyScore yatzyScore = new YatzyScore(3,4,3,4,6);
        assertEquals(14, yatzyScore.scores(YatzyScore.Category.TWO_PAIRS));
    }

    @Test
    public void testThreeOfAKind() throws Exception {
        YatzyScore yatzyScore = new YatzyScore(3,3,3,4,5);
        assertEquals(9, yatzyScore.scores(YatzyScore.Category.THREE_OF_A_KIND));
    }

    @Test
    public void testFourOfAKind() throws Exception {
        YatzyScore yatzyScore = new YatzyScore(3,3,3,3,5);
        assertEquals(12, yatzyScore.scores(YatzyScore.Category.FOUR_OF_A_KIND));
    }

    @Test
    public void testSmallStraight() throws Exception {
        YatzyScore yatzyScore = new YatzyScore(1,2,3,4,5);
        assertEquals(15, yatzyScore.scores(YatzyScore.Category.SMALL_STRAIGHT));
    }

    @Test
    public void testLargeStraight() throws Exception {
        YatzyScore yatzyScore = new YatzyScore(6,2,3,4,5);
        assertEquals(20, yatzyScore.scores(YatzyScore.Category.LARGE_STRAIGHT));
    }

    @Test
    public void testFullHouse() throws Exception {
        YatzyScore yatzyScore = new YatzyScore(6,2,2,2,6);
        assertEquals(18, yatzyScore.scores(YatzyScore.Category.FULL_HOUSE));
    }

}

