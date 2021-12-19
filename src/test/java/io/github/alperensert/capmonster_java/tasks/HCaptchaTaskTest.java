package io.github.alperensert.capmonster_java.tasks;

import io.github.alperensert.capmonster_java.exceptions.CapmonsterException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

class HCaptchaTaskTest extends BaseTaskTest {
    private final HCaptchaTask hCaptchaTask = new HCaptchaTask(System.getenv("CAPMONSTER_API_KEY"));

    @Test
    void createTask() {
        HCaptchaTask.TaskBuilder taskBuilder = new HCaptchaTask.TaskBuilder(
                "https://lessons.zennolab.com/captchas/hcaptcha/?level=easy",
                "472fc7af-86a4-4382-9a49-ca9090474471")
                .setCache(true)
                .setCookies(cookies)
                .setUserAgent(userAgent);
        taskId = hCaptchaTask.createTask(taskBuilder);
    }

    @Test
    void createTaskProxy() {
        HCaptchaTask.TaskBuilder taskBuilder = new HCaptchaTask.TaskBuilder(
                "https://lessons.zennolab.com/captchas/hcaptcha/?level=easy",
                "472fc7af-86a4-4382-9a49-ca9090474471")
                .setCache(true)
                .setCookies(cookies)
                .setProxy(proxy)
                .setUserAgent(userAgent);
        taskId = hCaptchaTask.createTask(taskBuilder);
    }

    @Test
    void createTaskInvisible() {
        HCaptchaTask.TaskBuilder taskBuilder = new HCaptchaTask.TaskBuilder(
                "https://lessons.zennolab.com/captchas/hcaptcha/invisible.php?level=moderate",
                "d391ffb1-bc91-4ef8-a45a-2e2213af091b")
                .setVisibility(true)
                .setCache(true)
                .setCookies(cookies)
                .setProxy(proxy)
                .setUserAgent(userAgent);
        taskId = hCaptchaTask.createTask(taskBuilder);
    }

    @AfterEach
    void afterEach() throws InterruptedException {
        JSONObject task;
        try {
            task = hCaptchaTask.joinTaskResult(taskId);
        } catch (CapmonsterException e) {
            if (Arrays.asList(acceptable_errors).contains(e.CODE)) return;
            else throw e;
        }

        if (!task.has("gRecaptchaResponse")) {
            throw new RuntimeException();
        }
    }

}