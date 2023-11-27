package service;

import action.Transaction;
import action.TransactionFactory;
import model.Host;
import model.Match;
import model.Player;
import model.PlayerAction;
import type.MatchType;
import type.OperationType;
import action.BetAction;
import util.FileHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class GameService {
    private final FileHandler fileHandler = new FileHandler();
    private final BetAction betAction = new BetAction();
    TransactionFactory transactionFactory = new TransactionFactory();

    public Map<String, Match> loadMatches(String fileName) {
        Map<String, Match> matchMap = new HashMap<>();
        for (String line : fileHandler.readFromFile(fileName)) {
            String[] row = line.split(",");
            matchMap.putIfAbsent(row[0], buildMatch(row));
        }
        return matchMap;
    }

    public List<PlayerAction> loadPlayerActions(String fileName) {
        List<PlayerAction> playerActions = new ArrayList<>();
        for (String line : fileHandler.readFromFile(fileName)) {
            playerActions.add(buildPlayerAction(line.split(",", -1)));
        }
        return playerActions;
    }

    public boolean handleAction(PlayerAction action, Player player, Match match, Host host) {
        if (action.getOperationType().equals(OperationType.BET))
            return betAction.handleBet(action, player, match, host);

        Transaction transaction = transactionFactory.createTransaction(action.getOperationType());
        return transaction.handle(action, player);
    }

    private Match buildMatch(String[] line) {
        return Match.builder()
                .returnRateA(Double.parseDouble(line[1]))
                .returnRateB(Double.parseDouble(line[2]))
                .result(MatchType.get(line[3]))
                .build();
    }

    private PlayerAction buildPlayerAction(String[] row) {
        return PlayerAction.builder()
                .playerId(row[0])
                .operationType(OperationType.get(row[1]))
                .matchId(nullIfEmpty(row[2]))
                .amount(Integer.parseInt(row[3]))
                .matchType(nullIfEmpty(row[4], MatchType::get))
                .build();
    }

    private String nullIfEmpty(String value) {
        return value.isEmpty() ? null : value;
    }

    private <T> T nullIfEmpty(String value, Function<String, T> function) {
        return value.isEmpty() ? null : function.apply(value);
    }
}
