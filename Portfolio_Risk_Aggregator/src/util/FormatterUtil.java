package util;

public class FormatterUtil {

    private FormatterUtil() {
    }

    public static String formatCurrency(double amount) {

        return String.format(
                "Rs.%.2f",
                amount
        );
    }

    public static String formatPercentage(double ratio) {

        return String.format(
                "%.2f%%",
                ratio * 100
        );
    }

    public static String formatPnL(double pnl) {

        if (pnl >= 0) {

            return String.format(
                    "+Rs.%.2f",
                    pnl
            );
        }

        return String.format(
                "-Rs.%.2f",
                Math.abs(pnl)
        );
    }

}