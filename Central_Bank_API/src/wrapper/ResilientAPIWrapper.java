package wrapper;

import client.ApiClient;
import exception.*;
import util.LoggerUtil;

import java.util.Map;
import java.util.logging.Logger;

public class ResilientAPIWrapper {

    private int consecutiveFailures = 0;

    private final ApiClient client = new ApiClient();
    private final Logger logger = LoggerUtil.getLogger();

    public String call(String endpoint, Map<String, String> params) {

        try {
            String response = client.callAPI(endpoint, params);
            resetFailures();
            logger.info(endpoint + " : SUCCESS");
            return response;

        } catch (RateLimitException e) {
            return handleRateLimit(endpoint, params, e);

        } catch (AuthenticationException e) {
            recordFailure(endpoint, e);
            logger.severe(endpoint + " : Authentication failed. Alerting operator.");
            return null;

        } catch (MalformedResponseException e) {
            logger.warning(endpoint + " : Malformed response. Continuing.");
            return "";

        } catch (ServiceUnavailableException e) {
            recordFailure(endpoint, e);
            logger.severe(endpoint + " : Service unavailable.");
            return null;

        } catch (CentralBankAPIException e) {
            recordFailure(endpoint, e);
            return null;
        }
    }

    private String handleRateLimit(String endpoint,
                                   Map<String, String> params,
                                   RateLimitException e) {

        logger.warning(
                endpoint + " : Rate limited. Retrying after "
                        + e.getRetry()
                        + " seconds"
        );

        try {
            Thread.sleep(e.getRetry() * 1000L);

            String response = client.callAPI(endpoint, params);

            resetFailures();
            logger.info(endpoint + " : SUCCESS after retry");

            return response;

        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            recordFailure(endpoint, ex);
            return null;

        } catch (CentralBankAPIException ex) {
            recordFailure(endpoint, ex);
            return null;
        }
    }

    private void recordFailure(String endpoint, Exception e) {

        consecutiveFailures++;

        logger.severe(
                endpoint
                        + " : FAILURE - "
                        + e.getClass().getSimpleName()
                        + " - "
                        + e.getMessage()
        );

        if (consecutiveFailures >= 5) {
            throw new APICircuitBreakerException(
                    "CIRCUIT OPEN - "
                            + consecutiveFailures
                            + " consecutive failures"
            );
        }
    }

    private void resetFailures() {
        consecutiveFailures = 0;
    }
}