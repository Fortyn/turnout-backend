package app.model;

/**
 * @author Georgy Sorokin
 */
public class TurnoutResponse {

    private final TurnoutRegistrationStatus status;

    public TurnoutResponse(TurnoutRegistrationStatus status) {
        this.status = status;
    }

    public TurnoutRegistrationStatus getStatus() {
        return status;
    }
}
