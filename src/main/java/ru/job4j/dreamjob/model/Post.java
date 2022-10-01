package ru.job4j.dreamjob.model;
import java.util.Date;
import java.util.Objects;
/**
 * Class Post - Вакансия. Решение задач уровня Middle.
 * 3.2. Web Тема : 3.2.2. Html, Bootstrap, Thymeleaf.
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 01.10.2022
 * @version 1
 */
public class Post {
    private int id;
    private String name;
    private String description;
    private Date created;
    /**
     * Method Post. Конструктор
     * @param id ID
     * @param name Название
     * @param description Описание
     * @param created Дата регистрации
     */
    public Post(int id, String name, String description, Date created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
    }
    /**
     * Method getId. Получение ID
     * @return Id
     */
    public int getId() {
        return id;
    }
    /**
     * Method setId. Изменение ID
     * @param id ID
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Method getName. Получение названия
     * @return Название
     */
    public String getName() {
        return name;
    }
    /**
     * Method setName. Изменение названия
     * @param name Название
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Method getDescription. Получение описания
     * @return Описание
     */
    public String getDescription() {
        return description;
    }
    /**
     * Method setDescription. Изменение описания
     * @param description Описание
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Method getCreated. Получение даты создания
     * @return Дата создания
     */
    public Date getCreated() {
        return created;
    }
    /**
     * Method setCreated. Изменение даты создания
     * @param created Дата создания
     */
    public void setCreated(Date created) {
        this.created = created;
    }
    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", name='" + name + '\''
                + ", description='" + description + '\'' + ", created=" + created + '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id && Objects.equals(name, post.name)
                && Objects.equals(description, post.description)
                && Objects.equals(created, post.created);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
