package dev.c4c.data;

import javax.persistence.*;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = Link.TABLE_NAME)
@IdClass(LinkId.class)
public class Link {
    public static final String TABLE_NAME = "links";

    @Id
    @GeneratedValue(generator = TABLE_NAME + "_GENERATOR")
    @SequenceGenerator(
        name = TABLE_NAME + "_GENERATOR",
        sequenceName = TABLE_NAME + "_SEQUENCE"
    )
    @Column(name = "link_namespace")
    String namespace;

    @Id
    @Column(name = "link_short_code")
    String shortCode;

    @Column(name = "link_redirect_url")
    String redirectURL;

    @JoinColumn(name = "link_owner", referencedColumnName = "user_id")
    @ManyToOne()
    User owner;

    @Column(name = "link_created")
    ZonedDateTime created;

    @Column(name = "link_expiry")
    ZonedDateTime expiry;
}
