Capmonster.cloud for Java
-
![GitHub repo size](https://img.shields.io/github/repo-size/alperensert/capmonster_java) ![GitHub](https://img.shields.io/github/license/alperensert/capmonster_java) ![Maven Central](https://img.shields.io/maven-central/v/io.github.alperensert/capmonster_java) ![Sonatype Nexus (Releases)](https://img.shields.io/nexus/r/io.github.alperensert/capmonster_java?server=https%3A%2F%2Fs01.oss.sonatype.org) ![GitHub last commit](https://img.shields.io/github/last-commit/alperensert/capmonster_java) ![GitHub Release Date](https://img.shields.io/github/release-date/alperensert/capmonster_java) ![GitHub Repo stars](https://img.shields.io/github/stars/alperensert/capmonster_java?style=social)

[Capmonster.cloud](https://capmonster.cloud) package for Java

If you have any problem with usage, [read the documentation](https://github.com/alperensert/capmonster_java/wiki) or [create an issue](https://github.com/alperensert/capmonster_java/issues/new)

*At least 2x cheaper, up to 30x faster than manual recognition services.*

### Installation
- Maven
    ```xml
    <dependency>
        <groupId>io.github.alperensert</groupId>
        <artifactId>capmonster_java</artifactId>
        <version>1.3</version>
    </dependency>
    ```
- Gradle
    ```groovy
    implementation group: 'io.github.alperensert', name: 'capmonster_java', version: '1.3'
    ```

### Supported captcha types
- Image to text
- Recaptcha v2
- Recaptcha v3
- Fun Captcha
- HCaptcha
- GeeTest

Usage Examples
-

#### Image to text
```java
import io.github.alperensert.capmonster_java.tasks.ImageToTextTask;
import org.json.JSONObject;

public class Capmonster {
    public static void main(String[] args) throws InterruptedException {
        ImageToTextTask imageToTextTask = new ImageToTextTask("API_KEY");
        ImageToTextTask.TaskBuilder taskBuilder = new ImageToTextTask.TaskBuilder(
                "BASE_64_ENCODED_IMAGE")
                .setModuleName("universal");
        int taskId = imageToTextTask.createTask(taskBuilder);
        JSONObject result = imageToTextTask.joinTaskResult(taskId);
        System.out.println(result);
    }
}

```

#### Recaptcha v2
```java
import io.github.alperensert.capmonster_java.tasks.RecaptchaV2Task;
import io.github.alperensert.capmonster_java.utilities.UserAgent;
import org.json.JSONObject;

public class Capmonster {
    public static void main(String[] args) throws InterruptedException {
        UserAgent userAgent = new UserAgent("some user agent");
        RecaptchaV2Task recaptchaV2Task = new RecaptchaV2Task("API_KEY");
        RecaptchaV2Task.TaskBuilder taskBuilder = new RecaptchaV2Task.TaskBuilder(
                "WEBSITE_URL",
                "WEBSITE_KEY")
                .setUserAgent(userAgent)
                .setCache(true);
        int taskId = recaptchaV2Task.createTask(taskBuilder);
        JSONObject result = recaptchaV2Task.joinTaskResult(taskId);
        System.out.println(result);
    }
}
```

For other examples and api documentation please visit [wiki](https://github.com/alperensert/capmonster_java/wiki)