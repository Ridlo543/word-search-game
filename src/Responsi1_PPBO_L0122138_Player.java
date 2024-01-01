public class Responsi1_PPBO_L0122138_Player {
    private String name;
    private int score;

    // konstruktor
    public Responsi1_PPBO_L0122138_Player(String name) {
        this.name = name;
        this.score = 0;
    }

    // Getter dan setter untuk name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter dan setter untuk score
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addToScore(int score) {
        this.score += score;
    }

    // Metode untuk mendapatkan representasi CSV dari objek Player
    public String toCsvString() {
        return name + "," + score;
    }

    // Metode untuk membuat objek Player dari representasi CSV
    public static Responsi1_PPBO_L0122138_Player fromCsvString(String csvString) {
        String[] parts = csvString.split(",");
        if (parts.length == 2) {
            String name = parts[0];
            int score = Integer.parseInt(parts[1]);
            Responsi1_PPBO_L0122138_Player player = new Responsi1_PPBO_L0122138_Player(name);
            player.setScore(score);
            return player;
        } else {
            return null;
        }
    }
}
