package view;

import domain.Movie;
import domain.PlaySchedule;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    /*
     * 예약할 영화를 선택
     */
    public static int inputMovieId() {
        System.out.println("## 예약할 영화를 선택하세요.");
        return scanner.nextInt();
    }

    /*
     * 예약할 영화의 시간을 선택
     */
    public static int inputMovieSchedule() {
        System.out.println("\n## 예약할 시간표를 선택하세요. (첫번째 상영 시간이 1번)");
        return scanner.nextInt();
    }

    /*
     * 예약할 영화의 시간에 입장할 인원을 입력
     */
    public static int inputMovieReservePerson() {
        System.out.println("\n## 예약할 인원을 입력하세요.");
        return scanner.nextInt();
    }

    /*
     * 예약을 계속 할 지, 결제를 할 지 입력
     */
    public static int inputReserveOrPurchase() {
        System.out.println("\n## 예약을 종료하고 결제를 진행하려면 1번, 추가 예약을 진행하려면 2번");
        return scanner.nextInt();
    }

    /*
     * 포인트 금액 입력
     */
    public static int inputPoint() {
        System.out.println("## 결제를 진행합니다.");
        System.out.println("## 포인트 사용 금액을 입력하세요. 포인트가 없으면 0 입력");
        return scanner.nextInt();
    }

    /*
     * 결제를 카드로 할 지 현금으로 할 지 선택
     */
    public static int inputCardOrCash() {
        System.out.println("\n## 신용카드는 1번, 현금은 2번");
        return scanner.nextInt();
    }
}
