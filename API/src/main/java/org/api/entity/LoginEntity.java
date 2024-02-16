package org.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "login", schema = "atopycare_schema")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_id")
    private Long loginId;

    @OneToOne
    @JoinColumn(name = "user_table_id", nullable = false)
    private UserEntity user;

    public LoginEntity(UserEntity user) {
        this.user = user;
    }

    public static LoginEntity of(UserEntity user) {
        return new LoginEntity(user);
    }

}

