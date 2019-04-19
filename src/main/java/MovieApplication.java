import domain.*;
import view.InputView;
import view.OutputView;

import static domain.CardOrCash.CARD;
import static domain.CardOrCash.CASH;

import java.util.InputMismatchException;
import java.util.List;

public class MovieApplication {
    public static final double CARD_DISCOUNT = 0.95;
    public static final double CASH_DISCOUNT = 0.98;
    private static int check;
    private static ReservationInfo reservationInfo;
    private static Point point;
    private static CardOrCash cardOrCash;

    /*
     * 예약 및 결제의 과정 진행 (main 메소드)
     */
    public static void main(String[] args) {
        List<Movie> movies = MovieRepository.getMovies();
        reservationInfo = new ReservationInfo();

        reserveUntilFine(movies);
        OutputView.printReservation(reservationInfo);
        int pointNum = givePoint();
        int payingMethod = choiceResult();
        int payment = finalPayment(pointNum, payingMethod);
        OutputView.printFinalPayment(payment);
    }

    /*
     * 포인트 사용 관련 처리
     */
    public static int givePoint() {
        point = new Point();
        point.usePoint();
        return point.getPointNum();
    }

    public static int choiceResult() {
        cardOrCash = new CardOrCash();
        cardOrCash.choice();
        return cardOrCash.getPayingMethod();
    }

    /*
     * 영화와 시간, 인원을 선택하는 과정
     */
    public static void reservationProgress(List<Movie> movies) {
        OutputView.printMovies(movies);
        int movieId = InputView.inputMovieId();
        OutputView.printChosenMovie(movies, movieId);
        reservationInfo.addChosenMovice(movies, movieId);
        int movieTime = InputView.inputMovieSchedule();
        reservationInfo.addChosenTime(movieTime);
        int moviePeople = InputView.inputMovieReservePerson();
        reservationInfo.addChosenPeople(movieTime, moviePeople);
    }

    /*
     * 결제 or 추가예약 선택
     */
    public static void reserverOrPurchase(List<Movie> movies) {
        while (check != 1) {
            reservationProgress(movies);
            checkReserveOrPurchaseInput();
        }
    }

    public static void checkReserveOrPurchaseInput() {
        check = InputView.inputReserveOrPurchase();
        if (!(check == 1 || check == 2)) {
            checkReserveOrPurchaseInput();
        }
    }

    /*
     * 오류없이 예약을 끝낼 때까지 반복
     */
    public static void reserveUntilFine(List<Movie> movies) {
        try {
            reserverOrPurchase(movies);
        } catch (IllegalArgumentException e) {
            handleIllegalAraument(movies);
        } catch (InputMismatchException e) {
            handleInputMismatch();
        } catch (IndexOutOfBoundsException e) {
            handleIndexOutOfBounds(movies);
        }
    }

    /*
     * Illegal argument exception 처리
     */
    public static void handleIllegalAraument(List<Movie> movies) {
        reservationInfo.recoveryCancelledPart();
        System.out.println("취소된 예약을 다시 진행해주세요.\n");
        reserveUntilFine(movies);
    }

    /*
     * Input mismatch exception 처리
     */
    public static void handleInputMismatch() {
        reservationInfo.recoveryCancelledPart();
        System.out.println("입력값이 잘못됐습니다 예약을 처음부터 다시 진행해주세요.\n");
        System.exit(-1);
    }

    /*
     * Index out of bounds exception 처리
     */
    public static void handleIndexOutOfBounds(List<Movie> movies) {
        reservationInfo.recoveryCancelledPart();
        System.out.println("입력 범위를 벗어났습니다. 취소된 예약을 다시 진행해주세요.\n");
        reserveUntilFine(movies);
    }

    /*
     * 최종 지불할 금액 계산
     */
    public static int finalPayment(int pointNum, int payingMethod) {
        double money = reservationInfo.howMuch();
        money -= pointNum;
        if (payingMethod == CARD) {
            money = money * CARD_DISCOUNT;
        }
        if (payingMethod == CASH) {
            money = money * CASH_DISCOUNT;
        }
        return (int) money;
    }
    // TODO 컨벤션 및 프로그래밍 요구사항에 맞게 수정
}
