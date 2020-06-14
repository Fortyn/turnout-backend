package jpa.entity;

import javax.persistence.*;

/**
 * @author Georgy Sorokin
 */
@Entity
@Table(name = "doors", schema = "turnout")
public class Door {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Wall wall;

    @ManyToOne
    @JoinColumn(name = "position", nullable = false)
    private Point position;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}
