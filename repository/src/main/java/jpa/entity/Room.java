package jpa.entity;

import javax.persistence.*;

/**
 * @author Georgy Sorokin
 */
@Entity
@Table(name = "rooms", schema = "turnout")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "lower_left_corner", nullable = false)
    private Point lowerLeftCorner;

    @ManyToOne
    @JoinColumn(name = "upper_right_corner", nullable = false)
    private Point upperRightCorner;

    @Column(nullable = false)
    private Boolean floor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Point getLowerLeftCorner() {
        return lowerLeftCorner;
    }

    public void setLowerLeftCorner(Point lowerLeftCorner) {
        this.lowerLeftCorner = lowerLeftCorner;
    }

    public Point getUpperRightCorner() {
        return upperRightCorner;
    }

    public void setUpperRightCorner(Point upperRightCorner) {
        this.upperRightCorner = upperRightCorner;
    }

    public Boolean getFloor() {
        return floor;
    }

    public void setFloor(Boolean floor) {
        this.floor = floor;
    }
}
