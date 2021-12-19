package io.github.alperensert.capmonster_java.tasks;

import io.github.alperensert.capmonster_java.exceptions.CapmonsterException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

class RecaptchaV3TaskTest extends BaseTaskTest {
    private final RecaptchaV3Task recaptchaV3Task = new RecaptchaV3Task(System.getenv("CAPMONSTER_API_KEY"));

    @Test
    void createTask() {
        RecaptchaV3Task.TaskBuilder taskBuilder = new RecaptchaV3Task.TaskBuilder(
                "https://lessons.zennolab.com/captchas/recaptcha/v3.php?level=beta",
                "6Le0xVgUAAAAAIt20XEB4rVhYOODgTl00d8juDob")
                .setPageAction("myverify")
                .setMinScore(0.6)
                .setCache(true);
        taskId = recaptchaV3Task.createTask(taskBuilder);
    }

    @AfterEach
    void afterEach() throws InterruptedException {
        JSONObject task;
        try {
            task = recaptchaV3Task.joinTaskResult(taskId);
        } catch (CapmonsterException e) {
            if (Arrays.asList(acceptable_errors).contains(e.CODE)) return;
            else throw e;
        }

        if (!task.has("gRecaptchaResponse") ) {
            throw new RuntimeException();
        }
    }

}