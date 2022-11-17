
package bridge.domain;

public class Player {

    private int currentPosition = 0;
    private int tryCount = 1;
    private MoveDirection lastMovementChoice;

    public Player() {
        this.lastMovementChoice = MoveDirection.START;
    }

    public void moveForward() {
        ++this.currentPosition;
    }

    public void plusRetryCount() {
        ++this.tryCount;
    }

    public void setLastMovementChoice(MoveDirection moveDirection) {
        this.lastMovementChoice = moveDirection;
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }

    public int getTryCount() {
        return this.tryCount;
    }

    public MoveDirection getLastMovementChoice() {
        return this.lastMovementChoice;
    }

    public void initCurrentPosition() {
        this.currentPosition = 0;
    }
}
