package action;

import model.Player;
import model.PlayerAction;

public class DepositTransaction implements Transaction {
    @Override
    public boolean handle(PlayerAction action, Player player) {
        player.setBalance(player.getBalance() + action.getAmount());
        return true;
    }
}
