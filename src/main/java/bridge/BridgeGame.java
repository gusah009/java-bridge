package bridge;

import bridge.domain.BridgeGameStatus;
import bridge.domain.MoveDirection;
import bridge.domain.Player;
import java.util.List;
import java.util.Objects;

public class BridgeGame {

    private final List<String> bridge;
    private final Player player;

    private boolean isEnd = false;

    public BridgeGame(BridgeMaker bridgeMaker, int bridgeSize) {
        this.bridge = bridgeMaker.makeBridge(bridgeSize);
        this.player = new Player();
        OutputView.printGuideMessage("다리 건너기 게임을 시작합니다.");
    }

    public boolean move(MoveDirection inputDirection) {
        player.setLastMovementChoice(inputDirection);
        player.moveForward();
        if (isEndOfBridge() && isPlayerCorrectSelected()) {
            isEnd = true;
        }
        return canMove();
    }

    private boolean canMove() {
        return !isEndOfBridge() && isPlayerCorrectSelected();
    }

    private boolean isPlayerCorrectSelected() {
        String actualBridge = bridge.get(player.getCurrentPosition() - 1);
        String playerSelected = getPlayerSelected();
        return Objects.equals(actualBridge, playerSelected);
    }

    private String getPlayerSelected() {
        if (player.getLastMovementChoice().isPresent()) {
            return player.getLastMovementChoice()
                    .get()
                    .getDirection();
        }
        return "";
    }

    private boolean isEndOfBridge() {
        return player.getCurrentPosition() == bridge.size();
    }

    public boolean retry(BridgeGameStatus playerInputCommand) {
        if (playerInputCommand == BridgeGameStatus.RESTART) {
            player.plusRetryCount();
            player.initCurrentPosition();
            return true;
        }
        return false;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public List<String> getBridge() {
        return bridge;
    }

    public Player getPlayer() {
        return player;
    }
}
