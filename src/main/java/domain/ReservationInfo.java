package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static utils.DateTimeUtils.isOneHourWithinRange;

public class ReservationInfo {
    private List<Movie> chosenMovie = new ArrayList<>();
    private List<LocalDateTime> chosenTimes = new ArrayList<>();
    private List<Integer> chosenPeople = new ArrayList<>();

    public ReservationInfo() {

    }

    public List<Movie> getChosenMovie() {
        return chosenMovie;
    }

    public List<LocalDateTime> getChosenTimes() {
        return chosenTimes;
    }

    public List<Integer> getChosenPeople() {
        return chosenPeople;
    }


    /*
     * 리스트에 영화 추가
     */
    public void addChosenMovice(List<Movie> movies, int movieId) {
        Movie movie = null;
        boolean condition = false;
        for (int i = 0; i < movies.size() && condition == false; i++) {
            condition = (movies.get(i).getId() == movieId) ? true : false;
            movie = (condition == true) ? movies.get(i) : null;
        }
        checkMovieList(movie);
        chosenMovie.add(movie);
    }

    /*
     * 리스트에 시작시간 추가
     */
    public void addChosenTime(int schedule) {
        LocalDateTime startTime = chosenMovie.get(chosenMovie.size() - 1).
                getPlaySchedules().get(schedule - 1).getStartDateTime();
        judgeOneHour(startTime);
        judgeStartTime(startTime);
        chosenTimes.add(startTime);
    }

    /*
     * 리스트에 예약 인원 추가
     */
    public void addChosenPeople(int index, int people) {
        PlaySchedule playSchedule = chosenMovie.get(chosenMovie.size() - 1).getPlaySchedules().get(index - 1);
        handleOverCapacity(playSchedule, people);
        handleWrongPeople(people);
        playSchedule.reserve(people);
        chosenPeople.add(people);
    }

    /*
     * 예약 가능 인원을 초과하여 예약하려는 경우 처리
     */
    public void handleOverCapacity(PlaySchedule playSchedule, int people) {
        if (people > playSchedule.getCapacity()) {
            System.out.println("예매 가능 인원을 초과하였습니다.");
            throw new IllegalArgumentException();
        }
    }

    /*
     * 0 이하의 값 입력시 처리
     */
    public void handleWrongPeople(int people) {
        if (people <= 0) {
            System.out.println("잘못된 값을 입력하였습니다.");
            throw new IllegalArgumentException();
        }
    }

    /*
     * 상영 시작 시간이 현재 시각보다 나중인지 판단한다
     */
    public void judgeStartTime(LocalDateTime localDateTime) {
        if (localDateTime.isBefore(LocalDateTime.now())) {
            System.out.println("상영 시작 시간이 이미 지난 영화입니다.");
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
        if ((chosenTimes.size() > 0) && (answer == true)) {
            System.out.println("두 영화의 시간 차이가 1시간 이내입니다.");
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

    /*
     * 예약 중간에 오류 발생시 중간값 삭제
     */
    public void recoverCancelledPart() {
        int movieLen = chosenMovie.size();
        int timeLen = chosenTimes.size();
        int peopleLen = chosenPeople.size();

        if (movieLen != peopleLen) {
            chosenMovie.remove(chosenMovie.size() - 1);
        }
        if (timeLen != peopleLen) {
            chosenTimes.remove(chosenTimes.size() - 1);
        }
    }

    /*
     * 예약 내역에 따른 비용 계산
     */
    public double howMuch() {
        double money = 0;
        for (int i = 0; i < chosenMovie.size(); i++) {
            int movieValue = chosenMovie.get(i).getPrice();
            money += movieValue * chosenPeople.get(i);
        }
        return money;
    }
}
