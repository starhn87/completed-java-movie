package view;

import domain.Movie;

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
            boolean recognize = false;
            recognize = (movie.getId() == movieId) ? true : false;
            answer = answer || recognize;
        }
        System.out.println(movie);
    }

    /*
     * 예매 내역 출력
     */
    public void printReservation(List<Movie> movies, List<LocalDateTime> localDateTimes, List<Integer> list) {
        System.out.println("예매 내역");
        for (int i = 0; i < movies.size()
                && i < localDateTimes.size()
                && i < list.size(); i++) {
            System.out.println(movies.get(i).toStringTitile());
            System.out.println(format(localDateTimes.get(i)));
            System.out.println("예약 인원: " + list.get(i) + "명");
            System.out.println();
        }
    }

    /*
     * 결제 진행
     */
    public static void printPurchase() {
        System.out.println("## 결제를 진행합니다.");
        System.out.println("## 포인트 사용 금액을 입력하세요. 포인트가 없으면 0 입력");
    }
}
