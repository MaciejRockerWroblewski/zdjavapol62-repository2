package pl.sdacademy.v1;

public class Main {
    public static void main(String[] args) {
        PersonRepository personRepository = new PersonRepository("people.txt");
        System.out.println(personRepository.findAll());
        System.out.println(personRepository.findById(1));

        Person person = new Person(4, "Maciej", "Wróblewski", 37);

        CarRepository carRepository = new CarRepository("cars.txt");
        System.out.println(carRepository.findAll());
        System.out.println(carRepository.findById(2));
    }
}
