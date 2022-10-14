package ru.job4j.dreamjob.service;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostStore;
import java.util.Collection;
/**
 * Class PostService - Сервис работы с вакансиями. Решение задач уровня Middle.
 * 3.2. Web  Тема : 3.2.4. Архитектура Web приложений
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 01.10.2022
 * @version 1
 */
@Service
@ThreadSafe
public class PostService {
    private final PostStore store;
    private final CityService cityService;
    public PostService(PostStore store, CityService cityService) {
        this.store = store;
        this.cityService = cityService;
    }
    public Collection<Post> findAll() {
        Collection<Post> posts = store.findAll();
        posts.forEach(
                post -> post.setCity(
                        cityService.findById(post.getCity().getId())
                )
        );
        return posts;
    }
    public void add(Post post) {
        store.add(post);
    }
    public void update(Post post) {
        store.update(post);
    }
    public Post findById(int id) {
        return store.findById(id);
    }
}
