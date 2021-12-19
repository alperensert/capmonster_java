package io.github.alperensert.capmonster_java.utilities;

/**
 * User Agent class helps you easily create user agent property and use it in tasks
 * @see <a href="https://en.wikipedia.org/wiki/User_agent">What is an user agent?</a>
 * @since 1.2
 */
public class UserAgent {
    /**
     * User agent holder
     */
    private final String userAgent;

    /**
     * Create user agent class to easily use in tasks
     * @param userAgent User Agent
     */
    public UserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * Get user agent
     * @return User Agent
     * @since 1.2
     */
    public String get() { return userAgent; }
}