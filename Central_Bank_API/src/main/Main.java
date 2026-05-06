package main;

import exception.APICircuitBreakerException;
import wrapper.ResilientAPIWrapper;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        ResilientAPIWrapper api = new ResilientAPIWrapper();

        Map<String,String> params = new HashMap<>();
        params.put("clientId", "demo123");
        params.put("requestId", "txn001");

        for (String endpoint : Sample.getTestEndpoints()) {

            try {

                System.out.println("\nCalling Endpoint: " + endpoint);

                String result = api.call(endpoint, params);

                if (result != null && !result.isEmpty()) {
                    System.out.println(endpoint + " : SUCCESS");
                }

                if ("".equals(result)) {
                    System.out.println(
                            endpoint
                            + " : MalformedResponseException handled, continuing"
                    );
                }

            } catch (APICircuitBreakerException e) {

                System.out.println(
                        "\nCIRCUIT OPEN : " + e.getMessage()
                );

                break;
            }
        }

        System.out.println("\nAPI simulation completed.");
    }
}