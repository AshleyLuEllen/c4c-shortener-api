package dev.c4c.service;

import dev.c4c.data.Link;
import dev.c4c.data.LinkId;
import dev.c4c.data.User;
import dev.c4c.repository.LinkRepository;
import dev.c4c.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LinkService {
    @Autowired
    private LinkRepository linkRepository;

    public Optional<Link> findLink(String namespace, String shortCode) {
        return linkRepository.findById(new LinkId(namespace, shortCode));
    }

    public Link saveLink(Link link) {
        return linkRepository.save(link);
    }
}
