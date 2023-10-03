package com.example.bank.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;


@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true, exclude = {"passCode"})
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "pass_code")
    private Integer passCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Account> accounts;

    public Client(Integer id) {
        this.id = id;
    }
}
