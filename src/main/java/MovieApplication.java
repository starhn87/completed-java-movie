import domain.Movie;
import domain.MovieRepository;
import domain.ReservationInfo;
import view.InputView;
import view.OutputView;

import java.util.InputMismatchException;
import java.util.List;

public class MovieApplication {
    private static final int CARD = 1;
    private static final int CASH = 2;
    private static final double CARD_DISCOUNT = 0.95;
    private static final double CASH_DISCOUNT = 0.98;
    private static int payingMethod;
    private static int point;
    private static ReservationInfo reservationInfo;

    /*
     * 예약 및 결제의 과정 진행 (main 메소드)
     */
    public static void main(String[] args) {
        List<Movie> movies = MovieRepository.getMovies();
        reservationInfo = new ReservationInfo();

        reserveUntilFine(movies);
        OutputView.printReservation(reservationInfo);
        payPoint();
        cardOrCash();
        int payment = finalPayment();
        OutputView.printFinalPayment(payment);
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
        int check = 0;
        while (check != 1) {
            reservationProgress(movies);
            check = InputView.inputReserveOrPurchase();
        }
    }

    /*
     * 오류없이 예약을 끝낼 때까지 반복
     */
    public static void reserveUntilFine(List<Movie> movies) {
        try {
            reserverOrPurchase(movies);
        } catch (IllegalArgumentException e) {
            reservationInfo.recoveryCancelledPart();
            System.out.println("취소된 예약을 다시 진행해주세요.\n");
            reserveUntilFine(movies);
        } catch (InputMismatchException e) {
            reservationInfo.recoveryCancelledPart();
            System.out.println("입력값이 잘못됐습니다 예약을 처음부터 다시 진행해주세요.\n");
            System.exit(-1);
        } catch (IndexOutOfBoundsException e) {
            reservationInfo.recoveryCancelledPart();
            System.out.println("입력 범위를 벗어났습니다. 취소된 예약을 다시 진행해주세요.\n");
            reserveUntilFine(movies);
        }
    }

    /*
     * 예약 완료 후 포인트 사용할 지 여부
     */
    public static void payPoint() {
        try {
            point = InputView.inputPoint();
            if (point < 0) {
                throw new IllegalArgumentException();
            }
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다. 예약을 처음부터 다시 진행해주세요.\n");
            System.exit(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("음수를 입력하셨습니다. 다시 입력해주세요.\n");
            payPoint();
        }
    }

    /*
     * 신용카드 or 현금 선택
     */
    public static void cardOrCash() {
        try {
            payingMethod = InputView.inputCardOrCash();
            if (!(payingMethod == CARD || payingMethod == CASH)) {
                throw new IllegalArgumentException();
            }
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다. 예약을 처음부터 다시 진행해주세요.");
            System.exit(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 범위의 값입니다. 다시 입력해주세요.");
            cardOrCash();
        }
    }

    /*
     * 최종 지불할 금액 계산
     */
    public static int finalPayment() {
        double money = reservationInfo.howMuch();
        money -= point;
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
