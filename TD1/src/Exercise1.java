import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

public class Exercise1 {
    public static void main(String[] args) {
        LogicalDateTime ldt = new LogicalDateTime("24/01/2024 10:34:47.6789");
        System.out.println(ldt);
        LogicalDuration offset = LogicalDuration.ofMinutes(15).add(LogicalDuration.ofSeconds(12).add(LogicalDuration.ofMillis(670)));
        LogicalDateTime offsetLdt = ldt.add(offset);
        System.out.println(offsetLdt);
        System.out.println(offset);
    }
}
