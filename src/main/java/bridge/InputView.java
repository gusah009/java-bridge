package bridge;

import bridge.domain.BridgeGameStatus;
import bridge.domain.MoveDirection;
import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;

public class InputView {

    public InputView() {
    }

    public int readBridgeSize() {
        OutputView.printGuideMessage("다리의 길이를 입력해주세요.");
        String playerInput = Console.readLine();

        try {
            return Integer.parseInt(playerInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("다리 길이는 숫자여야 합니다.", e);
        }
    }

    public MoveDirection readMoving() {
        OutputView.printGuideMessage("이동할 칸을 선택해주세요. (위: U, 아래: D)");
        String playerInput = Console.readLine();
        this.validateMoveDirection(playerInput);
        return getMoveDirection(playerInput);
    }

    private void validateMoveDirection(String playerInput) {
        if (isInvalidDirection(playerInput)) {
            throw new IllegalArgumentException("이동 방향이 올바르지 않습니다. 사용자의 입력: " + playerInput);
        }
    }

    private static boolean isInvalidDirection(String playerInput) {
        return Arrays.stream(MoveDirection.values())
                .noneMatch((moveDirection) -> moveDirection.isMatch(playerInput));
    }

    private static MoveDirection getMoveDirection(String playerInput) {
        return Arrays.stream(MoveDirection.values())
                .filter((direction) -> direction.isMatch(playerInput))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public BridgeGameStatus readGameCommand() {
        OutputView.printGuideMessage("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
        String playerInput = Console.readLine();
        return getGameStatus(playerInput);
    }

    private static boolean isValidStatus(String playerInput) {
        return Arrays.stream(BridgeGameStatus.values())
                .noneMatch((gameStatus) -> gameStatus.isMatch(playerInput));
    }

    private static BridgeGameStatus getGameStatus(String playerInput) {
        return Arrays.stream(BridgeGameStatus.values())
                .filter((gameStatus) -> gameStatus.isMatch(playerInput))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("게임의 재시도 여부가 올바르지 않습니다. 사용자의 입력: " + playerInput));
    }
}
