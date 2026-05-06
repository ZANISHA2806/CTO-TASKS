package client;

import exception.*;
import java.util.Map;

public class ApiClient {

    private boolean hasRetried = false;

    public String callAPI(String endpoint, Map<String, String> params)
            throws CentralBankAPIException {

        if ("SUCCESS_CALL".equals(endpoint)) {
            return "SUCCESS";
        }

        if ("RATE_LIMIT_CALL".equals(endpoint)) {
            if (!hasRetried) {
                hasRetried = true;
                throw new RateLimitException("Too many requests", 2);
            }
            return "SUCCESS";
        }

        if ("AUTH_ERROR_CALL".equals(endpoint)) {
            throw new AuthenticationException("Invalid API credentials");
        }

        if ("SERVICE_DOWN_CALL".equals(endpoint)) {
            throw new ServiceUnavailableException(
                    "Service is temporarily unavailable"
            );
        }

        if ("BAD_RESPONSE_CALL".equals(endpoint)) {
            throw new MalformedResponseException(
                    "Invalid response format"
            );
        }

        if (endpoint.startsWith("FAIL_CALL_")) {
            throw new ServiceUnavailableException(
                    "Service still failing"
            );
        }

        return "SUCCESS";
    }
}