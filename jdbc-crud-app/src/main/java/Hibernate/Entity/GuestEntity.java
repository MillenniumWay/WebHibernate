package Hibernate.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "guest", schema = "public", catalog = "postgres")
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
@Builder
public class GuestEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "bank_id")
    private int bankId;

    public GuestEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
