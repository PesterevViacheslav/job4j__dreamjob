package ru.job4j.dreamjob.store;
import ru.job4j.dreamjob.model.Candidate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Date;
/**
 * Class CandidateStore - Хранилище кандидатов. Решение задач уровня Middle.
 * 3.2. Web Тема : 3.2.2. Html, Bootstrap, Thymeleaf.
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 01.10.2022
 * @version 1
 */
public class CandidateStore {
    private static final CandidateStore INST = new CandidateStore();
    private final Map<Integer, Candidate> posts = new ConcurrentHashMap<>();
    private CandidateStore() {
        posts.put(1, new Candidate(1, "Candidate 1", "dsc1", new Date()));
        posts.put(2, new Candidate(2, "Candidate 2", "dsc2", new Date()));
        posts.put(3, new Candidate(3, "Candidate 3", "dsc3", new Date()));
    }
    public static CandidateStore instOf() {
        return INST;
    }
    public Collection<Candidate> findAll() {
        return posts.values();
    }
}
