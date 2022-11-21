
package bridge;

public class Application {

    static private final InputView inputView = new InputView();
    static private final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        try {
            playBridgeGame();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private static void playBridgeGame() {
        BridgeGame bridgeGame = new BridgeGame(new BridgeMaker(new BridgeRandomNumberGenerator()), getBridgeSize());
        do {
            moveUntilPossible(bridgeGame);
            if (bridgeGame.isEnd()) {
                break;
            }
        } while (isPlayerSelectRetry(bridgeGame));
        outputView.printResult(bridgeGame);
    }

    private static int getBridgeSize() {
        try {
            return inputView.readBridgeSize();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getBridgeSize();
        }
    }

    private static boolean isPlayerSelectRetry(BridgeGame bridgeGame) {
        try {
            return bridgeGame.retry(inputView.readGameCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return isPlayerSelectRetry(bridgeGame);
        }
    }

    private static void moveUntilPossible(BridgeGame bridgeGame) {
        while (tryInputMoving(bridgeGame)) {
            outputView.printMap(bridgeGame);
        }
        outputView.printMap(bridgeGame);
    }

    private static boolean tryInputMoving(BridgeGame bridgeGame) {
        try {
            return bridgeGame.move(inputView.readMoving());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return tryInputMoving(bridgeGame);
        }
    }
}
