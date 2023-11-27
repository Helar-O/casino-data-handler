package type;

public enum OperationType {
    DEPOSIT,
    BET,
    WITHDRAW;

    public static OperationType get(String type) {
        try {
            return valueOf(type);
        } catch (Exception ignored) {
            throw new IllegalArgumentException("Invalid operation type " + type);
        }
    }
}
