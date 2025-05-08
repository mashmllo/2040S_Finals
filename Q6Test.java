import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

public class Q6Test {

    @Test
    public void givenInput() {
        int[] A = new int[]{ 1, -3, 2, 10, 2 };
        int[] B = new int[]{ 16, 2, -5, 2, 51};
        int[] C = new int[]{61, 62, 40, 30, 50 };

        KnapSack knapSack = new KnapSack();
        assertEquals(206, knapSack.maxScore(A, B, C));
    }

    @Test
    public void minInput() {
        int[] A = {5};
        int[] B = {10};
        int[] C = {7};
        KnapSack knapSack = new KnapSack();
        assertEquals(10, knapSack.maxScore(A, B, C));
    }

    @Test
    public void twoDays() {
        int[] A = {1, 2};
        int[] B = {3, 4};
        int[] C = {5, 6};
        KnapSack knapSack = new KnapSack();
        assertEquals(11, knapSack.maxScore(A, B, C));
    }

    @Test
    public void streakConsistentTriggered() {
        int[] A = {5, 6, 7};
        int[] B = {1, 1, 1};
        int[] C = {2, 2, 2};
        KnapSack knapSack = new KnapSack();
        assertEquals(15, knapSack.maxScore(A, B, C));
    }

    @Test
    public void altSequence() {
        int[] A = {1, 100, 1, 100};
        int[] B = {100, 1, 100, 1};
        int[] C = {0, 0, 0, 0};
        KnapSack knapSack = new KnapSack();
        assertEquals(400, knapSack.maxScore(A, B, C));
    }

    @Test
    public void allNegative() {
        int[] A = {-1, -2, -3};
        int[] B = {-4, -5, -6};
        int[] C = {-7, -8, -9};
        KnapSack knapSack = new KnapSack();
        assertEquals(-9, knapSack.maxScore(A, B, C));
    }

    @Test
    public void longPerformance() {
        int n = 1000;
        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[n];
        Arrays.fill(A, 1);
        Arrays.fill(B, 2);
        Arrays.fill(C, 3);
        KnapSack knapSack = new KnapSack();
        assertEquals(2667, knapSack.maxScore(A, B, C));
    }

    @Test
    public void switchOften() {
        int[] A = {10, 1, 10, 1, 10};
        int[] B = {1, 10, 1, 10, 1};
        int[] C = {0, 0, 0, 0, 0};
        KnapSack knapSack = new KnapSack();
        assertEquals(50, knapSack.maxScore(A, B, C));
    }

    @Test
    public void equalScore() {
        int[] A = {5, 5, 5};
        int[] B = {5, 5, 5};
        int[] C = {5, 5, 5};
        KnapSack knapSack = new KnapSack();
        assertEquals(15, knapSack.maxScore(A, B, C));
    }
}
