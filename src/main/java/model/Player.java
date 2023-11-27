package model;

import lombok.Data;

@Data
public class Player {
    private long balance = 0;
    private int gamesWon;
    private int gamesLost;

    public double getWinRate() {
        if (gamesWon > 0 || gamesLost > 0)
            return Math.round((double) gamesWon / (gamesWon + gamesLost) * 100.0) / 100.0;
        return 0;
    }
}
