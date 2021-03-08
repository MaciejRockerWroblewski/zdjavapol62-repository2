package pl.sdacademy.v1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonRepository {
    private Set<Person> people;
    private Path filePath;

    public PersonRepository(String filename) {
        filePath = Paths.get(filename);
        try {
            people = Files.lines(filePath)
                    // Wersja 1 (lambda):
//                    .map(fileLine -> createFromFileLine(fileLine))
                    // Wersja 2 (referencja metody):
                    .map(this::createFromFileLine)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException("Błąd odczytu danych z repozytorium");
        }
    }

    private Person createFromFileLine(String fileLine) {
        String[] fileLineParts = fileLine.split(",");
        int id = Integer.parseInt(fileLineParts[0]);
        String firstName = fileLineParts[1];
        String lastName = fileLineParts[2];
        int age = Integer.parseInt(fileLineParts[3]);
        return new Person(id, firstName, lastName, age);
    }

    public Set<Person> findAll() {
        return people;
    }

    public Person findById(int id) {
        // Wersja ze strumieniem:
        return people.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);
        // Wersja z pętlą for each:
//        for (Person person : people) {
//            if (person.getId() == id) {
//                return person;
//            }
//        }
//        return null;
    }

    public void add(Person person) {
        person.setId(getNextId());
        people.add(person);
        saveToFile();
    }

    private void saveToFile() {
        List<String> fileLines = people.stream()
                .map(this::createFileLine)
                .collect(Collectors.toList());
        try {
            Files.write(filePath, fileLines);
        } catch (IOException e) {
            throw new RuntimeException("Błąd zapisu danych");
        }
    }

    private String createFileLine(Person person) {
        return person.getId() + ","
                + person.getFirstName() + ","
                + person.getLastName() + ","
                + person.getAge();
    }

    private int getNextId() {
        return people.stream()
                .mapToInt(Person::getId)
                .max()
                .orElse(0) + 1;
    }
}