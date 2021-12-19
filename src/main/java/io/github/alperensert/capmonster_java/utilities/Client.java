package io.github.alperensert.capmonster_java.utilities;

import io.github.alperensert.capmonster_java.exceptions.CapmonsterException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Abstract class for tasks
 * @since 1.2
 */
public abstract class Client {
    /**
     * Default time for JoinTaskResult method
     * @see #joinTaskResult(int, int)
     */
    private static final int maximumTime = 120;
    /**
     * Balance url address for api
     * @see #getBalance()
     */
    private static final String balanceUrl = "/getBalance";
    /**
     * Task result url address for api
     * @see #getTaskResult(int)
     */
    private static final String taskResultUrl = "/getTaskResult";
    /**
     * Task create url address for api
     */
    private static final String createTaskUrl = "/createTask";
    /**
     * Base host url for api
     */
    private static final String hostUrl = "https://api.capmonster.cloud";
    /**
     * Unique key of your account
     */
    protected final String CLIENT_KEY;

    /**
     * @param apiKey Your unique key for solving captchas
     */
    public Client(String apiKey) { CLIENT_KEY = apiKey; }

    /**
     * Get account's balance
     * @return account balance
     * @since 1.2
     */
    public int getBalance() {
        JSONObject data = new JSONObject();
        data.put("clientKey", CLIENT_KEY);
        return (int) makeRequest("getBalance", data).get("balance");
    }

    /**
     * Get task result
     * @see #joinTaskResult(int) 
     * @param taskId ID which was obtained in createTask method
     * @return Task's result if is ready
     * @since 1.2
     */
    public JSONObject getTaskResult(int taskId) {
        JSONObject data = new JSONObject();
        data.put("clientKey", CLIENT_KEY);
        data.put("taskId", taskId);
        JSONObject result = makeRequest("getTaskResult", data);
        boolean isReady = isReady(result);
        return isReady ? (JSONObject) result.get("solution") : null;
    }

    /**
     * Check the task until successfully solve
     * @param taskId ID which was obtained in createTask method
     * @return Task's result when is ready
     * @throws CapmonsterException Maximum time is exceed
     * @throws InterruptedException .
     * @since 1.2
     */
    public JSONObject joinTaskResult(int taskId) throws InterruptedException {
        for (int i = 0; i <= maximumTime + 1; i += 2) {
            JSONObject result = getTaskResult(taskId);
            if (result != null) return result;
            else TimeUnit.SECONDS.sleep(2);
        }
        throw new CapmonsterException("ERROR_MAXIMUM_TIME_EXCEED", "Maximum time is exceed.");
    }

    /**
     * Check the task until successfully solve
     * @param taskId ID which was obtained in createTask method
     * @param maximumTime How long will check
     * @return Task's result when is ready
     * @throws CapmonsterException Maximum time is exceed
     * @throws InterruptedException .
     * @since 1.2
     */
    public JSONObject joinTaskResult(int taskId, int maximumTime) throws InterruptedException {
        for (int i = 0; i <= maximumTime + 1; i += 2) {
            JSONObject result = getTaskResult(taskId);
            if (result != null) return result;
            else TimeUnit.SECONDS.sleep(2);
        }
        throw new CapmonsterException("ERROR_MAXIMUM_TIME_EXCEED", "Maximum time is exceed.");
    }

    /**
     * Check response has an error message or not
     * @param response Response from api
     * @since 1.2
     */
    private static void checkResponse(String response) {
        JSONObject responseJson = new JSONObject(response);
        if (responseJson.get("errorId") != null && (int)responseJson.get("errorId") == 0) {
            return;
        }
        if ((int) responseJson.get("errorId") != 0) {
            throw new CapmonsterException((String) responseJson.get("errorCode"), (String) responseJson.get("errorDescription"));
        } else {
            throw new CapmonsterException("[ERROR CODE: HTTP_ERROR]", "Sometimes can be happen if capmonster servers there is too much intensity");
        }
    }

    /**
     * Check task is ready or not
     * @param response Response from api
     * @return ready or not
     * @since 1.2
     */
    private static boolean isReady(JSONObject response) {
        String status = response.get("status").toString();

        if (Objects.equals(status, "processing")) {
            return false;
        } else if (Objects.equals(status, "ready")) {
            return true;
        } else {
            throw new CapmonsterException((String) response.get("errorCode"), (String) response.get("errorDescription"));
        }
    }

    /**
     * Kinda request helper
     * @param method Request's method like getBalance or createTask
     * @param data Request data
     * @return Response from api
     * @since 1.2
     */
    protected JSONObject makeRequest(String method, JSONObject data) {
        JSONObject response = null;
        if (Objects.equals(method, "getBalance")) {
            method = balanceUrl;
        } else if (Objects.equals(method, "getTaskResult")) {
            method = taskResultUrl;
        } else if (Objects.equals(method, "createTask")) {
            method = createTaskUrl;
            data.put("softId", 30);
        }
        try {
            response = requestHandler(hostUrl + method, data);
            checkResponse(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Handle with requests
     * @param url Request URL
     * @param data Request data
     * @return Response from api
     * @throws IOException #
     * @since 1.2
     */
    private static JSONObject requestHandler(String url, JSONObject data) throws IOException {
        URL parsedUrl = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection)parsedUrl.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Connection-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = data.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return new JSONObject(response.toString());
        }
    }
}
