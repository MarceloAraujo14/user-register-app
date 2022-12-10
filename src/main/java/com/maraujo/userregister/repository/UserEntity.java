package com.maraujo.userregister.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;
import java.util.Objects;

@Builder
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(generator = "user_register_seq")
    @SequenceGenerator(sequenceName = "user_register_seq", name = "user_register_seq")
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String cpf;
    private String street;
    private String streetNumber;
    private String city;
    private String state;
    private String postalCode;
    private String phone;
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserEntity that = (UserEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
