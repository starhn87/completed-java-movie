# java-movie
영화를 예약하고 결제하는 프로그램을 구현하는 프로젝트

## 기능목록 (추가된 것 위주로 설명)
**InputView 클래스**
- 예약할 영화를 선택
- 예약할 영화의 시간을 선택
- 예약할 영화의 시간에 입장할 인원을 선택
- 예약을 계속 할 지, 결제를 할 지 선택

**OutputView 클래스**
- 예약할 영화를 출력하고 담음
- 예매 내역 출력
- 결제 진행

**MovieApplication 클래스**
- 영화를 예매하고 구매하는 과정 (main 메소드)

**Movie 클래스**
- 영화의 아이디, 이름, 가격 출력과 playSchedule get

**PlaySchedule 클래스**
- 예매 조건을 통과하면 예매 가능 인원에서 예매할 인원을 뺌

**ReservationInfo 클래스**
- 선택한 영화 목록, 시간, 인원을 담을 리스트를 만든다
- 각 리스트의 getter를 만든다
- 선택한 영화가 상영목록에 있는지 판단한다
- 상영 목록의 영화 중 id가 같은 게 있고 상영 목록에 있다면 예약영화 리스트에 추가한다
- 상영 시작 시간이 현재 시각보다 나중인지 판단한다
- 예약한 영화의 수가 두 개 이상일 경우 예약한 영화들간의 시간 차이가 1시간 이내인지 판단한다
- 방금 선택한 영화의 시간이 조건에 부합하면 예약시간 리스트에 추가한다
- 예약할 인원이 예약 가능 인원보다 크거나 잘못된 값을 입력하지 않으면 예약인원 리스트에 추가한다