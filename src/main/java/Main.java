import model.Host;
import model.Match;
import model.Player;
import model.PlayerAction;
import service.GameService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String PLAYER_FILE_PATH = "player_data.txt";
    private static final String MATCH_FILE_PATH = "match_data.txt";

    private static final GameService gameService = new GameService();

    public static void main(String[] args) {
        Map<String, Match> matchMap = gameService.loadMatches(MATCH_FILE_PATH);
        Map<String, Player> playerMap = new HashMap<>();
        List<PlayerAction> invalidActions = new ArrayList<>();

        Host host = new Host();

        for (PlayerAction action : gameService.loadPlayerActions(PLAYER_FILE_PATH)) {
            Player player = playerMap.computeIfAbsent(action.getPlayerId(), k -> new Player());

            if (!gameService.handleAction(action, player, matchMap.get(action.getMatchId()), host)) {
                invalidActions.add(action);
            }
        }

        System.out.println("Invalid actions: " + invalidActions);
        playerMap.forEach((k, v) -> System.out.println(k + " " + v.getBalance() + " " + v.getWinRate()));
        System.out.println("Host balance: " + host.getBalance());
    }
}
