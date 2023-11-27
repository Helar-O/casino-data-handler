package type;

public enum MatchType {
    A,
    B,
    DRAW;

    public static MatchType get(String type) {
        try {
            return valueOf(type);
        } catch (Exception ignored) {
            throw new IllegalArgumentException("Invalid match result type " + type);
        }
    }
}
