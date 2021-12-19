package io.github.alperensert.capmonster_java.utilities;

import org.json.JSONObject;
import java.io.Serializable;
import java.util.List;

/**
 * Cookies is the right way of creating cookies object for using in Tasks. <br>
 * Basically, Cookies can creatable from JSONObject, Serializable List or
 * plain strings. <br><br>
 * String format must be like this:
 * <pre>
 * {@code
 * String cookies = "cookiename1=cookievalue1;cookiename2=cookievalue2";
 * }
 * </pre>
 * @since 1.2
 */
public class Cookies {
    /**
     * String cookies holder
     */
    private final String cookies;

    /**
     * Create cookies from string <br><br>
     * An string example available in class document, for information see Cookies
     * @see Cookies
     * @param cookies Cookies
     * @since 1.2
     */
    public Cookies(String cookies) {
        this.cookies = cookies;
    }

    /**
     * Create cookies from JSONObject
     * @param cookies Cookies
     * @since 1.2
     */
    public Cookies(JSONObject cookies) {
        String strCookies = "";
        for (String key : cookies.keySet()) {
            String value = (String) cookies.get(key);
            strCookies = strCookies.concat(key + "=" + value + ";");
        }
        this.cookies = strCookies;
    }

    /**
     * Create cookies from Serializable List
     * @param cookies Cookies
     * @since 1.2
     */
    public Cookies(List<? extends Serializable> cookies) {
        if (cookies.size() % 2 != 0) throw new Error("Array cookies length must be even numbers");
        String strCookies = "";
        for (Serializable value : cookies) {
            strCookies = cookies.indexOf(value) % 2 == 0 ? strCookies.concat(value + "=") : strCookies.concat(value + ";");
        }
        this.cookies = strCookies;
    }

    /**
     * Get cookies
     * @return Cookies as string
     * @since 1.2
     */
    public String get() { return this.cookies; }
}
