import domain.Movie;
import domain.MovieRepository;
import domain.ReservationInfo;
import view.InputView;
import view.OutputView;

import java.util.InputMismatchException;
import java.util.List;

public class MovieApplication {
    private static ReservationInfo reservationInfo;

    public static void main(String[] args) {
        List<Movie> movies = MovieRepository.getMovies();
        reservationInfo = new ReservationInfo();

        reserveUntilFine(movies);
        OutputView.printReservation(reservationInfo);
        int point = InputView.inputPoint();
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

}
