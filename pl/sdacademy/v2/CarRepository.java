package pl.sdacademy.v2;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CarRepository extends AbstractRepository<Car> {
    public CarRepository(String filename) {
        super(filename);
    }

    @Override
    protected Car createFromFileLine(String fileLine) {
        String[] fileLineParts = fileLine.split(",");
        int id = Integer.parseInt(fileLineParts[0]);
        String model = fileLineParts[1];
        int maxSpeed = Integer.parseInt(fileLineParts[2]);
        return new Car(id, model, maxSpeed);
    }

    @Override
    protected String createFileLine(Car car) {
        return car.getId() + ","
                + car.getModel() + ","
                + car.getMaxSpeed();
    }
}