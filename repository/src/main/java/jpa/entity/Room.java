package jpa.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

/**
 * @author Georgy Sorokin
 */
@Entity
@Table(name = "rooms", schema = "turnout")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "lower_left_corner", nullable = false)
    private Point lowerLeftCorner;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "upper_right_corner", nullable = false)
    private Point upperRightCorner;

    @Column(nullable = false)
    private Boolean floor;

    @JsonManagedReference
    @OneToMany(mappedBy = "room", cascade = CascadeType.PERSIST)
    private List<Wall> walls;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name = "rooms_anchors",
            schema = "turnout",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "anchor_id")
    )
    private List<Anchor> anchors;

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

    public List<Wall> getWalls() {
        return walls;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public List<Anchor> getAnchors() {
        return anchors;
    }

    public void setAnchors(List<Anchor> anchors) {
        this.anchors = anchors;
    }
}
