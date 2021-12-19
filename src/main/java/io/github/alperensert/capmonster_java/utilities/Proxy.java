package io.github.alperensert.capmonster_java.utilities;


/**
 * Proxy class helps you easily create proxy credentials and use it in tasks.
 * @since 1.2
 */
public class Proxy {
    /**
     * Proxy's type. Can be one of them below:
     * <ul>
     *     <li><b>http</b> - usual http/https proxy</li>
     *     <li><b>https</b> - try this only if "http" doesn't work (required by some custom proxy servers)</li>
     *     <li><b>socks4</b> - socks4 proxy</li>
     *     <li><b>socks5</b> - socks5 proxy</li>
     * </ul>
     * @since 1.2
     */
    String type;
    /**
     * Proxy's IP address. Can be IPv4 or IPv6. Not allowed to use:
     * <ul>
     *     <li>host names instead of IPs</li>
     *     <li>transparent proxies (where client IP is visible)</li>
     *     <li>proxies from local networks (192.., 10.., 127...)</li>
     * </ul>
     */
    String address;
    /**
     * Proxy's port
     */
    int port;
    /**
     * Login for proxy which requires authorization (basic)
     */
    String username;
    /**
     * Proxy's password
     */
    String password;

    /**
     * Create a proxy class without basic authentication (username and password). <br><br>
     * <h2>Attention!</h2>
     * If the proxy is authorized by IP, then be sure to add 116.203.55.208 to the white list.
     * @param type Proxy type
     * @param address Proxy address
     * @param port Proxy port
     * @see #type
     * @see #address
     * @see #port
     * @since 1.2
     */
    public Proxy(String type, String address, int port) {
        this.type = type;
        this.address = address;
        this.port = port;
    }

    /**
     * Create a proxy class with basic authentication (username and password)
     * @param type Proxy type
     * @param address Proxy address
     * @param port Proxy port
     * @param username Proxy login username
     * @param password Proxy password
     * @see #type
     * @see #address
     * @see #port
     * @see #username
     * @see #password
     * @since 1.2
     */
    public Proxy(String type, String address, int port, String username, String password) {
        this.type = type;
        this.address = address;
        this.port = port;
        this.username = username;
        this.password = password;
    }
}
