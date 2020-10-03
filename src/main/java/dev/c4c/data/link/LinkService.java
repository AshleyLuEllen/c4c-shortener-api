package dev.c4c.data.link;

import dev.c4c.data.link.Link;
import dev.c4c.data.link.LinkId;
import dev.c4c.data.link.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Link> findAll() {
        return linkRepository.findAll();
    }

    public void deleteLink(String namespace, String shortCode) {
        linkRepository.deleteById(new LinkId(namespace, shortCode));
    }
}
