package main;
import java.util.Arrays;
import java.util.List;

public class Sample {

    public static List<String> getTestEndpoints() {

        return Arrays.asList(
                "SUCCESS_CALL",
                "RATE_LIMIT_CALL",
                "AUTH_ERROR_CALL",
                "SERVICE_DOWN_CALL",
                "BAD_RESPONSE_CALL",
                "FAIL_CALL_1",
                "FAIL_CALL_2",
                "FAIL_CALL_3",
                "FAIL_CALL_4",
                "FAIL_CALL_5"
        );
    }
}