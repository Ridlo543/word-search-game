import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Responsi1_PPBO_L0122138_WordSearchGame {
    private char[][] board;
    private List<String> selectedWords;
    private Random random;
    private static final char EMPTY_CELL = '\0';
    private static final int ALPHABET_SIZE = 26;
    private static int totalCells;
    private static int totalWords;
    private int correctAnswers;
    private Responsi1_PPBO_L0122138_PlayerManager playerManager;

    public Responsi1_PPBO_L0122138_WordSearchGame(int numRows, int numCols) {
        assert numRows > 0 && numCols > 0 : "Ukuran papan harus lebih dari 0";
        board = new char[numRows][numCols];
        random = new Random();
        initializeBoard();
        selectedWords = new ArrayList<>();
        correctAnswers = 0; // Inisialisasi list jawaban yang benar
        totalCells = numRows * numCols; // Inisialisasi totalCells
        totalWords = 5;
        this.playerManager = new Responsi1_PPBO_L0122138_PlayerManager();
    }

    private void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
    }

    public void loadWordsFromFile(String filePath, int numWords) {
        Responsi1_PPBO_L0122138_WordLoader wordLoader = new Responsi1_PPBO_L0122138_WordLoader();
        selectedWords = wordLoader.loadWords(filePath);

        Collections.shuffle(selectedWords);
        selectedWords = selectedWords.subList(0, Math.min(numWords, selectedWords.size()));

        totalWords = selectedWords.size();
    }

    public void placeWordsOnBoard() {
        Responsi1_PPBO_L0122138_WordPlacer wordPlacer = new Responsi1_PPBO_L0122138_WordPlacer(board, selectedWords,
                random, ALPHABET_SIZE);
        wordPlacer.placeWords();
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        Responsi1_PPBO_L0122138_UI ui = new Responsi1_PPBO_L0122138_UI(playerManager);
        int choice;

        System.out.println("\nSelamat datang di Word Search Game!");
        

        Responsi1_PPBO_L0122138_Player currentPlayer = null;
        do {
            ui.displayMenu();
            choice = ui.getUserChoice();
            ui.clearBuffer();

            switch (choice) {
                case 1:
                    currentPlayer = ui.registerPlayer();
                    break;
                case 2:
                    currentPlayer = ui.loginPlayer();
                    break;
                case 3:
                    ui.displayScores();
                    break;
                case 4:
                    System.out.println("Terima kasih! Sampai jumpa lagi.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }

            if (choice == 4) {
                break;
            }

            if (currentPlayer != null && choice != 3) {

                System.out.println("\nCarilah kata yang tepat disusun secara VERTICAL atau HORIZONTAL!");
                System.out.println("Ketik 'lihat' untuk melihat jawaban\n");
                
                correctAnswers = 0;
                initializeBoard();
                loadWordsFromFile("jawaban.txt", Math.min(totalWords, totalCells));
                placeWordsOnBoard();

                while (!selectedWords.isEmpty()) {
                    printBoard();
                    ui.displayProgress(correctAnswers, totalWords);
                    System.out.print("Masukkan jawaban (atau ketik 'lihat' untuk melihat jawaban): ");
                    String guess = scanner.next().toUpperCase();

                    if ("LIHAT".equals(guess)) {
                        ui.displayAnswers(selectedWords);
                    } else if (selectedWords.contains(guess)) {
                        selectedWords.remove(guess);
                        correctAnswers++;
                        System.out.println("Selamat, Anda menemukan kata \"" + guess + "\"!");

                        int score = totalCells;
                        currentPlayer.addToScore(score);

                        if (selectedWords.isEmpty()) {
                            System.out.println("Selamat, Anda telah menemukan semua kata!");

                            System.out.println("Skor Anda: " + currentPlayer.getScore());

                            playerManager.savePlayersAsText();

                            System.out.print("Apakah Anda ingin bermain lagi? (y/n): ");
                            String playAgainChoice = scanner.next().toLowerCase();
                            if (!playAgainChoice.equals("y")) {
                                break; // Keluar dari permainan
                            }

                            // Reset papan dan kata yang terpilih
                            correctAnswers = 0;
                            initializeBoard();
                            loadWordsFromFile("jawaban.txt", Math.min(totalWords, totalCells));
                            placeWordsOnBoard();

                        }
                    } else {
                        System.out.println("Jawaban \"" + guess + "\" tidak benar. Coba lagi.");
                    }

                    playerManager.savePlayersAsText();

                }
            }
        } while (choice != 4);

        playerManager.savePlayersAsText();
        scanner.close();
    }

}