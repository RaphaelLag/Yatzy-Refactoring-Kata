package Kata;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * score a GIVEN roll in a GIVEN category.
 */
public class YatzyScore {

    public enum Category {
        CHANCE,
        YATZY,
        ONES,
        TWOS,
        THREES,
        FOURS,
        FIVES,
        SIXES,
        PAIR,
        TWO_PAIRS,
        THREE_OF_A_KIND,
        FOUR_OF_A_KIND,
        SMALL_STRAIGHT,
        LARGE_STRAIGHT,
        FULL_HOUSE
    }

    private final int[] counts; //the value at index i indicates the number of values i of dice in the roll

    private final static int DICE_NUMBER = 5;

    public YatzyScore(int d1, int d2, int d3, int d4, int d5) throws Exception {
        if (!checkDice(d1) ||
                !checkDice(d2) ||
                !checkDice(d3) ||
                !checkDice(d4) ||
                !checkDice(d5)
        ) {
            throw new Exception("dice values must be between 1 inclusive and 6 inclusive");
        }

        counts = new int[6];
        counts[d1 - 1]++;
        counts[d2 - 1]++;
        counts[d3 - 1]++;
        counts[d4 - 1]++;
        counts[d5 - 1]++;
    }


    /**
     *
     score a GIVEN roll in a GIVEN category.
     * @param category
     * @return
     */
    public int scores(Category category) {
        int score = 0;
        switch (category) {
            case CHANCE:
                score = sumOfAllDice();
                break;
            case YATZY:
                score = yatzy();
                break;
            case ONES:
                score = sumOf(1);
                break;
            case TWOS:
                score = sumOf(2);
                break;
            case THREES:
                score = sumOf(3);
                break;
            case FOURS:
                score = sumOf(4);
                break;
            case FIVES:
                score = sumOf(5);
                break;
            case SIXES:
                score = sumOf(6);
                break;
            case PAIR:
                score = pair();
                break;
            case TWO_PAIRS:
                score = twoPairs();
                break;
            case THREE_OF_A_KIND:
                score = ofAKind(3);
                break;
            case FOUR_OF_A_KIND:
                score = ofAKind(4);
                break;
            case SMALL_STRAIGHT:
                score = straightFrom(1);
                break;
            case LARGE_STRAIGHT:
                score = straightFrom(2);
                break;
            case FULL_HOUSE:
                score = fullHouse();
                break;
        }
        return score;
    }

    /**
     * The player scores the sum of all dice
     * @return
     */
    private int sumOfAllDice()
    {
        return IntStream.range(0, 6)
                .map(i -> (i + 1) * counts[i])
                .sum();
    }

    /**
     * If all dice have the same number, the player scores 50 points.
     * Otherwise the player scores 0 points.
     * @return
     */
    private int yatzy()
    {
        return Arrays.stream(counts)
                .anyMatch(count -> count == DICE_NUMBER) ? 50 : 0;
    }

    /**
     * The player scores the sum of the dice that reads the value of the parameter
     * diceValue.
     * @return
     */
    private int sumOf(int diceValue) {
        return diceValue * counts[diceValue - 1];
    }

    /**
     * The player scores the sum of the two highest matching dice.
     * If there is none, he scores 0.
     * @return
     */
    private int pair()
    {
        return IntStream.range(0, 6)
                .map(i -> 5 - i)
                .filter(i -> counts[i] >= 2)
                .boxed()
                .findFirst()
                .map(i -> 2 * (i + 1)).orElse(0);
    }

    /**
     * If there are two pairs of dice with the same number, the player scores the sum of these dice,
     * otherwise he scores 0.
     * @return
     */
    private int twoPairs()
    {
        int result = 0;

        List<Integer> list = IntStream.range(0, 6)
                .map(i -> 5 - i)
                .filter(i -> counts[i] >= 2)
                .boxed()
                .collect(Collectors.toList());

        if (list.size() == 2) {
            result = list.stream().mapToInt(i -> 2 * (i + 1)).sum();
        }

        return result;
    }

    /**
     * If there are a number of dice with the same number equal to diceValue, the player scores the sum of these dice
     * otherwise he scores 0.
     * @return
     */
    private int ofAKind(int diceValue) {
        int result = 0;
        for (int i = 0; i < 6; i++) {
            if (counts[i] >= diceValue) {
                result = (i + 1) * diceValue;
            }
        }
        return result;
    }


    /**
     * if the dice are straight from the from parameter, the player scores the sum of the dice,
     * otherwise he scores 0.
     * @return
     */
    private int straightFrom(int from) {
        int result = 0;

        if (IntStream.range(from - 1, from + DICE_NUMBER - 1)
                .noneMatch(i -> counts[i] != 1)) {
            result = sumOfAllDice();
        }

        return result;
    }

    /**
     * If the dice are two of a kind and three of a kind, the player scores the sum of all the dice,
     * otherwise he scores 0.
     * @return
     */
    private int fullHouse() {

        int result = 0;

        int twoOfAKind = ofAKind(2);
        int threeOfAKind = ofAKind(3);
        if (twoOfAKind != 0 && threeOfAKind != 0) {
            result = twoOfAKind + threeOfAKind;
        }

        return result;

    }

    /**
     * @param dice
     * @return true if the value of the parameter is between 1 and 6, false otherwise
     */
    private boolean checkDice(int dice) {
        return dice >= 1 && dice <= 6;
    }

}
