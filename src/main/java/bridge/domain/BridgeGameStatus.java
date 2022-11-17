package bridge.domain;

import java.util.Objects;

public enum BridgeGameStatus {
    RESTART("R"),
    QUIT("Q");

    final String status;

    BridgeGameStatus(String status) {
        this.status = status;
    }

    public boolean isMatch(String inputStatus) {
        return Objects.equals(this.status, inputStatus);
    }

    public String getStatus() {
        return status;
    }
}
