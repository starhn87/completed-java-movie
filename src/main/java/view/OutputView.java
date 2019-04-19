package view;

import domain.Movie;
import domain.ReservationInfo;

import java.time.LocalDateTime;
import java.util.List;

import static utils.DateTimeUtils.format;
import static utils.DateTimeUtils.isOneHourWithinRange;

public class OutputView {
    public static void printMovies(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    /*
     * 예약할 영화를 출력하고 담음
     */
    public static void printChosenMovie(List<Movie> movies, int movieId) {
        boolean answer = false;
        Movie movie = null;
        for (int i = 0; i < movies.size() && answer == false; i++) {
            movie = movies.get(i);
            boolean recognize = (movie.getId() == movieId) ? true : false;
            answer = answer || recognize;
        }
        if (answer == true) {
            System.out.println(movie);
        }
    }

    /*
     * 예매 내역 출력
     */
    public static void printReservation(ReservationInfo reservationInfo) {
        System.out.println("예매 내역");
        List<Movie> movies = reservationInfo.getChosenMovie();
        List<LocalDateTime> times = reservationInfo.getChosenTimes();
        List<Integer> people = reservationInfo.getChosenPeople();
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i).toStringTitile());
            System.out.println("시작시간: " + format(times.get(i)));
            System.out.println("예약 인원: " + people.get(i) + "명\n");
        }
    }

    /*
     * 최종 결제 금액 출력
     */
    public static void printFinalPayment(int payment) {
        System.out.println("\n최종 결제한 금액은 " + payment + "원입니다.");
        System.out.println("예매를 완료했습니다. 즐거운 영화 관람되세요.");
    }
}
