import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Module11Test {
    static List<String> names = Arrays.asList("Andrii", "Anna", "Volodymyr", "Oleh", "Ihor", "Marta");
    static String[] numbers = new String[] {"1, 3, 9", "5, 4", "2, 8, 7", "3"};
    static Stream<String> firstStream = Stream.of("Kyiv", "Lviv", "Ternopil", "Odesa" );
    static Stream<String> secondStream = Stream.of("3000", "800", "220", "1000", "1000", "30", "5");

    public static void main(String[] args) {
        System.out.println(oddIndexNames(names));
        System.out.println(namesToUpperCase(names));
        System.out.println(numbersArrayPrinting(numbers));
        LCGTest(1, 25214903917L, 11, (long)Math.pow(2, 48));
        System.out.println(zip(firstStream, secondStream).collect(Collectors.toList()));
    }
    // task #1
    public static String oddIndexNames(List<String> names) {
        return names.stream()
                .filter(name -> names.indexOf(name) % 2 == 0)
                .map(name -> String.format("%d. %s", names.indexOf(name) + 1, name))
                .collect(Collectors.joining(", ", "", "."));
    }

    // task #2
    public static List<String> namesToUpperCase(List<String> names) {
        return names.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    // task #3
    public static String numbersArrayPrinting(String[] input) {
        return Arrays.stream(input)
                .map(s -> Arrays.asList(s.split(", ")))
                .flatMap(Collection::stream)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining(", ", "\"","\""));
    }

    // task #4
    public static Stream<Long> linearCongruentialGenerator(long seed, long a, long c, long m) {
        return Stream.iterate(seed, x -> (a * x + c) % m);
    }

    private static void LCGTest(long seed, long a, long c, long m) {
        List<Long> result = linearCongruentialGenerator(seed, a, c, m)
                .limit(10)
                .collect(Collectors.toList());
        System.out.println(result);
    }

    // task #5
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        List<T> firstList = first.collect(Collectors.toList());
        List<T> secondList = second.collect(Collectors.toList());
        List<T> resultList = new LinkedList<>();
        int zipSize = Math.min(firstList.size(), secondList.size());

        for(int i = 0; i < zipSize; i++) {
            resultList.add(firstList.get(i));
            resultList.add(secondList.get(i));
        }

        return resultList.stream();
    }
}
