package pl.sdacademy.v1;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CarRepository {
    private Set<Car> cars;
    private Path filePath;

    public CarRepository(String filename) {
        filePath = Paths.get(filename);
        try {
            cars = Files.lines(filePath)
                    .map(this::createFromFileLine)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException("Błąd odczytu danych z repozytorium");
        }
    }

    private Car createFromFileLine(String fileLine) {
        String[] fileLineParts = fileLine.split(",");
        int id = Integer.parseInt(fileLineParts[0]);
        String model = fileLineParts[1];
        int maxSpeed = Integer.parseInt(fileLineParts[2]);
        return new Car(id, model, maxSpeed);
    }

    public Set<Car> findAll() {
        return cars;
    }

    public Car findById(int id) {
        return cars.stream()
                .filter(car -> car.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void add(Car car) {
        car.setId(getNextId());
        cars.add(car);
        saveToFile();
    }

    private void saveToFile() {
        List<String> fileLines = cars.stream()
                .map(this::createFileLine)
                .collect(Collectors.toList());
        try {
            Files.write(filePath, fileLines);
        } catch (IOException e) {
            throw new RuntimeException("Błąd zapisu danych");
        }
    }

    private String createFileLine(Car car) {
        return car.getId() + ","
                + car.getModel() + ","
                + car.getMaxSpeed();
    }

    private int getNextId() {
        return cars.stream()
                .mapToInt(Car::getId)
                .max()
                .orElse(0) + 1;
    }
}
