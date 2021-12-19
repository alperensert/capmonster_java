package io.github.alperensert.capmonster_java.utilities;

import org.json.JSONObject;

/**
 * Request builder is helps to build JSON data for tasks
 * @since 1.2
 */
public class RequestBuilder {
    /**
     * "task" data holder
     */
    private final JSONObject TASK = new JSONObject();
    /**
     * Main data holder
     */
    private final JSONObject DATA = new JSONObject();

    /**
     * Create a request from scratch
     * @param clientKey Api key
     * @param taskType Task type
     * @since 1.2
     */
    public RequestBuilder(String clientKey, String taskType) {
        DATA.put("clientKey", clientKey);
        TASK.put("type", taskType);
    }

    /**
     * Build the request data
     * @return JSON data
     * @since 1.2
     */
    public JSONObject build() {
        DATA.put("task", TASK);
        return DATA;
    }

    public RequestBuilder addTask(String key, String value) {
        if (value != null) TASK.put(key, value);
        return this;
    }

    public RequestBuilder addTask(String key, int value) {
        TASK.put(key, value);
        return this;
    }

    public RequestBuilder addTask(String key, boolean value) {
        TASK.put(key, value);
        return this;
    }

    public RequestBuilder addTask(String key, double value) {
        TASK.put(key, value);
        return this;
    }

    public RequestBuilder addData(String key, String value) {
        if (value != null) DATA.put(key, value);
        return this;
    }

    public RequestBuilder addData(String key, int value) {
        DATA.put(key, value);
        return this;
    }

    public RequestBuilder addData(String key, boolean value) {
        DATA.put(key, value);
        return this;
    }

    public RequestBuilder addData(String key, double value) {
        DATA.put(key, value);
        return this;
    }

    /**
     * Add cookies automatically. If the cookies aren't defined, this method does nothing like expected
     * @param cookies Cookies
     * @return RequestBuilder
     * @since 1.2
     */
    public RequestBuilder addCookies(Cookies cookies) {
        if (cookies != null) TASK.put("cookies", cookies.get());
        return this;
    }

    /**
     * Add no cache property to task
     * @param cache -
     * @return RequestBuilder
     * @since 1.2
     */
    public RequestBuilder addNoCache(boolean cache) {
        if (cache) TASK.put("nocache", true);
        return this;
    }

    /**
     * Add user agent property to task
     * @param ua UserAgent
     * @see UserAgent
     * @return RequestBuilder
     * @since 1.2
     */
    public RequestBuilder addUserAgent(UserAgent ua) {
        if (ua != null) TASK.put("userAgent", ua.get());
        return this;
    }

    /**
     * Add proxy credentials to task
     * @param proxy Proxy
     * @see Proxy
     * @return RequestBuilder
     * @since 1.2
     */
    public RequestBuilder addProxy(Proxy proxy) {
        if (proxy == null) {
            TASK.put("type", TASK.getString("type") + "Proxyless");
            return this;
        }
        if (proxy.type != null && proxy.address != null && proxy.port != 0) {
            TASK.put("proxyType", proxy.type);
            TASK.put("proxyAddress", proxy.address);
            TASK.put("proxyPort", proxy.port);

            if (proxy.username != null && proxy.password != null) {
                TASK.put("proxyLogin", proxy.username);
                TASK.put("proxyPassword", proxy.password);
            }
        }
        return this;
    }
}
