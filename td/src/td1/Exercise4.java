package td1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Exercise4 {
    public static void main(String[] args) {
        System.out.println("Let's create a new Predicate using lambda expression in Java");
        Predicate<String> predicate = (e -> {
            return true;
        });
        System.out.println("Predicate is empty: " + predicate.test("Hello World"));
        System.out.println("FilterService test");
        FilterService<String> filterString = new FilterService<>();
        List<String> list = List.of("Hello", "World", "Java", "Python", "C++");
        System.out.println("List before filtering: " + list);
        System.out.println("List after filtering: " + filterString.filter(list, e -> e.length() > 4));

        List<ColorObject> colorObjects = new ArrayList<>();
        FilterService<ColorObject> filterColor = new FilterService<>();
        colorObjects.add(new ColorObject(Color.RED, "John"));
        colorObjects.add(new ColorObject(Color.GREEN, "Wick"));
        colorObjects.add(new ColorObject(Color.BLUE, "Matrix"));
        colorObjects.add(new ColorObject(Color.RED, "Hello"));
        System.out.println("List of colors object" + colorObjects);
        System.out.println("List of red objects" + filterColor.filter(colorObjects, e -> e.color() == Color.RED));
    }

    // Enum for the colors
    enum Color {
        RED, GREEN, BLUE, YELLOW, BLACK, WHITE
    }

    static class FilterService<T> {
        public List<T> filter(List<T> list, Predicate<T> predicate) {
            return list.stream().filter(predicate).toList();
        }
    }

    static record ColorObject(Color color, String name) {
    }
}
