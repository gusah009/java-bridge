package bridge;

import static bridge.domain.MoveDirection.DOWN;
import static bridge.domain.MoveDirection.UP;

import java.util.ArrayList;
import java.util.List;

public class BridgeMaker {

    private static final BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();

    public List<String> makeBridge(int size) {
        checkBridgeSize(size);
        List<String> newBridge = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            newBridge.add(getPossibleDirection());
        }
        return newBridge;
    }

    private void checkBridgeSize(int size) {
        if (size < 3 || size > 20) {
            throw new IllegalArgumentException("다리 길이는 3부터 20 사이의 숫자여야 합니다.");
        }
    }

    private String getPossibleDirection() {
        if (bridgeNumberGenerator.generate() == 1) {
            return UP.getDirection();
        }
        return DOWN.getDirection();
    }
}
