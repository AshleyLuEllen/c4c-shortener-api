package dev.c4c.repository;

import dev.c4c.data.Link;
import dev.c4c.data.LinkId;
import dev.c4c.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, LinkId> {

}