package io.github.alperensert.capmonster_java.tasks;

import io.github.alperensert.capmonster_java.utilities.*;
import org.json.JSONObject;

/**
 * This class contains everything for Google ReCaptcha2 solving task.
 * @see Client
 * @since 1.1
 */
public class RecaptchaV2Task extends Client {
    /**
     * @param clientKey #CLIENT_KEY}
     * @since 1.1
     */
    public RecaptchaV2Task(String clientKey) {
        super(clientKey);
    }

    /**
     * Create RecaptchaV2 Task easily with using task builder
     * @param taskBuilder TaskBuilder
     * @see TaskBuilder
     * @return Created task's ID
     * @since 1.1
     */
    public int createTask(TaskBuilder taskBuilder) {
        RequestBuilder request = new RequestBuilder(this.CLIENT_KEY, "NoCaptchaTask")
                .addTask("websiteURL", taskBuilder.websiteUrl)
                .addTask("websiteKey", taskBuilder.websiteKey)
                .addNoCache(taskBuilder.cache)
                .addCookies(taskBuilder.cookies)
                .addUserAgent(taskBuilder.userAgent)
                .addTask("recaptchaDataSValue", taskBuilder.recaptchaSValue)
                .addProxy(taskBuilder.proxy);
        JSONObject t = makeRequest("createTask", request.build());
        return t.getInt("taskId");
    }

    /**
     * Task Builder helps you while creating credentials about task
     * @since 1.2
     */
    public static class TaskBuilder {
        /**
         * Address of a webpage with Google ReCaptcha
         */
        private final String websiteUrl;
        /**
         * Recaptcha website key. Mostly you can find it in g-recaptcha div.
         * <pre>{@code
         *  <div class="g-recaptcha" data-sitekey="THAT_ONE"></div>
         * }</pre>
         */
        private final String websiteKey;
        /**
         * @see Proxy
         */
        private Proxy proxy;
        /**
         * @see UserAgent
         */
        private UserAgent userAgent;
        /**
         * @see Cookies
         */
        private Cookies cookies;
        /**
         * Some custom implementations may contain additional "data-s" parameter in ReCaptcha2 div,
         * which is in fact a one-time token and must be grabbed every time you want to solve a ReCaptcha2. <br><br>
         * Mostly you can find it in g-recaptcha div
         * <pre>{@code
         * <div class="g-recaptcha" data-sitekey="some sitekey" data-s="THIS_ONE"></div>
         * }</pre>
         */
        private String recaptchaSValue;
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
            this.websiteKey = websiteKey;
            this.websiteUrl = websiteUrl;
        }

        /**
         * Additional cookies which we must use during interaction with target page or Google.
         * @param cookies Cookies
         * @see Cookies
         * @return TaskBuilder
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
         * Some custom implementations may contain additional "data-s" parameter in ReCaptcha2 div,
         * which is in fact a one-time token and must be grabbed every time you want to solve a ReCaptcha2.
         * @see #recaptchaSValue
         * @param value .
         * @return TaskBuilder
         * @since 1.2
         */
        public TaskBuilder setRecaptchaSValue(String value) {
            this.recaptchaSValue = value;
            return this;
        }

        /**
         * @see #cache
         * @param cache Cached results or not
         * @return TaskBuilder
         * @since 1.2
         */
        public TaskBuilder setCache(boolean cache) {
            this.cache = cache;
            return this;
        }
    }
}
