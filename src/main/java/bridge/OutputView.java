package bridge;

import bridge.domain.MoveDirection;
import bridge.domain.Player;
import java.util.List;
import java.util.Objects;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";

    public OutputView() {
    }

    public void printMap(List<String> bridge, Player player) {
        printBridgeLine(bridge, player, MoveDirection.UP);
        printBridgeLine(bridge, player, MoveDirection.DOWN);
    }

    private static void printBridgeLine(List<String> bridge, Player player, MoveDirection moveDirection) {
        printStartLine();
        printPreviousCells(bridge, player, moveDirection);
        printCurrentCell(bridge, player, moveDirection);
        printEndLine();
    }

    private static void printStartLine() {
        System.out.print("[");
    }

    private static void printEndLine() {
        System.out.println("]");
    }

    private static void printPreviousCells(List<String> bridge, Player player, MoveDirection moveDirection) {
        for (int i = 0; i < player.getCurrentPosition(); ++i) {
            printPreviousCell(bridge, moveDirection, i);
        }

    }

    private static void printPreviousCell(List<String> bridge, MoveDirection moveDirection, int printPosition) {
        if (Objects.equals(bridge.get(printPosition), moveDirection.getDirection())) {
            System.out.print(" O |");
            return;
        }
        System.out.print("   |");
    }

    private static void printCurrentCell(List<String> bridge, Player player, MoveDirection moveDirection) {
        if (player.getLastMovementChoice() == moveDirection) {
            if (Objects.equals(bridge.get(player.getCurrentPosition()), moveDirection.getDirection())) {
                System.out.print(" O ");
                return;
            }
            System.out.print(" X ");
            return;
        }
        System.out.print("   ");
    }

    public void printResult(List<String> bridge, Player player) {
        System.out.println("최종 게임 결과");
        this.printMap(bridge, player);
        System.out.println("게임 성공 여부: " + getGameEndStatus(bridge, player));
        System.out.println("총 시도한 횟수: " + player.getTryCount());
    }

    private String getGameEndStatus(List<String> bridge, Player player) {
        return this.isEndOfBridge(bridge, player) && this.isPlayerCorrectSelected(bridge, player) ? "성공" : "실패";
    }

    private boolean isPlayerCorrectSelected(List<String> bridge, Player player) {
        String actualBridge = bridge.get(player.getCurrentPosition());
        String playerSelected = player.getLastMovementChoice().getDirection();
        return Objects.equals(actualBridge, playerSelected);
    }

    private boolean isEndOfBridge(List<String> bridge, Player player) {
        return player.getCurrentPosition() == bridge.size() - 1;
    }

    public static void printGuideMessage(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(String message) {
        System.out.println(ERROR_PREFIX + message);
    }
}
