package pl.sdacademy.v2;

public class Car implements Entity{
    private String model;
    private int maxSpeed;
    private int id;

    public Car(String model, int maxSpeed) {
        this.model = model;
        this.maxSpeed = maxSpeed;
    }

    public Car(String model, int maxSpeed, int id) {
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.id = id;
    }

    public Car(int id, String model, int maxSpeed) {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", id=" + id +
                '}';
    }
}

    