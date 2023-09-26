package com.restapi.rest.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seq_user", sequenceName = "seq_user", allocationSize = 1)
    private long id;
    @Column
    @NotNull
    @Size(min = 2, max = 30, message = "Имя должно быть от 2 до 30 символов")
    private String firstName;
    @Column
    @NotNull
    @Size(min = 2, max = 30, message = "Фамилия должна быть от 2 до 30 символов")
    private String lastName;
    @Column
    @NotNull
    @Min(value = 0, message = "Возраст не может быть 0")
    @Max(value = 130, message = "Ты не можешь быть старше 122")
    private int age;
    @NotNull
    @Column(unique = true)
    @Email(message = "Email должен быть @mail или другой похожий вариант")
    private String email;
    @NotNull()
    private String password;
    @Fetch(FetchMode.JOIN)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;

    public User(String firstName, String lastName, int age, String email, String password, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPassword(String lastName) {
        this.password = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format("Id: %d, Username: %s, E-mail: ", this.id, this.firstName, this.email);
    }

    public String rolesToString() {
        StringBuilder str = new StringBuilder();

        this.roles.forEach(role -> str.append(role.getName()));

        return str.toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
