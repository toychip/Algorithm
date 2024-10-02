import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static List<Meeting> meetings;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        meetings = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split(" ");
            meetings.add(new Meeting(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }

        dp = new int[N + 1];
        Arrays.fill(dp, -1);

        writer.write(String.valueOf(recur(0)));
        writer.flush();
        writer.close();
    }

    private static int recur(int index) {
        if (index == N) {
            return 0;
        }

        if (index > N) {
            return Integer.MIN_VALUE;
        }

        Meeting meeting = meetings.get(index);

        // 이미 저장되었다면
        if (dp[index] != -1) {
            return dp[index];
        }

        // 상담을 받거나, 안받거나, 그 중에서 더 많은 돈을 버는 경우를 내 DP 테이블에 저장
        dp[index] = Math.max(
                recur(index + meeting.getDay()) + meeting.getPay(), recur(index + 1)
        );

        return dp[index];
    }

    static class Meeting {
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
}


