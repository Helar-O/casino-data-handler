package action;

import model.Player;
import model.PlayerAction;

public interface Transaction {
    boolean handle(PlayerAction action, Player player);
}
