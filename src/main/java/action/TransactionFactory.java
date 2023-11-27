package action;

import type.OperationType;

public class TransactionFactory {
    public Transaction createTransaction(OperationType operationType) {
        return switch (operationType) {
            case DEPOSIT -> new DepositTransaction();
            case WITHDRAW -> new WithdrawTransaction();
            default -> throw new IllegalArgumentException("Invalid operation type " + operationType);
        };
    }
}
