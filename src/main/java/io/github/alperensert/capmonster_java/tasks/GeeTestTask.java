package io.github.alperensert.capmonster_java.tasks;

import io.github.alperensert.capmonster_java.utilities.Client;
import io.github.alperensert.capmonster_java.utilities.Proxy;
import io.github.alperensert.capmonster_java.utilities.RequestBuilder;
import io.github.alperensert.capmonster_java.utilities.UserAgent;
import org.json.JSONObject;

/**
 * This class contains everything for GeeTest solving task
 * @see Client
 * @since 1.2
 */
public class GeeTestTask extends Client {
    /**
     * @param apiKey Your unique key for solving captchas
     */
    public GeeTestTask(String apiKey) {
        super(apiKey);
    }

    /**
     * Create GeeTest task easily with task builder
     * @param taskBuilder TaskBuilder
     * @see TaskBuilder
     * @return Created task's ID
     */
    public int createTask(TaskBuilder taskBuilder) {
        RequestBuilder request = new RequestBuilder(this.CLIENT_KEY, "GeeTestTask")
                .addTask("websiteURL", taskBuilder.websiteUrl)
                .addTask("gt", taskBuilder.gt)
                .addTask("challenge", taskBuilder.challenge)
                .addTask("geetestApiServerSubdomain", taskBuilder.apiServerSubdomain)
                .addTask("geetestGetLib", taskBuilder.getLib)
                .addProxy(taskBuilder.proxy)
                .addUserAgent(taskBuilder.userAgent);
        JSONObject t = makeRequest("createTask", request.build());
        return t.getInt("taskId");
    }

    public static class TaskBuilder {
        /**
         * Address of the page on which the captcha is recognized
         */
        private final String websiteUrl;
        /**
         * The GeeTest identifier key for the domain. Static value, rarely updated.
         */
        private final String gt;
        /**
         * A dynamic key.
         * Each time our API is called, we need to get a new key value.
         * If the captcha is loaded on the page, then the challenge value is no longer valid.
         * It is necessary to examine the requests and find the one in which this value is returned and,
         * before each creation of the recognition task, execute this request and parse the challenge from it.
         */
        private final String challenge;
        /**
         * May be required for some sites.
         */
        private String apiServerSubdomain;
        /**
         * Send JSON as a string.
         */
        private String getLib;
        /**
         * @see UserAgent
         */
        private UserAgent userAgent;
        /**
         * @see Proxy
         */
        private Proxy proxy;

        /**
         * @param websiteUrl WebsiteUrl
         * @param gt GT
         * @param challenge Challenge
         * @see #challenge
         * @see #websiteUrl
         * @see #gt
         * @since 1.2
         */
        public TaskBuilder(String websiteUrl, String gt, String challenge) {
            this.websiteUrl = websiteUrl;
            this.gt = gt;
            this.challenge = challenge;
        }

        /**
         * @param subdomain Api server subdomain
         * @see #apiServerSubdomain
         * @return TaskBuilder
         * @since 1.2
         */
        public TaskBuilder setServerSubdomain(String subdomain) {
            this.apiServerSubdomain = subdomain;
            return this;
        }

        /**
         * @param lib getLib
         * @see #getLib
         * @return TaskBuilder
         * @since 1.2
         */
        public TaskBuilder setGetLib(String lib) {
            this.getLib = lib;
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
