package action;

import model.Host;
import model.Match;
import model.Player;
import model.PlayerAction;
import type.MatchType;

public class BetAction {
    public boolean handleBet(PlayerAction action, Player player, Match match, Host host) {
        if (player.getBalance() < action.getAmount()) return false;

        switch (getPlayerResult(action.getMatchType(), match.getResult())) {
            case 0 -> player.setGamesLost(player.getGamesLost() + 1);
            case 1 -> handleWin(player, getWonAmount(action, match), host);
            case -1 -> handleLose(player, action.getAmount(), host);
        }
        return true;
    }

    private int getPlayerResult(MatchType playerBet, MatchType matchResult) {
        if (matchResult.equals(MatchType.DRAW)) return 0;
        if (playerBet.equals(matchResult)) return 1;
        else return -1;
    }

    private void handleWin(Player player, int amount, Host host) {
        player.setGamesWon(player.getGamesWon() + 1);
        player.setBalance(player.getBalance() + amount);
        host.setBalance(host.getBalance() - amount);
    }

    private void handleLose(Player player, int amount, Host host) {
        player.setGamesLost(player.getGamesLost() + 1);
        player.setBalance(player.getBalance() - amount);
        host.setBalance (host.getBalance() + amount);
    }

    private int getWonAmount(PlayerAction action, Match match) {
        MatchType matchType = action.getMatchType();
        double returnRate = (matchType == MatchType.A) ? match.getReturnRateA() : match.getReturnRateB();
        return (int) Math.floor(action.getAmount() * returnRate);
    }
}
