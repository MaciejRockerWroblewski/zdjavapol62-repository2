package pl.sdacademy;

public class Main {
    public static void main(String[] args) {
        PersonRepository personRepository = new PersonRepository("people.txt");
        System.out.println(personRepository.findAll());
        System.out.println(personRepository.findById(1));

        Person person = new Person(4, "Maciej", "Wróblewski", 37);
    }
}
