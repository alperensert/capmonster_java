package io.github.alperensert.capmonster_java.tasks;

import io.github.alperensert.capmonster_java.utilities.Client;
import io.github.alperensert.capmonster_java.utilities.RequestBuilder;
import org.json.JSONObject;

/**
 * Solve Image captchas
 * @see Client
 * @since 1.1
 */
public class ImageToTextTask extends Client {
    /**
     * @param clientKey Unique key for solving captchas. a.k.a api key
     * @since 1.1
     */
    public ImageToTextTask(String clientKey) { super(clientKey); }

    /**
     * Create ImageToText task easily with using task builder
     * @param taskBuilder TaskBuilder
     * @see TaskBuilder
     * @return Created task's ID
     * @since 1.1
     */
    public int createTask(TaskBuilder taskBuilder) {
        RequestBuilder request = new RequestBuilder(this.CLIENT_KEY, "ImageToTextTask")
                .addTask("body", taskBuilder.base64EncodedImage)
                .addTask("CapMonsterModule", taskBuilder.module)
                .addTask("recognizingThreshold", taskBuilder.recognizingThreshold)
                .addTask("Case", taskBuilder._case)
                .addTask("numeric", taskBuilder.numeric)
                .addTask("math", taskBuilder.math);
        JSONObject t = makeRequest("createTask", request.build());
        return t.getInt("taskId");
    }

    /**
     * Task builder helps you while creating credentials about task
     * @since 1.2
     */
    public static class TaskBuilder {
        /**
         * Image's body encoded in base64. Make sure to send it without line breaks and as string.
         * @see java.util.Base64
         */
        private final String base64EncodedImage;
        /**
         * Name of the recognizing module.
         * <br> All available modules:
         * <ul>
         *     <li>amazon</li>
         *     <li>botdetect</li>
         *     <li>facebook</li>
         *     <li>gmx</li>
         *     <li>google</li>
         *     <li>hotmail</li>
         *     <li>mailru</li>
         *     <li>ok</li>
         *     <li>oknew</li>
         *     <li>solvemedia</li>
         *     <li>steam</li>
         *     <li>vk</li>
         *     <li>yandex</li>
         *     <li>yandexnew</li>
         *     <li>yandexwave</li>
         * </ul>
         */
        private String module;
        /**
         * Required trust value of captcha result. Must between 0 and 100. <br><br>
         * For example, if you set this value to 90 and the system solved the captcha with confidence of 80,
         * then the money for the solution will not be charged.
         */
        private int recognizingThreshold;
        /**
         * Whether to consider case when deciding or not
         */
        private boolean _case;
        /**
         * Default value is <b>0</b> <br><br>
         * Pass <b>1</b> if the captcha consists of numbers only
         */
        private int numeric;
        /**
         * Captcha requires a mathematical action or not
         * <br><br>
         * If this is true, result will be mathematical action's result (e.g 2+5=<b>7</b>) return value will 7
         */
        private boolean math;


        /**
         * @param base64EncodedImage base64EncodedImage
         * @see #base64EncodedImage
         * @since 1.2
         */
        public TaskBuilder(String base64EncodedImage) {
            this.base64EncodedImage = base64EncodedImage;
        }

        /**
         * Set module name of task
         * @param name module
         * @see #module
         * @return TaskBuilder
         * @since 1.2
         */
        public TaskBuilder setModuleName(String name) {
            this.module = name;
            return this;
        }

        /**
         * Set recognizing threshold
         * @param value recognizingThreshold
         * @see #recognizingThreshold
         * @return TaskBuilder
         * @since 1.2
         */
        public TaskBuilder setThreshold(int value) {
            this.recognizingThreshold = value;
            return this;
        }

        /**
         * @param _case _case
         * @return TaskBuilder
         * @see #_case
         * @since 1.2
         */
        public TaskBuilder setCase(boolean _case) {
            this._case = _case;
            return this;
        }

        /**
         * If captcha only contains number, it's a numeric captcha.
         * @see #numeric
         * @param numeric numeric
         * @return TaskBuilder
         * @since 1.2
         */
        public TaskBuilder isNumeric(boolean numeric) {
            if (numeric) this.numeric = 1;
            return this;
        }

        /**
         * @param math Contains math or not
         * @return TaskBuilder}
         * @see #math
         * @since 1.2
         */
        public TaskBuilder setMath(boolean math) {
            this.math = math;
            return this;
        }
    }
}
