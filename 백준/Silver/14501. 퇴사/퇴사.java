import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static List<Meeting> meetings;
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        meetings = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split(" ");
            meetings.add(new Meeting(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }

        recur(0, 0);
        writer.write(String.valueOf(answer));
        writer.flush();
        writer.close();
    }

    private static void recur(int index, int pay) {
        if (index > N) {
            return;
        }

        if (index == N) {
            answer = Math.max(pay, answer);
            return;
        }

        Meeting meeting = meetings.get(index);

        // 상담을 할 수 있는 경우. 상담 종료일이 N일을 넘지 않도록 확인
        if (index + meeting.getDay() <= N) {
            recur(index + meeting.getDay(), pay + meeting.getPay());
        }

        recur(index + 1, pay);
    }
}

class Meeting{
    private final int day;
    private final int pay;

    public Meeting(final int day, final int pay) {
        this.day = day;
        this.pay = pay;
    }

    public int getDay() {
        return day;
    }

    public int getPay() {
        return pay;
    }
}
