package io.github.alperensert.capmonster_java.tasks;

import io.github.alperensert.capmonster_java.exceptions.CapmonsterException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class FunCaptchaTaskTest extends BaseTaskTest {
    private final FunCaptchaTask funCaptchaTask = new FunCaptchaTask(System.getenv("CAPMONSTER_API_KEY"));

    @Test
    void createTask() {
        FunCaptchaTask.TaskBuilder taskBuilder = new FunCaptchaTask.TaskBuilder(
                "https://funcaptcha.com/fc/api/nojs/?pkey=69A21A01-CC7B-B9C6-0F9A-E7FA06677FFC",
                "69A21A01-CC7B-B9C6-0F9A-E7FA06677FFC")
                .setCookies(cookies)
                .setUserAgent(userAgent);
        taskId = funCaptchaTask.createTask(taskBuilder);
    }

    @Test
    void createTaskProxy() {
        FunCaptchaTask.TaskBuilder taskBuilder = new FunCaptchaTask.TaskBuilder(
                "https://funcaptcha.com/fc/api/nojs/?pkey=69A21A01-CC7B-B9C6-0F9A-E7FA06677FFC",
                "69A21A01-CC7B-B9C6-0F9A-E7FA06677FFC")
                .setCookies(cookies)
                .setProxy(proxy)
                .setUserAgent(userAgent);
        taskId = funCaptchaTask.createTask(taskBuilder);
    }

    @AfterEach
    void afterEach() throws InterruptedException {
        JSONObject task;
        try {
            task = funCaptchaTask.joinTaskResult(taskId);
        } catch (CapmonsterException e) {
            if (Arrays.asList(acceptable_errors).contains(e.CODE)) return;
            else throw e;
        }

        if (!task.has("token")) {
            throw new RuntimeException();
        }
    }
}