import model.Employee;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>(Arrays.asList(
                new Employee("Alice1", 21, Employee.Position.DIRECTOR),
                new Employee("Alice2", 23, Employee.Position.ENGINEER),
                new Employee("Alice3", 29, Employee.Position.PRODUCTOWNER),
                new Employee("Alice4", 31, Employee.Position.MANAGER),
                new Employee("Alice5", 37, Employee.Position.ENGINEER),
                new Employee("Alice6", 41, Employee.Position.MANAGER),
                new Employee("Alice7", 43, Employee.Position.ENGINEER),
                new Employee("Alice8", 47, Employee.Position.ENGINEER),
                new Employee("Alice9", 53, Employee.Position.PRODUCTOWNER),
                new Employee("Alice10", 59, Employee.Position.MANAGER),
                new Employee("Alice11", 61, Employee.Position.ENGINEER)
        ));

        List<Integer> integerListExample = new ArrayList<>(Arrays.asList(5, 2, 10, 9, 4, 3,  10, 1,13));

        List<String> stringListExample = new ArrayList<>(Arrays.asList("A", "Al", "Ali", "Alic", "Alice", "Alice1", "Alice1B", "Alice1Bo", "Alice1Bob", "BobAlice1", "Alice1Bob1", "Bob1Alice1", "Alice1Bob1A"));

        String stringExample = "a ab ab abc abc ae dae a fd sa asd gdf asd qw asd qw gfd a abc aasdf";

        String[] arrayStringExample = {"a ab ab abc abc", "ae dae fdsaf fd sa", "asd gdf asd qw asd", "qw gfd a abc aasdf"};

        //Найдите в списке целых чисел 3-е наиболшее число
        Integer max = integerListExample
                .stream()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElseThrow();

        System.out.println("max = " + max);

        //Найдите в списке целых чисел 3-е наиболшее уникальное число
        Integer maxUnic = integerListExample
                .stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElseThrow();

        System.out.println("maxUnic = " + maxUnic);

        //Список имен 3 самых старших сотрудников с должностью инженер, в порядке убывания возраста

        List<Employee> nameOldEngineer = employeeList
                .stream()
                .filter(employee -> employee.getPosition() == Employee.Position.ENGINEER)
                .sorted(Comparator.comparing(Employee::getAge))
                .limit(3)
                .toList();

        System.out.println("nameOldEngineer.toString() = " + nameOldEngineer.toString());

        //Посчитать средний возраст сотрудников с должностью инженер
        double averageAgeEngineer = employeeList
                .stream()
                .filter(employee -> employee.getPosition() == Employee.Position.ENGINEER)
                .mapToDouble(Employee::getAge)
                .average()
                .orElse(0.0);

        System.out.println("averageAgeEngineer = " + averageAgeEngineer);

        //Найти самое длинное слово
        String maxLengthWord = stringListExample
                .stream()
                .max(Comparator.comparing(String::length))
                .orElse("Нет слов в списке");

        System.out.println("maxLengthWord = " + maxLengthWord);

        //Построить хэш-мапы, в которой будут храниться пары: слово - сколько раз оно встречается во входной строке
        Map<String, Long> countWordMap = Arrays.stream(stringExample.split(" "))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        System.out.println("countWordMap = " + countWordMap);

        //Отпечатать в консоль строки из списка в порядке увеличения длины слова, если слова имеют одинаковую длины, то должен быть сохранен алфавитный порядок
        List<String> sortListWord = stringListExample
                .stream()
                .sorted(
                        Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder())
                )
                .toList();

        System.out.println("sortListWord = " + sortListWord);

        //Найти в массиве слов самое длинное слово, если таких несколько, получить любое из них
        String maxLengthWordInArray =  Arrays.stream(arrayStringExample)
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .max(Comparator.comparing(String::length))
                .orElse("пустой массив");

        System.out.println("maxLengthWordInArray = " + maxLengthWordInArray);
    }
}