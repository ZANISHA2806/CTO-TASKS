package exception;

import enums.LimitType;

public class LoadLimitException extends CardLoadException {

    private final double attemptedamount;
    private final LimitType limitType;

    public LoadLimitException(double attemptedamount, LimitType limitType) {

        super(
            "Rs." + attemptedamount +
            (limitType == LimitType.SINGLELIMIT
                ? " exceeds single-load limit of Rs."
                : " exceeds daily-load limit of Rs.")
            + limitType.getLimit()
        );

        this.attemptedamount = attemptedamount;
        this.limitType = limitType;
    }

    public double getAttemptedamount() {
        return attemptedamount;
    }

    public LimitType getLimittype() {
        return limitType;
    }
}