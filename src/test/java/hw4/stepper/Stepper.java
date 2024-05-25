package hw4.stepper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kzlv4natoly
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Stepper {
    public enum Side {
        LEFT, RIGHT
    }

    private final List<Side> history = new ArrayList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition leftTurnCondition = lock.newCondition();
    private final Condition rightTurnCondition = lock.newCondition();
    private boolean isLeftTurn = true;

    public void leftStep() throws InterruptedException {
        lock.lock();
        try {
            while (!isLeftTurn) {
                leftTurnCondition.await();
            }
            history.add(Side.LEFT);
            isLeftTurn = false;
            rightTurnCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void rightStep() throws InterruptedException {
        lock.lock();
        try {
            while (isLeftTurn) {
                rightTurnCondition.await();
            }
            history.add(Side.RIGHT);
            isLeftTurn = true;
            leftTurnCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public List<Side> getHistory() {
        return history;
    }
}
