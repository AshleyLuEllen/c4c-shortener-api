package dev.c4c.data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity
@Table(name = User.TABLE_NAME)
public class User {
    public static final String TABLE_NAME = "users";

    @Id
    @GeneratedValue(generator = TABLE_NAME + "_GENERATOR")
    @SequenceGenerator(
        name = TABLE_NAME + "_GENERATOR",
        sequenceName = TABLE_NAME + "_SEQUENCE"
    )
    @Column(name = "user_id")
    Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "user_email")
    String emailAddress;

    @Column(name = "user_role")
    @Enumerated(value = EnumType.STRING)
    Role role;

    @Column(name = "pass_hash")
    @JsonIgnore
    String password;

    @Column(name = "salt")
    @JsonIgnore
    String salt;
}
