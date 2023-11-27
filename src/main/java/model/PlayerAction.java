package model;

import lombok.Builder;
import lombok.Data;
import type.MatchType;
import type.OperationType;

@Data
@Builder
public class PlayerAction {
    private String playerId;
    private OperationType operationType;
    private String matchId;
    private int amount;
    private MatchType matchType;
}
