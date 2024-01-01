import java.util.List;
import java.util.Scanner;

public class Responsi1_PPBO_L0122138_UI {
    private Responsi1_PPBO_L0122138_PlayerManager playerManager;
    private Scanner scanner;

    public Responsi1_PPBO_L0122138_UI(Responsi1_PPBO_L0122138_PlayerManager playerManager) {
        this.playerManager = playerManager;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {

        System.out.println("======= Game Menu =======");
        System.out.println("| 1. | Daftar           |");
        System.out.println("| 2. | Login            |");
        System.out.println("| 3. | Tampilkan Skor   |");
        System.out.println("| 4. | Keluar           |");
        System.out.println("=========================");
    }

    public int getUserChoice() {
        int choice = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Pilih menu (1-4) >> ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                validInput = choice >= 1 && choice <= 4;
            } else {
                System.out.println("Input tidak valid. Harap masukkan angka antara 1-4.");
                scanner.next();
            }
        }

        return choice;
    }

    public void clearBuffer() {
        scanner.nextLine(); // Membersihkan newline character dari buffer
    }

    public Responsi1_PPBO_L0122138_Player registerPlayer() {
        System.out.print("Masukkan nama: ");
        String name = scanner.nextLine();
        Responsi1_PPBO_L0122138_Player registeredPlayer = playerManager.registerPlayer(name);
        if (registeredPlayer != null) {
            System.out.println("Pendaftaran berhasil. Selamat datang, " + name + "!");
            System.out.println("\n$ Enter untuk bermain");
            scanner.nextLine();
        } else {
            System.out.println("Pendaftaran gagal. Nama sudah digunakan. Silakan coba lagi.");
        }
        return registeredPlayer; // Mengembalikan objek Player yang baru didaftarkan
    }

    public Responsi1_PPBO_L0122138_Player loginPlayer() {
        System.out.print("Masukkan nama: ");
        String name = scanner.nextLine();
        Responsi1_PPBO_L0122138_Player player = playerManager.loginPlayer(name);
        if (player != null) {
            System.out.println("Login berhasil. Selamat datang, " + name + "!");
            // enter untuk melanjutkan
            System.out.println("\n$ Enter untuk bermain");
            scanner.nextLine();
            return player;
        } else {
            System.out.println("Login gagal. Nama tidak ditemukan. Silakan daftar atau coba lagi.");
            return null;
        }
    }

    public void displayScores() {
        System.out.println("======== Skor Pemain ========");
        for (Responsi1_PPBO_L0122138_Player player : playerManager.getPlayers()) {
            System.out.printf("| %-18s | %4d |\n", player.getName(), player.getScore());
        }
        System.out.println("=============================\n");
    }

    public void displayAnswers(List<String> selectedWords) {
        System.out.println("Jawaban yang benar:");
        for (String word : selectedWords) {
            System.out.printf("- %s\n", word);
        }
        System.out.print("\n");
    }

    public void displayProgress(int correctAnswers, int totalWords) {
        System.out.println("\nProgres: " + correctAnswers + " dari " + totalWords + " kata telah ditemukan.");
    }

    public Responsi1_PPBO_L0122138_PlayerManager getPlayerManager() {
        return playerManager;
    }

}