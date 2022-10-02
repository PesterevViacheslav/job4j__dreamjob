package ru.job4j.dreamjob.model;
import java.util.Date;
import java.util.Objects;
/**
 * Class Candidate - Кандидат. Решение задач уровня Middle.
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 02.10.2022
 * @version 1
 */
public class Candidate {
    private int id;
    private String name;
    private String description;
    private Date created;

    public Candidate(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id && Objects.equals(name, candidate.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
    @Override
    public String toString() {
        return "Candidate{" + "id=" + id + ", name='" + name + '\''
                +  ", description='" + description + '\'' + ", created='"
                + created + '}';
    }
}
