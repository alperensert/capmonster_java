package io.github.alperensert.capmonster_java.tasks;

import io.github.alperensert.capmonster_java.exceptions.CapmonsterException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class RecaptchaV2TaskTest extends BaseTaskTest {
    private final RecaptchaV2Task recaptchaV2Task = new RecaptchaV2Task(System.getenv("CAPMONSTER_API_KEY"));

    @Test
    void createTask() {
        RecaptchaV2Task.TaskBuilder taskBuilder = new RecaptchaV2Task.TaskBuilder(
                "https://lessons.zennolab.com/captchas/recaptcha/v2_simple.php?level=high",
                "6Lcg7CMUAAAAANphynKgn9YAgA4tQ2KI_iqRyTwd")
                .setUserAgent(userAgent)
                .setCache(true)
                .setCookies(cookies);
        taskId = recaptchaV2Task.createTask(taskBuilder);
    }

    @Test
    void createTaskProxy() {
        RecaptchaV2Task.TaskBuilder taskBuilder = new RecaptchaV2Task.TaskBuilder(
                "https://lessons.zennolab.com/captchas/recaptcha/v2_simple.php?level=high",
                "6Lcg7CMUAAAAANphynKgn9YAgA4tQ2KI_iqRyTwd")
                .setUserAgent(userAgent)
                .setCache(true)
                .setCookies(cookies)
                .setProxy(proxy);
        taskId = recaptchaV2Task.createTask(taskBuilder);
    }

    @AfterEach
    void afterEach() throws InterruptedException {
        JSONObject task;
        try {
            task = recaptchaV2Task.joinTaskResult(taskId);
        } catch (CapmonsterException e) {
            if (Arrays.asList(acceptable_errors).contains(e.CODE)) return;
            else throw e;
        }

        if (!task.has("gRecaptchaResponse") ) {
            throw new RuntimeException();
        }
    }
}