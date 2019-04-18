import domain.Movie;
import domain.MovieRepository;
import domain.PlaySchedule;
import domain.ReservationInfo;
import view.InputView;
import view.OutputView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovieApplication {
    private static ReservationInfo reservationInfo;

    public static void main(String[] args) {
        List<Movie> movies = MovieRepository.getMovies();
        reservationInfo = new ReservationInfo();
        int check = 0;

        try {
            while (check != 1) {
                OutputView.printMovies(movies);
                int movieId = InputView.inputMovieId();
                OutputView.printChosenMovie(movies, movieId);
                reservationInfo.addChosenMovice(movies, movieId);
                int movieTime = InputView.inputMovieSchedule();
                reservationInfo.addChosenTime(movieTime);
                int moviePeople = InputView.inputMovieReservePerson();
                reservationInfo.addChosenPeople(movieTime, moviePeople);
                check = InputView.inputReserveOrPurchase();
            }

        } catch (IllegalArgumentException e) {
            // TODO 예매를 진행할지 말지 선택권
            main(args);
        }

        // TODO 구현 진행, static 문제 해결

    }

    public static void reservationProgress() {

    }
}
