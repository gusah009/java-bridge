package bridge.domain;

public enum MoveDirection {
    UP("U"),
    DOWN("D");

    final String direction;

    MoveDirection(String direction) {
        this.direction = direction;
    }

    public boolean isMatch(String otherDirection) {
        return this.direction.equals(otherDirection);
    }

    public String getDirection() {
        return direction;
    }
}
