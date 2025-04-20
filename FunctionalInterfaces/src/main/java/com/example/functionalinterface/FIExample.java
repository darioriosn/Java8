package com.example.functionalinterface;

import com.example.functionalinterface.entities.Person;
import com.example.functionalinterface.validators.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.*;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

public class FIExample {

    public static void main(String[] args) {
        // Datos de ejemplo
        List<Person> people = Arrays.asList(
                new Person("John", 28, "Developer", 75000),
                new Person("Sarah", 32, "Architect", 125000),
                new Person("Mike", 24, "Developer", 65000),
                new Person("Emily", 35, "Manager", 140000)
        );

        System.out.println("=== Interfaces Funcionales Básicas ===");

        // 1. Function<T, R> - toma un argumento y devuelve un resultado
        System.out.println("\n1. Function<T, R>:");
        Function<Person, String> nameExtractor = person -> person.getName();
        people.forEach(person -> System.out.println("Nombre: " + nameExtractor.apply(person)));

        // 2. Predicate<T> - toma un argumento y devuelve un booleano
        System.out.println("\n2. Predicate<T>:");
        Predicate<Person> isDeveloper = person -> "Developer".equals(person.getRole());
        people.forEach(person -> System.out.println(
                person.getName() + " es desarrollador: " + isDeveloper.test(person)));

        // 3. Consumer<T> - toma un argumento y no devuelve resultado (void)
        System.out.println("\n3. Consumer<T>:");
        Consumer<Person> salaryPrinter = person ->
                System.out.println(person.getName() + " gana $" + person.getSalary());
        people.forEach(salaryPrinter);

        // 4. Supplier<T> - no toma argumentos y devuelve un resultado
        System.out.println("\n4. Supplier<T>:");
        Supplier<LocalDate> currentDate = () -> LocalDate.now();
        System.out.println("Fecha actual: " + currentDate.get());

        // 5. UnaryOperator<T> - toma un argumento y devuelve un resultado del mismo tipo
        System.out.println("\n5. UnaryOperator<T>:");
        UnaryOperator<String> toUpperCase = str -> str.toUpperCase();
        System.out.println("Transformado: " + toUpperCase.apply("Hello world!"));

        // 6. BinaryOperator<T> - toma dos argumentos del mismo tipo y devuelve un resultado de ese tipo
        System.out.println("\n6. BinaryOperator<T>:");
        BinaryOperator<Integer> sum = (a, b) -> a + b;
        System.out.println("Suma de 10 y 25: " + sum.apply(10, 25));

        System.out.println("\n=== Interfaces Funcionales de Dos Argumentos ===");

        // 7. BiFunction<T, U, R> - toma dos argumentos y devuelve un resultado
        System.out.println("\n7. BiFunction<T, U, R>:");
        BiFunction<Person, Double, Double> salaryAdjuster =
                (person, factor) -> person.getSalary() * factor;
        Person john = people.get(0);
        System.out.println(john.getName() + " salario ajustado: $" +
                salaryAdjuster.apply(john, 1.1)); // 10% de aumento

        // 8. BiPredicate<T, U> - toma dos argumentos y devuelve un booleano
        System.out.println("\n8. BiPredicate<T, U>:");
        BiPredicate<Person, Integer> salaryThreshold =
                (person, threshold) -> person.getSalary() > threshold;
        people.forEach(person -> System.out.println(
                person.getName() + " gana más de $100K: " +
                        salaryThreshold.test(person, 100000)));

        // 9. BiConsumer<T, U> - toma dos argumentos y no devuelve resultado
        System.out.println("\n9. BiConsumer<T, U>:");
        BiConsumer<Person, String> roleAssigner = (person, newRole) ->
                System.out.println("Asignando a " + person.getName() + " como " + newRole);
        people.forEach(person -> roleAssigner.accept(person, "Senior " + person.getRole()));

        System.out.println("\n=== Especializaciones para Tipos Primitivos ===");

        // 10. IntPredicate - especializado para valores int
        System.out.println("\n10. IntPredicate:");
        IntPredicate isEven = num -> num % 2 == 0;
        System.out.println("¿Es 42 par? " + isEven.test(42));

        // 11. LongConsumer - especializado para valores long
        System.out.println("\n11. LongConsumer:");
        LongConsumer printHexValue = value ->
                System.out.println("Valor hexadecimal de " + value + " es: 0x" +
                        Long.toHexString(value).toUpperCase());
        printHexValue.accept(255);

        // 12. DoubleFunction<R> - toma un double y devuelve un resultado
        System.out.println("\n12. DoubleFunction<R>:");
        DoubleFunction<String> formatCurrency = amount ->
                String.format("$%.2f", amount);
        System.out.println("Formateado: " + formatCurrency.apply(1234.56));

        // 13. ToIntFunction<T> - toma un argumento y devuelve un int
        System.out.println("\n13. ToIntFunction<T>:");
        ToIntFunction<Person> ageExtractor = Person::getAge;
        double averageAge = people.stream()
                .mapToInt(ageExtractor)
                .average()
                .orElse(0);
        System.out.println("Edad promedio: " + averageAge);

        System.out.println("\n=== Ejemplos Prácticos en Java 8 ===");

        // 14. Negación de predicados en Java 8 (sin Predicate.not())
        System.out.println("\n14. Negación de predicados (Java 8):");
        Predicate<Person> isNotDeveloper = isDeveloper.negate();
        List<Person> nonDevelopers = people.stream()
                .filter(isNotDeveloper)
                .collect(Collectors.toList());
        System.out.println("No desarrolladores: " +
                nonDevelopers.stream()
                        .map(Person::getName)
                        .collect(Collectors.joining(", ")));

        // 15. Interfaz funcional personalizada con métodos default
        System.out.println("\n15. Interfaz funcional personalizada:");
        Validator<Person> ageValidator = person -> person.getAge() >= 18;
        people.forEach(person -> System.out.println(
                person.getName() + " validación de edad: " +
                        ageValidator.validate(person)));

        // 16. Composición de funciones
        System.out.println("\n16. Composición de funciones:");
        Function<Person, String> getRoleUpperCase =
                person -> person.getRole().toUpperCase();
        Function<String, String> addPrefix =
                s -> "ROL: " + s;
        Function<Person, String> getRoleWithPrefix =
                getRoleUpperCase.andThen(addPrefix);

        people.forEach(person -> System.out.println(
                person.getName() + " - " + getRoleWithPrefix.apply(person)));

        // 17. Uso de interfaces funcionales con streams
        System.out.println("\n17. Uso con streams:");
        Map<String, Double> roleToAverageSalary = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getRole,
                        Collectors.averagingDouble(Person::getSalary)
                ));
        System.out.println("Salario promedio por rol: " + roleToAverageSalary);

        // 18. Uso con CompletableFuture (programación asíncrona)
        System.out.println("\n18. Con CompletableFuture:");
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> "Procesando datos...")
                .thenApply(s -> s + " Paso 2...")
                .thenApply(s -> s + " ¡Completo!");

        System.out.println("Resultado: " + future.join());

        // 19. Referencias a métodos como interfaces funcionales
        System.out.println("\n19. Referencias a métodos:");
        // Referencia a método estático
        Function<String, Integer> parseInt = Integer::parseInt;
        // Referencia a método de instancia de un objeto particular
        Consumer<String> printer = System.out::println;
        // Referencia a método de instancia de un objeto arbitrario de un tipo particular
        Function<String, String> toLowerCase = String::toLowerCase;

        printer.accept("Resultado de parsear '42': " + parseInt.apply("42"));
        printer.accept("Minúsculas: " + toLowerCase.apply("JAVA 8"));
    }

}