package ru.job4j.dreamjob.store;
import ru.job4j.dreamjob.model.Post;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Date;
/**
 * Class PostStore - Хранилище вакансий. Решение задач уровня Middle.
 * 3.2. Web Тема : 3.2.2. Html, Bootstrap, Thymeleaf.
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 01.10.2022
 * @version 1
 */
public class PostStore {
    private static final PostStore INST = new PostStore();
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "dsc1", new Date()));
        posts.put(2, new Post(2, "Middle Java Job", "dsc2", new Date()));
        posts.put(3, new Post(3, "Senior Java Job", "dsc3", new Date()));
    }
    public static PostStore instOf() {
        return INST;
    }
    public Collection<Post> findAll() {
        return posts.values();
    }
}
