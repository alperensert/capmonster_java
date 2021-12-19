package io.github.alperensert.capmonster_java.tasks;

import io.github.alperensert.capmonster_java.utilities.Client;
import io.github.alperensert.capmonster_java.utilities.RequestBuilder;
import org.json.JSONObject;

/**
 * This class contains everything for Google ReCaptcha3 solving task.
 * @see Client
 * @since 1.1
 */
public class RecaptchaV3Task extends Client {

    /**
     * @param clientKey Unique key for solving captchas
     * @since 1.1
     */
    public RecaptchaV3Task(String clientKey) {
        super(clientKey);
    }

    /**
     * Create RecaptchaV3 Task easily with using task builder
     * @param taskBuilder TaskBuilder
     * @see TaskBuilder
     * @return Created task's ID
     * @since 1.1
     */
    public int createTask(TaskBuilder taskBuilder) {
        RequestBuilder request = new RequestBuilder(this.CLIENT_KEY, "RecaptchaV3TaskProxyless")
                .addTask("websiteURL", taskBuilder.websiteUrl)
                .addTask("websiteKey", taskBuilder.websiteKey)
                .addNoCache(taskBuilder.cache)
                .addTask("minScore", taskBuilder.minimumScore)
                .addTask("pageAction", taskBuilder.pageAction);
        JSONObject t = makeRequest("createTask", request.build());
        return t.getInt("taskId");
    }

    /**
     * Task Builder helps you while creating credentials
     * @since 1.2
     */
    public static class TaskBuilder {
        /**
         * Address of a webpage with Google ReCaptcha
         */
        private final String websiteUrl;
        /**
         * Recaptcha website key. Mostly you can find it in render URL
         * <pre>
         * https://www.google.com/recaptcha/api.js?render="THAT_ONE"
         * </pre>
         */
        private final String websiteKey;
        /**
         * It's like trust value. Must be 0.1 and 0.9
         */
        private double minimumScore;
        /**
         * Widget action value. Website owner defines what user is doing on the page through this parameter.
         * <br><br> <b>Default value is</b> <i>verify</i>. <br><br>Example:
         * <pre>grecaptcha.execute('site_key', {action:'verify'})</pre>
         */
        private String pageAction;
        /**
         * If you don't want cached results or experiencing any of the followings, this must be true.
         * <ul>
         *     <li>All parameters are correct but site rejects token</li>
         *     <li>All parameters are correct but site rejects token sometimes</li>
         * </ul>
         */
        private boolean cache;

        /**
         * @param websiteUrl WebsiteUrl
         * @param websiteKey WebsiteKey
         * @see #websiteKey
         * @see #websiteUrl
         * @since 1.2
         */
        public TaskBuilder(String websiteUrl, String websiteKey) {
            this.websiteUrl = websiteUrl;
            this.websiteKey = websiteKey;
        }

        /**
         * Set minimum score
         * @param score minimumScore
         * @see #minimumScore
         * @return TaskBuilder
         * @since 1.2
         */
        public TaskBuilder setMinScore(double score) {
            if (0.1 <= score && score <= 0.9) {
                this.minimumScore = score;
                return this;
            } throw new IllegalArgumentException("Minimum score must between 0.1 and 0.9");
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

        /**
         * @see #pageAction
         * @param action .
         * @return TaskBuilder
         * @since 1.2
         */
        public TaskBuilder setPageAction(String action) {
            this.pageAction = action;
            return this;
        }
    }
}
