package domain;

import view.InputView;

import java.util.InputMismatchException;

public class Point {
    private int pointNum;

    public Point() {

    }

    public int getPointNum() {
        return pointNum;
    }

    /*
     * 예약 완료 후 포인트 사용할 지 여부
     */
    public void usePoint() {
        try {
            pointNum = InputView.inputPoint();
            handleUnsignedPoint(pointNum);
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다. 예약을 처음부터 다시 진행해주세요.\n");
            System.exit(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("음수를 입력하셨습니다. 다시 입력해주세요.\n");
            usePoint();
        }
    }

    /*
     * 포인트가 음수시 처리
     */
    public void handleUnsignedPoint(int point) {
        if (point < 0) {
            throw new IllegalArgumentException();
        }
    }
}
