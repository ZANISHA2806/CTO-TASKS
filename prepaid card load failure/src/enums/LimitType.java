package enums;

public enum LimitType {
    SINGLELIMIT(50000),
    DAILYLIMIT(10000);

    private int limit;

    LimitType(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }
}