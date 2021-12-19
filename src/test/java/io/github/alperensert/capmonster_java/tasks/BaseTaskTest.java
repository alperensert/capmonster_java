package io.github.alperensert.capmonster_java.tasks;

import io.github.alperensert.capmonster_java.utilities.Cookies;
import io.github.alperensert.capmonster_java.utilities.Proxy;
import io.github.alperensert.capmonster_java.utilities.UserAgent;

public abstract class BaseTaskTest {
    final Cookies cookies = new Cookies("cookiename1=cookievalue1;cookiename2=cookievalue2");
    final UserAgent userAgent = new UserAgent(System.getenv("USER_AGENT"));
    final String[] proxyString = System.getenv("PROXY").split(",", -1);
    final Proxy proxy = new Proxy(proxyString[0], proxyString[1], Integer.parseInt(proxyString[2]),
            proxyString[3], proxyString[4]);
    int taskId;
    final String[] acceptable_errors = new String[]{"ERROR_CAPTCHA_UNSOLVABLE", "ERROR_MAXIMUM_TIME_EXCEED",
            "ERROR_NO_SLOT_AVAILABLE"};
}
