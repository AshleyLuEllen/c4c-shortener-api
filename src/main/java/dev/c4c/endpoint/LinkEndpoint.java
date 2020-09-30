package dev.c4c.endpoint;

import dev.c4c.data.link.Link;
import dev.c4c.data.link.LinkService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/link")
    public Link saveLink(@RequestBody Link link) {
        return linkService.saveLink(link);
    }
}
