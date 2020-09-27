package dev.c4c.endpoint;

import dev.c4c.data.Link;
import dev.c4c.service.LinkService;
import dev.c4c.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@RestController
public class LinkEndpoint {
    @Autowired
    private LinkService linkService;

    @GetMapping("/")
    public RedirectView mainPage() {
        return getNamespacedLink("special:Home Page", "home_page");
    }

    @GetMapping("/{code}")
    public RedirectView getLink(@PathVariable String code) {
        return getNamespacedLink("globals", code);
    }

    @GetMapping("/{namespace}/{code}")
    public RedirectView getNamespacedLink(@PathVariable String namespace, @PathVariable String code) {
        Link link = linkService.findLink(namespace, code)
            .orElse(
                linkService.findLink("special:Invalid Link Redirect", "invalid_link")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Short link not found."))
            );
        return new RedirectView(link.getRedirectURL());
    }
}