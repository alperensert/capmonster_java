package io.github.alperensert.capmonster_java.tasks;

import io.github.alperensert.capmonster_java.utilities.*;
import org.json.JSONObject;

/**
 * This class contains everything for FunCaptcha solving task
 * @see Client
 * @since 1.1
 */
public class FunCaptchaTask extends Client {
    /**
     * @param apiKey Your unique key for solving captchas
     */
    public FunCaptchaTask(String apiKey) {
        super(apiKey);
    }

    /**
     * Create Funcaptcha task easily with task builder
     * @param taskBuilder TaskBuilder
     * @return Created task's ID
     */
    public int createTask(TaskBuilder taskBuilder) {
        RequestBuilder request = new RequestBuilder(this.CLIENT_KEY, "FunCaptchaTask")
                .addTask("websiteURL", taskBuilder.websiteUrl)
                .addTask("websitePublicKey", taskBuilder.websitePublicKey)
                .addTask("funcaptchaApiJSSubdomain", taskBuilder.apiJSSubdomain)
                .addUserAgent(taskBuilder.userAgent)
                .addProxy(taskBuilder.proxy)
                .addCookies(taskBuilder.cookies);
        JSONObject t = makeRequest("createTask", request.build());
        return t.getInt("taskId");
    }

    public static class TaskBuilder {
        /**
         * Address of a webpage with FunCaptcha
         */
        private final String websiteUrl;
        /**
         * FunCaptcha website key. Mostly you can find in funcaptcha div
         * <pre>{@code
         *  <div id="funcaptcha" data-pkey="THAT_ONE"></div>
         * }</pre>
         */
        private final String websitePublicKey;
        /**
         * A special subdomain of funcaptcha.com, from which the JS captcha widget should be loaded.
         * Most FunCaptcha installations work from shared domains, so this option is only needed in certain rare cases.
         */
        private String apiJSSubdomain;
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
         * @param websiteUrl Website URL
         * @param websitePublicKey Website public key
         * @see #websiteUrl
         * @see #websitePublicKey
         * @since 1.2
         */
        public TaskBuilder(String websiteUrl, String websitePublicKey) {
            this.websiteUrl = websiteUrl;
            this.websitePublicKey = websitePublicKey;
        }

        /**
         * @param subdomain API javascript subdomain
         * @see #apiJSSubdomain
         * @return TaskBuilder
         */
        public TaskBuilder setApiSubdomain(String subdomain) {
            this.apiJSSubdomain = subdomain;
            return this;
        }

        /**
         * Additional cookies which we must use during interaction with target page or Google.
         * @param cookies  Cookies
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
    }
}
