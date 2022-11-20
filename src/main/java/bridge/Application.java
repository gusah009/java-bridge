
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
        BridgeGame bridgeGame = new BridgeGame(new BridgeMaker(new BridgeRandomNumberGenerator()),
                inputView.readBridgeSize());
        do {
            moveUntilPossible(bridgeGame);
            if (bridgeGame.isEnd()) {
                break;
            }
        } while (bridgeGame.retry(inputView.readGameCommand()));
        outputView.printResult(bridgeGame);
    }

    private static void moveUntilPossible(BridgeGame bridgeGame) {
        while (bridgeGame.move(inputView.readMoving())) {
            outputView.printMap(bridgeGame);
        }
        outputView.printMap(bridgeGame);
    }
}
