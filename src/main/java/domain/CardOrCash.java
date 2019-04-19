package domain;

import view.InputView;

import java.util.InputMismatchException;

public class CardOrCash {
    public static final int CARD = 1;
    public static final int CASH = 2;
    private int payingMethod;

    public CardOrCash() {

    }

    public int getPayingMethod() {
        return payingMethod;
    }

    /*
     * 카드 or 현금 선택
     */
    public void choice() {
        try {
            payingMethod = InputView.inputCardOrCash();
            handleWrongBoundary(payingMethod);
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다. 예약을 처음부터 다시 진행해주세요.");
            System.exit(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 범위의 값입니다. 다시 입력해주세요.");
            choice();
        }
    }

    /*
     * 잘못된 입력 범위 처리
     */
    public void handleWrongBoundary(int payingMethod) {
        if (!(payingMethod == CARD || payingMethod == CASH)) {
            throw new IllegalArgumentException();
        }
    }
}
