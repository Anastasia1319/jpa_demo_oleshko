package org.belhard.hibernate.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", length = 25, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 75, nullable = false)
    private String lastName;

    @ManyToMany(cascade = {CascadeType.PERSIST,
                            CascadeType.MERGE,
                            CascadeType.REFRESH})
    @JoinTable(name = "students_sections",
    joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "section_id"))
    private List<Section> sections;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(sections, student.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, sections);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sections=" + sections +
                '}';
    }
}
