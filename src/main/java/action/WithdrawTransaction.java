package action;

import model.Player;
import model.PlayerAction;

public class WithdrawTransaction implements Transaction {
    @Override
    public boolean handle(PlayerAction action, Player player) {
        if (player.getBalance() >= action.getAmount()) {
            player.setBalance(player.getBalance() - action.getAmount());
            return true;
        }
        return false;
    }
}
