import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] score_A = new int[]{ 1, -3, 2, 10, 2 };
        int[] score_B = new int[]{ 16, 2, -5, 2, 51};
        int[] score_C = new int[]{61, 62, 40, 30, 50 };

        KnapSack knapSack = new KnapSack();
        System.out.println("Max Score: " + knapSack.maxScore(score_A, score_B, score_C));
    }
}

class KnapSack {
    // Memoization table: mem[index][lastRoutine][streak]
    // - 'index' is the current index (n in the explanation)
    // - 'lastRoutine' is the routine chosen the previous day (0=A, 1=B, 2=C, 3=none at start)
    // - 'streak' is how many times the same routine has been done in a row (T[routine] in the explanation)
    public int[][][] mem;

    // Combined 2D array to store scores of A, B, and C for easier access
    public int[][] scores;

    // Total number of days (length of input arrays)
    public int totalExercise;


    /**
     * Initializes variables and starts the recursive knapsack search:
     *      to find the max no. of points obtainable, given the constraint
     *          that he can only do at most 2 routine in a row & exactly 1 in a day
     * @param A Push routine
     * @param B Pull routine
     * @param C Leg routine
     * @return return the max no. of points whether by starting from A, B
     *          or C returns the max no. of points
     */
    public int maxScore(int[] A, int[] B, int[] C) {
        this.totalExercise = A.length;
        // scores[0] = A, scores[1] = B, scores[2] = C
        // Merge into 2D array for easier indexing
        this.scores = new int[][]{A, B, C};
        this.mem = new int[totalExercise][4][3]; // [index][lastRoutine][streak](3 routines + 1 for 'none', max streak = 2)

        // Fill memo table with uncomputed values
        for (int[][] layer : mem)
            for (int[] row : layer)
                Arrays.fill(row, Integer.MIN_VALUE);

        // Initialise T[] with 0s (no consecutive streaks yet)
        return KnapSack(0, new int[]{0, 0, 0});
    }

    /**
     * To find the max no. of points obtainable,
     * and since the constraint that he can only do at most 2 routines in a row &
     * exactly 1 in a day,
     * KnapSack(n, T[]) can be used where n is the total no. of points &
     *  T[] represent how many times he has done a particular routine in a row
     */
    private int KnapSack(int n, int[] T) {

        // No more days left
        if (n == totalExercise){
            return 0;
        }

        // Determine which routine was done last and how many times
        int lastRoutine = 3; // 3 = no routine done yet
        int streak = 0;
        for (int r = 0; r < 3; r++) {
            if (T[r] > 0) {
                lastRoutine = r;
                streak = T[r];
                break;
            }
        }

        // return already computed value
        if (mem[n][lastRoutine][streak] != Integer.MIN_VALUE) {
            return mem[n][lastRoutine][streak];
        }

        int maxPoints = Integer.MIN_VALUE;

        // Try each routine A(0), B(1), C(2)
        for (int r = 0; r < 3; r++) {
            // cannot do the same exercise more than 2 times
            if (T[r] == 2) {
                continue;
            }

            // Create a new array to represent T for the next recursion
            int[] nextT = new int[3];
            nextT[r] = T[r] + 1; // increment streak for current routine

            // If same routine is chosen again, increment streak
            // Else (different routine chosen), reset streak to 1
            for (int i = 0; i < 3; i++) {
                if (i != r) nextT[i] = 0;
            }

            // Apply the recurrence:
            // knapsack = max(KnapSack(n + SA, T[A] + 1), KnapSack(n + SB, T[B] + 1), KnapSack(n + SC, T[C] + 1))
            int points = scores[r][n] + KnapSack(n + 1, nextT);
            maxPoints = Math.max(maxPoints, points);
        }

        // Memoize the result for the current state & find max
        mem[n][lastRoutine][streak] = maxPoints;
        return maxPoints;
    }
}