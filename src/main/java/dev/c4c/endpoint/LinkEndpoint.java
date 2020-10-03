package dev.c4c.endpoint;

import dev.c4c.data.link.Link;
import dev.c4c.data.link.LinkService;
import dev.c4c.error.ResourceConflictException;
import dev.c4c.error.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping(path = "/admin/")
public class LinkEndpoint {
    @Autowired
    private LinkService linkService;

    @GetMapping("/links")
    public List<Link> findAllLinks() {
        return linkService.findAll();
    }

    @GetMapping("/link/{namespace}/{code}")
    public Link findLinkById(@PathVariable String namespace, @PathVariable String code) {
        var link = linkService.findLink(namespace, code);
        return link.orElse(null);
    }


    @PutMapping("/link/{namespace}/{code}")
    public Link saveLink(@PathVariable String namespace, @PathVariable String code, @RequestBody Link link) {
        Optional<Link> existing = linkService.findLink(namespace, code);
        if (existing.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        if ((link.getNamespace() != null && !link.getNamespace().equals(namespace)) || (link.getShortCode() != null && !link.getShortCode().equals(code))) {
            throw new ResourceConflictException("Path link does not match link object.");
        }

        return linkService.saveLink(link);
    }

    @DeleteMapping("/link/{namespace}/{code}")
    public void deleteLInk(@PathVariable String namespace, @PathVariable String code) {
        linkService.deleteLink(namespace, code);
    }


    @PostMapping("/link")
    public Link createLink(@RequestBody Link link) {
        return linkService.saveLink(link);
    }
}
