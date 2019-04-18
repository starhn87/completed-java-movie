package domain;

import java.util.ArrayList;
import java.util.List;

import static utils.DateTimeUtils.createDateTime;

public class MovieRepository {
    private static List<Movie> movies = new ArrayList<>();

    static {
        Movie movie1 = new Movie(1, "생일", 8_000);
        movie1.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-19 12:00"), 6));
        movie1.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-19 14:40"), 6));
        movie1.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-19 17:00"), 6));
        movie1.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-19 19:40"), 3));
        movie1.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-19 22:00"), 3));
        movies.add(movie1);

        Movie movie2 = new Movie(5, "돈", 10_000);
        movie2.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-19 08:00"), 3));
        movie2.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-19 10:30"), 5));
        movie2.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-19 13:00"), 5));
        movie2.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-19 15:30"), 5));
        movies.add(movie2);

        Movie movie3 = new Movie(7, "파이브피트", 9_000);
        movie3.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-19 13:00"), 4));
        movie3.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-19 15:40"), 4));
        movie3.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-19 18:00"), 4));
        movie3.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-19 20:40"), 3));
        movie3.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-19 23:15"), 3));
        movies.add(movie3);

        Movie movie4 = new Movie(8, "덤보", 9_000);
        movie4.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-19 11:30"), 2));
        movie4.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-19 16:00"), 3));
        movie4.addPlaySchedule(new PlaySchedule(createDateTime("2019-04-19 21:30"), 2));
        movies.add(movie4);
    }

    public static List<Movie> getMovies() {
        return movies;
    }
}
