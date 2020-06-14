package jpa.entity;

import javax.persistence.*;

/**
 * @author Georgy Sorokin
 */
@Entity
@Table(name = "points", schema = "turnout")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer abscissa;

    @Column(nullable = false)
    private Integer ordinate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAbscissa() {
        return abscissa;
    }

    public void setAbscissa(Integer abscissa) {
        this.abscissa = abscissa;
    }

    public Integer getOrdinate() {
        return ordinate;
    }

    public void setOrdinate(Integer ordinate) {
        this.ordinate = ordinate;
    }
}
