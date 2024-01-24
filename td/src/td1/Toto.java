package td1;

public record Toto(Number value) implements Comparable<Toto> {

    @Override
    public int compareTo(Toto o) {
        return value.intValue() - o.value.intValue();
    }
}
