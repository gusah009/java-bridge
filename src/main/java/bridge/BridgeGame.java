package bridge;

import bridge.domain.BridgeGameStatus;
import bridge.domain.MoveDirection;
import bridge.domain.Player;
import java.util.List;
import java.util.Objects;

public class BridgeGame {

    private final List<String> bridge;
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final Player player;

    public BridgeGame(BridgeMaker bridgeMaker) {
        int bridgeSize = this.inputView.readBridgeSize();
        this.bridge = bridgeMaker.makeBridge(bridgeSize);
        this.player = new Player();
        OutputView.printGuideMessage("다리 건너기 게임을 시작합니다.");
    }

    public boolean move() {
        MoveDirection inputDirection = this.inputView.readMoving();
        this.player.setLastMovementChoice(inputDirection);
        this.outputView.printMap(this.bridge, this.player);
        if (this.canMove()) {
            this.player.moveForward();
            return true;
        }
        return false;
    }

    private boolean canMove() {
        return this.isPlayerCorrectSelected() && !this.isEndOfBridge();
    }

    private boolean isPlayerCorrectSelected() {
        String actualBridge = this.bridge.get(this.player.getCurrentPosition());
        String playerSelected = this.player.getLastMovementChoice().getDirection();
        return Objects.equals(actualBridge, playerSelected);
    }

    private boolean isEndOfBridge() {
        return this.player.getCurrentPosition() == this.bridge.size() - 1;
    }

    public boolean retry() {
        if (this.isEndOfBridge() && this.isPlayerCorrectSelected()) {
            this.outputView.printResult(this.bridge, this.player);
            return false;
        }
        if (this.inputView.readGameCommand() == BridgeGameStatus.RESTART) {
            this.player.plusRetryCount();
            this.player.initCurrentPosition();
            return true;
        }
        this.outputView.printResult(this.bridge, this.player);
        return false;
    }
}
