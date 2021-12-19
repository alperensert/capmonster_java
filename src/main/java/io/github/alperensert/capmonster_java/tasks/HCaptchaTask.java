package io.github.alperensert.capmonster_java.tasks;

import io.github.alperensert.capmonster_java.utilities.*;
import org.json.JSONObject;

/**
 * This class contains everything for HCaptcha solving task
 * @see Client
 * @since 1.1
 */
public class HCaptchaTask extends Client {
    /**
     * @param apiKey Your unique key for solving captchas
     */
    public HCaptchaTask(String apiKey) {
        super(apiKey);
    }

    /**
     * Create HCaptcha task easily with task builder
     * @param taskBuilder TaskBuilder
     * @see TaskBuilder
     * @return Created task's ID
     */
    public int createTask(TaskBuilder taskBuilder) {
        RequestBuilder request = new RequestBuilder(this.CLIENT_KEY, "HCaptchaTask")
                .addTask("websiteURL", taskBuilder.websiteUrl)
                .addTask("websiteKey", taskBuilder.websiteKey)
                .addTask("isInvisible", taskBuilder.isInvisible)
                .addTask("data", taskBuilder.customData)
                .addUserAgent(taskBuilder.userAgent)
                .addCookies(taskBuilder.cookies)
                .addProxy(taskBuilder.proxy)
                .addNoCache(taskBuilder.cache);
        JSONObject t = makeRequest("createTask", request.build());
        return t.getInt("taskId");
    }

    /**
     * Task Builder helps you while creating credentials about task
     * @since 1.2
     */
    public static class TaskBuilder {
        /**
         * Address of a webpage with hCaptcha
         */
        private final String websiteUrl;
        /**
         * hCaptcha website key.
         */
        private final String websiteKey;
        /**
         * Use true for invisible version of hcaptcha
         */
        private boolean isInvisible = false;
        /**
         * Custom data that is used in some implementations of hCaptcha, mostly with isInvisible=true.
         * In most cases you see it as rqdata inside network requests. <br>
         * <h2>IMPORTANT!</h2>
         * You must set userAgent if you submit captcha with data parameter. The value should match the
         * User-Agent you use when interacting with the target website.
         */
        private String customData;
        /**
         * @see Cookies
         */
        private Cookies cookies;
        /**
         * @see UserAgent
         */
        private UserAgent userAgent;
        /**
         * @see Proxy
         */
        private Proxy proxy;
        /**
         * If you don't want cached results or experiencing any of the followings, this must be true.
         * <ul>
         *     <li>All parameters are correct but site rejects token</li>
         *     <li>All parameters are correct but site rejects token sometimes</li>
         * </ul>
         */
        private boolean cache = false;

        /**
         * @param websiteUrl WebsiteUrl
         * @param websiteKey WebsiteKey
         * @see #websiteUrl
         * @see #websiteKey
         * @since 1.2
         */
        public TaskBuilder(String websiteUrl, String websiteKey) {
            this.websiteUrl = websiteUrl;
            this.websiteKey = websiteKey;
        }

        /**
         * @param visible is Invisible
         * @return TaskBuilder
         * @see #isInvisible
         * @since 1.2
         */
        public TaskBuilder setVisibility(boolean visible) {
            this.isInvisible = visible;
            return this;
        }

        /**
         * @param data customData
         * @see #customData
         * @since 1.2
         * @return TaskBuilder
         */
        public TaskBuilder setCustomData(String data) {
            this.customData = data;
            return this;
        }

        /**
         * Browser's User-Agent which is used in emulation. It is required that you use a signature of a modern browser,
         * otherwise Google will ask you to "update your browser"
         * @param ua UserAgent
         * @see UserAgent
         * @return TaskBuilder
         * @since 1.2
         */
        public TaskBuilder setUserAgent(UserAgent ua) {
            this.userAgent = ua;
            return this;
        }

        /**
         * Additional cookies which we must use during interaction with target page or Google.
         * @param cookies Cookies
         * @return TaskBuilder
         * @see Cookies
         * @since 1.2
         */
        public TaskBuilder setCookies(Cookies cookies) {
            this.cookies = cookies;
            return this;
        }

        /**
         * Set proxy for the task.
         * If the proxy is authorized by IP, then be sure to add 116.203.55.208 to the white list.
         * @param proxy Proxy
         * @see Proxy
         * @return TaskBuilder
         * @since 1.2
         */
        public TaskBuilder setProxy(Proxy proxy) {
            if (proxy != null) this.proxy = proxy;
            return this;
        }

        /**
         * @see #cache
         * @param cache .
         * @return TaskBuilder
         * @since 1.2
         */
        public TaskBuilder setCache(boolean cache) {
            this.cache = cache;
            return this;
        }
    }
}
