package jpa.entity;

/**
 * @author Georgy Sorokin
 */
public enum Direction {
    FRONT(0),
    RIGHT(1),
    BACK(2),
    LEFT(3);

    public final Integer id;

    Direction(Integer id) {
        this.id = id;
    }
}
