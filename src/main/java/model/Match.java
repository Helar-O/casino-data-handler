package model;

import lombok.Builder;
import lombok.Data;
import type.MatchType;

@Data
@Builder
public class Match {
    private double returnRateA;
    private double returnRateB;
    private MatchType result;
}
