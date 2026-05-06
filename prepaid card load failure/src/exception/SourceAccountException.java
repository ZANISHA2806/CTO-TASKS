package exception;

import enums.AccountStatus;

public class SourceAccountException extends CardLoadException {

    private final String sourceVPA;
    private final AccountStatus status;

    public SourceAccountException(String sourceVPA, AccountStatus status) {

        super(
            sourceVPA +
            (status == AccountStatus.FROZEN ? " is frozen" : " is invalid")
        );

        this.sourceVPA = sourceVPA;
        this.status = status;
    }

    public String getSourceVPA() {
        return sourceVPA;
    }

    public AccountStatus getStatus() {
        return status;
    }
}