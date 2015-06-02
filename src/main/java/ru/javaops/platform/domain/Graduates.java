package ru.javaops.platform.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * A Graduates.
 */
@Entity
@Table(name = "GRADUATES")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Graduates extends BaseEntity{

    @Column(name = "graduates_order")
    private Integer graduatesOrder;

    @ManyToOne
    private Course course;

    public Integer getGraduatesOrder() {
        return graduatesOrder;
    }

    public void setGraduatesOrder(Integer graduatesOrder) {
        this.graduatesOrder = graduatesOrder;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Graduates{" +
                "id=" + id +
                ", graduatesOrder='" + graduatesOrder + "'" +
                '}';
    }
}
