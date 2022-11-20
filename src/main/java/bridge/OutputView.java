package bridge;

import bridge.domain.MoveDirection;
import bridge.domain.Player;
import java.util.List;
import java.util.Objects;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";

    public OutputView() {
    }

    public void printMap(BridgeGame bridgeGame) {
        printBridgeLine(bridgeGame, MoveDirection.UP);
        printBridgeLine(bridgeGame, MoveDirection.DOWN);
        printEmptyLine();
    }

    private static void printBridgeLine(BridgeGame bridgeGame, MoveDirection moveDirection) {
        printStartLine();
        printPreviousCells(bridgeGame, moveDirection);
        printCurrentCell(bridgeGame, moveDirection);
        printEndLine();
    }

    private static void printStartLine() {
        System.out.print("[");
    }

    private static void printEndLine() {
        System.out.println("]");
    }

    private static void printPreviousCells(BridgeGame bridgeGame, MoveDirection moveDirection) {
        for (int i = 0; i < bridgeGame.getPlayer().getCurrentPosition() - 1; ++i) {
            printPreviousCell(bridgeGame.getBridge(), moveDirection, i);
        }

    }

    private static void printPreviousCell(List<String> bridge, MoveDirection moveDirection, int printPosition) {
        if (Objects.equals(bridge.get(printPosition), moveDirection.getDirection())) {
            System.out.print(" O |");
            return;
        }
        System.out.print("   |");
    }

    private static void printCurrentCell(BridgeGame bridgeGame, MoveDirection moveDirection) {
        if (bridgeGame.getPlayer().getLastMovementChoice() == moveDirection) {
            if (Objects.equals(bridgeGame.getBridge().get(bridgeGame.getPlayer().getCurrentPosition() - 1),
                    moveDirection.getDirection())) {
                System.out.print(" O ");
                return;
            }
            System.out.print(" X ");
            return;
        }
        System.out.print("   ");
    }

    private static void printEmptyLine() {
        System.out.println();
    }

    public void printResult(BridgeGame bridgeGame) {
        System.out.println("최종 게임 결과");
        this.printMap(bridgeGame);
        System.out.println("게임 성공 여부: " + getGameEndStatus(bridgeGame.getBridge(), bridgeGame.getPlayer()));
        System.out.println("총 시도한 횟수: " + bridgeGame.getPlayer().getTryCount());
    }

    private String getGameEndStatus(List<String> bridge, Player player) {
        return this.isEndOfBridge(bridge, player) && this.isPlayerCorrectSelected(bridge, player) ? "성공" : "실패";
    }

    private boolean isPlayerCorrectSelected(List<String> bridge, Player player) {
        String actualBridge = bridge.get(player.getCurrentPosition() - 1);
        String playerSelected = player.getLastMovementChoice().getDirection();
        return Objects.equals(actualBridge, playerSelected);
    }

    private boolean isEndOfBridge(List<String> bridge, Player player) {
        return player.getCurrentPosition() == bridge.size();
    }

    public static void printGuideMessage(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(String message) {
        System.out.println(ERROR_PREFIX + message);
    }
}
