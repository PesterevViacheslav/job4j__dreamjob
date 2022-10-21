package ru.job4j.dreamjob.control;
import org.junit.Test;
import org.springframework.ui.Model;
import ru.job4j.dreamjob.controller.PostControl;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.service.PostService;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class PostControlTest {
    @Test
    public void whenPosts() {
        List<Post> posts = Arrays.asList(
                new Post(1, "post1", "dsc", new City(0, "city1")),
                new Post(2, "post2", "dsc", new City(1, "city2"))
        );
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        when(postService.findAll()).thenReturn(posts);
        CityService cityService = mock(CityService.class);
        PostControl postControl = new PostControl(
                postService,
                cityService
        );
        String page = postControl.posts(model, session);
        verify(model).addAttribute("posts", posts);
        assertThat(page, is("posts"));
    }

    @Test
    public void whenCreatePost() {
        Post input = new Post(1, "post2", "dsc", new City(1, "city2"));
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostControl postControl = new PostControl(
                postService,
                cityService
        );
        String page = postControl.createPost(input);
        verify(postService).add(input);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void whenUpdatePost() {
        Post input = new Post(1, "post2", "dsc", new City(0, "city2"));
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostControl postControl = new PostControl(
                postService,
                cityService
        );
        String page = postControl.updatePost(input);
        verify(postService).update(input);
        assertThat(page, is("redirect:/posts"));
    }
}