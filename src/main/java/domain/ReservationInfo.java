package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static utils.DateTimeUtils.isOneHourWithinRange;

public class ReservationInfo {
    private static List<Movie> chosenMovie = new ArrayList<>();
    private static List<LocalDateTime> chosenTimes = new ArrayList<>();
    private static List<Integer> chosenPeople = new ArrayList<>();

    public List<Movie> getChosenMovie() {
        return chosenMovie;
    }

    public List<Integer> getChosenPeople() {
        return chosenPeople;
    }

    public List<LocalDateTime> getChosenTimes() {
        return chosenTimes;
    }

    public void addChosenMovice(List<Movie> movies, int movieId) {      // TODO 영화 선택을 하면서 바로 오류도 거르게
        Movie movie = null;
        boolean condition = false;
        for (int i = 0; i < movies.size() && condition == false; i++) {
            condition = (movies.get(i).getId() == movieId) ? true : false;
            movie = (condition == true) ? movies.get(i) : null;
        }
        checkMovieList(movie);
        chosenMovie.add(movie);
    }

    public void addChosenTime(int schedule) {       // TODO 시간 선택을 하면서 바로 오류도 거르게
        LocalDateTime startTime = chosenMovie.get(chosenMovie.size() - 1).
                getPlaySchedules().get(schedule - 1).getStartDateTime();
        judgeOneHour(startTime);
        judgeStartTime(startTime);
        chosenTimes.add(startTime);
    }

    public void addChosenPeople(int index, int people) {       // // TODO 인원 선택을 하면서 바로 오류도 거르게
        PlaySchedule playSchedule = chosenMovie.get(chosenMovie.size() - 1).getPlaySchedules().get(index - 1);
        if (people > playSchedule.getCapacity()) {
            System.out.println("예매 가능 인원을 초과하였습니다.");
            chosenMovie.remove(chosenMovie.size() - 1);
            chosenTimes.remove(chosenTimes.size() - 1);
            throw new IllegalArgumentException();
        }
        if (people <= 0) {
            System.out.println("잘못된 값을 입력하였습니다.");
            chosenMovie.remove(chosenMovie.size() - 1);
            chosenTimes.remove(chosenTimes.size() - 1);
            throw new IllegalArgumentException();
        }
        playSchedule.reserve(people);
        chosenPeople.add(people);
    }

    /*
     * 상영 시작 시간이 현재 시각보다 나중인지 판단한다
     */
    public void judgeStartTime(LocalDateTime localDateTime) {
        if (localDateTime.isBefore(LocalDateTime.now())) {
            System.out.println("상영 시작 시간이 이미 지난 영화입니다.");
            chosenMovie.remove(chosenMovie.size() - 1);
            throw new IllegalArgumentException();
        }
    }

    /*
     * 두 영화의 시간 차이가 1시간 이내인지 판단한다
     */
    public void judgeOneHour(LocalDateTime localDateTime) {
        boolean answer = false;
        for (int i = 0; i < chosenTimes.size() && answer == false; i++) {
            answer = answer || isOneHourWithinRange(chosenTimes.get(i), localDateTime);
        }
        if ((chosenTimes.size() > 1) && (answer == true)) {
            System.out.println("두 영화의 시간 차이가 1시간 이내입니다.");
            chosenMovie.remove(chosenMovie.size() - 1);
            throw new IllegalArgumentException();
        }
    }

    /*
     * 상영목록에 있는지 판단
     */
    public void checkMovieList(Movie movie) {
        if (movie == null) {
            System.out.println("상영 목록에 없는 영화입니다.");
            throw new IllegalArgumentException();
        }
    }
}
