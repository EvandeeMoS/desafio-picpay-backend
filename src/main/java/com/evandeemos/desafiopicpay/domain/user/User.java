package com.evandeemos.desafiopicpay.domain.user;

import com.evandeemos.desafiopicpay.domain.user.dto.UserCreationDto;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Users")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType type;

    public User(UserCreationDto data) {
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.document = data.document();
        this.email = data.email();
        this.password = data.password();
        this.type = data.userType();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
