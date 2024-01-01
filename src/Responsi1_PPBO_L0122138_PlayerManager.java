import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Responsi1_PPBO_L0122138_PlayerManager {
    private List<Responsi1_PPBO_L0122138_Player> players;
    private static final String PLAYERS_FILE = "players.txt";

    public Responsi1_PPBO_L0122138_PlayerManager() {
        players = new ArrayList<>();
        if (!fileExists(PLAYERS_FILE)) {
            createFile(PLAYERS_FILE);
        }
        loadPlayersFromText(); 
    }

    void savePlayersAsText() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PLAYERS_FILE))) {
            for (Responsi1_PPBO_L0122138_Player player : players) {
                writer.println(player.toCsvString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    private void loadPlayersFromText() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PLAYERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Responsi1_PPBO_L0122138_Player player = Responsi1_PPBO_L0122138_Player.fromCsvString(line);
                if (player != null) {
                    players.add(player);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    private void createFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("File created: " + filePath);
            } else {
                System.out.println("File already exists: " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Responsi1_PPBO_L0122138_Player registerPlayer(String name) {
        // Cek apakah pemain sudah terdaftar
        for (Responsi1_PPBO_L0122138_Player player : players) {
            if (player.getName().equalsIgnoreCase(name)) {
                System.out.println("Player dengan nama '" + name + "' sudah terdaftar.");
                return null;
            }
        }

        // Daftarkan pemain baru
        Responsi1_PPBO_L0122138_Player newPlayer = new Responsi1_PPBO_L0122138_Player(name);
        players.add(newPlayer);
        savePlayersAsText();
        return newPlayer; // Mengembalikan objek Player yang baru didaftarkan
    }

    public Responsi1_PPBO_L0122138_Player loginPlayer(String name) {
        // Cek apakah pemain sudah terdaftar
        for (Responsi1_PPBO_L0122138_Player player : players) {
            if (player.getName().equalsIgnoreCase(name)) {
                System.out.println("Selamat datang, " + name + "!");
                return player;
            }
        }

        System.out.println("Player dengan nama '" + name + "' tidak terdaftar. Silakan daftar terlebih dahulu.");
        return null;
    }

    public List<Responsi1_PPBO_L0122138_Player> getPlayers() {
        return players;
    }

}