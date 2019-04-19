import domain.Movie;
import domain.MovieRepository;
import domain.ReservationInfo;
import view.InputView;
import view.OutputView;

import java.util.InputMismatchException;
import java.util.List;

public class MovieApplication {
    private static ReservationInfo reservationInfo;
    private static final int CARD = 1;
    private static final int CASH = 2;
    private static final double CARD_DISCOUNT = 0.95;
    private static final double CASH_DISCOUNT = 0.98;

    public static void main(String[] args) {
        List<Movie> movies = MovieRepository.getMovies();
        reservationInfo = new ReservationInfo();

        reserveUntilFine(movies);
        OutputView.printReservation(reservationInfo);
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
            System.out.println(reservationInfo.getChosenMovie()
                    + "\n" + reservationInfo.getChosenTimes() + "\n"
                    + reservationInfo.getChosenPeople());
            check = InputView.inputReserveOrPurchase();
        }
    }

    /*
     * 오류가 안나고 예약을 끝마칠 때까지 반복
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
     * 예약 완료 후 결제 과정 진행
     */
    public static int payPoint() {
        int point = -1;
        try {
            point = InputView.inputPoint();
            if (point < 0) {
                throw new IllegalArgumentException();
            }
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            payPoint();
        } catch (IllegalArgumentException e) {
            System.out.println("음수를 입력하셨습니다. 다시 입력해주세요.");
            payPoint();
        }
        return point;
    }

    /*
     * 최종 지불해야 할 금액 계산
     */
    public static int finalPayment(int cardOrCash) {
        int money = reservationInfo.howMuch();
        money -= payPoint();
    }
}
