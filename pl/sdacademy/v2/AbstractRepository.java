package pl.sdacademy.v2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractRepository<T extends Entity> {
    private Set<T> entities;
    private Path filePath;

    public AbstractRepository(String filename) {
        filePath = Paths.get(filename);
        try {
            entities = Files.lines(filePath)
                    .map(this::createFromFileLine)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException("Błąd odczytu danych z repozytorium");
        }
    }

    protected abstract T createFromFileLine(String fileLine);

    public Set<T> findAll() {
        return entities;
    }

    public T findById(int id) {
        return entities.stream()
                .filter(entity -> entity.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void add(T entity) {
        entity.setId(getNextId());
        entities.add(entity);
        saveToFile();
    }

    private void saveToFile() {
        List<String> fileLines = entities.stream()
                .map(this::createFileLine)
                .collect(Collectors.toList());
        try {
            Files.write(filePath, fileLines);
        } catch (IOException e) {
            throw new RuntimeException("Błąd zapisu danych");
        }
    }

    protected abstract String createFileLine(T entity);

    private int getNextId() {
        return entities.stream()
                .mapToInt(Entity::getId)
                .max()
                .orElse(0) + 1;
    }
}