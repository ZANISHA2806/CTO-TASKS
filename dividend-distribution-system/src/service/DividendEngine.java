package service;

import java.util.ArrayList;
import java.util.List;

import exception.*;
import model.*;
import util.DividendLogger;

public class DividendEngine {

    private List<ShareholderRecord> shareholders;
    private double dividendPerShare;
    private double tdsRate;

    private int processedCount = 0;
    private int failureCount = 0;

    private List<DividendResult> results = new ArrayList<>();

    public DividendEngine(List<ShareholderRecord> shareholders,
                          double dividendPerShare,
                          double tdsRate) {
        this.shareholders = shareholders;
        this.dividendPerShare = dividendPerShare;
        this.tdsRate = tdsRate;
    }

    public void processDistribution() {

        for (ShareholderRecord sh : shareholders) {

            processedCount++;

            try {
                checkEligibility(sh);
                validateBank(sh);

                double gross = calculateDividend(sh);
                double tds = deductTDS(sh, gross);
                double net = gross - tds;

                DividendResult result = new DividendResult(
                        sh.getShareholderId(),
                        gross,
                        tds,
                        net,
                        Status.DISBURSED,
                        "DISBURSED"
                );

                results.add(result);

                DividendLogger.logSuccess(
                        sh.getShareholderId() +
                        ": Gross Rs." + gross +
                        " | TDS Rs." + tds +
                        " | Net Rs." + net +
                        " — DISBURSED"
                );

            } catch (DividendException | TaxDeductionException e) {

                failureCount++;

                DividendLogger.logFailure(sh.getShareholderId(), e);

                results.add(new DividendResult(
                        sh.getShareholderId(),
                        0,
                        0,
                        0,
                        Status.FAILED,
                        e.getMessage()
                ));

                double failureRate = (double) failureCount / processedCount;

                if (failureRate > 0.30) {
                    throw new DividendProcessingHaltException(
                            failureCount,
                            processedCount,
                            failureRate
                    );
                }
            }
        }
    }

    private void checkEligibility(ShareholderRecord sh)
            throws IneligibleShareholderException {

        if (!sh.isEligible() || sh.getSharesHeld() <= 0) {
            throw new IneligibleShareholderException(
                    sh.getShareholderId(),
                    "no shares held on record date"
            );
        }
    }

    private void validateBank(ShareholderRecord sh)
            throws BankAccountMismatchException {

        if (!sh.isBankValid()) {
            throw new BankAccountMismatchException(
                    "registered IFSC does not match"
            );
        }
    }

    private double calculateDividend(ShareholderRecord sh)
            throws ZeroDividendException {

        double gross = sh.getSharesHeld() * dividendPerShare;

        if (gross < 1) {
            throw new ZeroDividendException(
                    sh.getSharesHeld() + " shares yields Rs.0 after rounding"
            );
        }

        return gross;
    }

    private double deductTDS(ShareholderRecord sh, double gross)
            throws TaxDeductionException {

        double tds = gross * tdsRate;

        if (sh.isTdsFail()) {
            throw new TaxDeductionException(
                    sh.getShareholderId(),
                    tds
            );
        }

        return tds;
    }

    public void generateDividendStatement() {

        int successCount = 0;

        for (DividendResult r : results) {

            if (r.getStatus() == Status.DISBURSED) {
                successCount++;
            }

            DividendLogger.logInfo(
                    r.getShareholderId() + ": " + r.getMessage()
            );
        }

        int total = results.size();
        double failureRate = (double) failureCount / total * 100;

        DividendLogger.logInfo(
                "Processed=" + total +
                " | Success=" + successCount +
                " | Failed=" + failureCount +
                " | Failure rate=" + failureRate + "%"
        );
    }
}