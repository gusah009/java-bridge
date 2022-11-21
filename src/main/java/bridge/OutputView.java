package bridge;

import bridge.domain.MoveDirection;
import java.util.Objects;

public class OutputView {

    private final static String MOVE_MARKER = "O";
    private final static String WRONG_MOVE_MARKER = "X";
    private final static String NO_MOVE_MARKER = " ";
    private final static String MARKER_DELIMITER = " | ";
    private final static String START_LINE = "[ ";
    private final static String END_LINE = " ]";
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
        printCells(bridgeGame, moveDirection);
        printEndLine();
    }

    private static void printStartLine() {
        System.out.print(START_LINE);
    }

    private static void printEndLine() {
        System.out.println(END_LINE);
    }

    private static void printCells(BridgeGame bridgeGame, MoveDirection moveDirection) {
        int currentPosition = bridgeGame.getPlayer().getCurrentPosition() - 1;
        for (int i = 0; i < currentPosition; ++i) {
            printPreviousCell(getActual(bridgeGame, i), moveDirection);
            printDelimiter();
        }
        printCurrentCell(getPlayerChoice(bridgeGame), getActual(bridgeGame, currentPosition), moveDirection);
    }

    private static String getPlayerChoice(BridgeGame bridgeGame) {
        return bridgeGame.getPlayer()
                .getLastMovementChoice()
                .get()
                .getDirection();
    }

    private static String getActual(BridgeGame bridgeGame, int i) {
        return bridgeGame.getBridge().get(i);
    }

    private static void printDelimiter() {
        System.out.print(MARKER_DELIMITER);
    }

    private static void printPreviousCell(String actual, MoveDirection moveDirection) {
        if (Objects.equals(actual, moveDirection.getDirection())) {
            System.out.print(MOVE_MARKER);
            return;
        }
        System.out.print(NO_MOVE_MARKER);
    }

    private static void printCurrentCell(String playerChoice, String actual, MoveDirection moveDirection) {
        if (Objects.equals(playerChoice, moveDirection.getDirection())) {
            if (!Objects.equals(playerChoice, actual)) {
                System.out.print(WRONG_MOVE_MARKER);
                return;
            }
            System.out.print(MOVE_MARKER);
            return;
        }
        System.out.print(NO_MOVE_MARKER);
    }

    private static void printEmptyLine() {
        System.out.println();
    }

    public void printResult(BridgeGame bridgeGame) {
        System.out.println("최종 게임 결과");
        this.printMap(bridgeGame);
        System.out.println("게임 성공 여부: " + getGameEndStatus(bridgeGame.isEnd()));
        System.out.println("총 시도한 횟수: " + bridgeGame.getPlayer().getTryCount());
    }

    private String getGameEndStatus(boolean isEnd) {
        if (isEnd) {
            return "성공";
        }
        return "실패";
    }

    public static void printGuideMessage(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(String message) {
        System.out.println(ERROR_PREFIX + message);
        printEmptyLine();
    }
}
