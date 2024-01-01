import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Responsi1_PPBO_L0122138_WordPlacer {
    private char[][] board;
    private List<String> selectedWords;
    private Random random;
    private static final char EMPTY_CELL = '\0';
    private static final int ALPHABET_SIZE = 26;

    public Responsi1_PPBO_L0122138_WordPlacer(char[][] board, List<String> selectedWords, Random random, int alphabetSize) {
        this.board = board;
        this.selectedWords = selectedWords;
        this.random = random;
    }

    public void placeWords() {
        Collections.shuffle(selectedWords);
        for (String word : selectedWords) {
            placeWord(word);
        }

        // Mengisi cell yang kosong dengan huruf acak
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == EMPTY_CELL || board[i][j] == '\0') {
                    // Place initial here
                    board[i][j] = (char) ('A' + random.nextInt(ALPHABET_SIZE));
                }
            }
        }
    }

    private void placeWord(String word) {
        int direction = random.nextInt(2); // 0 for horizontal, 1 for vertical

        if (direction == 0 && word.length() <= board.length) {
            // Horizontal placement
            int row = random.nextInt(board.length);
            int col = random.nextInt(board[0].length - word.length() + 1);

            while (checkCollisionHorizontal(row, col, word.length())) {
                // Keep trying new locations until no collision
                row = random.nextInt(board.length);
                col = random.nextInt(board[0].length - word.length() + 1);
            }

            // Place the word horizontally
            for (int i = 0; i < word.length(); i++) {
                board[row][col + i] = word.charAt(i);
            }
        } else if (direction == 1 && word.length() <= board.length) {
            // Vertical placement
            int row = random.nextInt(board.length - word.length() + 1);
            int col = random.nextInt(board[0].length);

            while (checkCollisionVertical(row, col, word.length())) {
                // Keep trying new locations until no collision
                row = random.nextInt(board.length - word.length() + 1);
                col = random.nextInt(board[0].length);
            }

            // Place the word vertically
            for (int i = 0; i < word.length(); i++) {
                board[row + i][col] = word.charAt(i);
            }
        } else {
            // Handle the case where the word cannot fit on the board
            System.out.println("Skipped word placement: " + word + " (direction: "
                    + (direction == 0 ? "horizontal" : "vertical") + ")");
        }
    }

    private boolean checkCollisionHorizontal(int row, int col, int wordLength) {
        for (int i = 0; i < wordLength; i++) {
            if (board[row][col + i] != '\0') {
                // Collision, overlapping character found
                return true;
            }
        }
        return false;
    }

    private boolean checkCollisionVertical(int row, int col, int wordLength) {
        for (int i = 0; i < wordLength; i++) {
            if (board[row + i][col] != '\0') {
                // Collision, overlapping character found
                return true;
            }
        }
        return false;
    }
}
