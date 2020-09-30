package dev.c4c.data.link;

import dev.c4c.data.link.Link;
import dev.c4c.data.link.LinkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, LinkId> {

}