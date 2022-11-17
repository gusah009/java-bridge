
package bridge;

public class Application {

    public static void main(String[] args) {
        try {
            playBridgeGame();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }

    }

    private static void playBridgeGame() {
        BridgeGame bridgeGame = new BridgeGame(new BridgeMaker(new BridgeRandomNumberGenerator()));
        do {
            while (bridgeGame.move())
                ;
        } while (bridgeGame.retry());

    }
}
