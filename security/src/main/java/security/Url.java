package security;

/**
 * Keeps Url which gives access to authentication and authorization.
 *
 * @author Sorokin Georgy
 */
public final class Url {

    public static final String LOGIN = "/auth/login";

    public static final String REFRESH = "/auth/refresh";

    public static final String PUBLIC = "/public";

    private Url() {
    }

}
