package ru.job4j.dreamjob.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.service.PostService;

/**
 * Class PostStore - Контроллер вакансий. Решение задач уровня Middle.
 * 3.2. Web Тема : 3.2.2. Html, Bootstrap, Thymeleaf.
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 01.10.2022
 * @version 1
 */
@Controller
public class PostControl {
    private final PostService postService;
    public PostControl(PostService postService) {
        this.postService = postService;
    }
    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "posts";
    }
    @GetMapping("/formAddPost")
    public String addPost(Model model) {
        model.addAttribute("post", new Post(0, "Заполните поле", ""));
        return "addPost";
    }
    @PostMapping("/createPost")
    public String createPost(@ModelAttribute Post post) {
        postService.add(post);
        return "redirect:/posts";
    }
    @PostMapping("/updatePost")
    public String updatePost(@ModelAttribute Post post) {
        postService.update(post);
        return "redirect:/posts";
    }
    @GetMapping("/formUpdatePost/{postId}")
    public String formUpdatePost(Model model, @PathVariable("postId") int id) {
        model.addAttribute("post", postService.findById(id));
        return "updatePost";
    }
}