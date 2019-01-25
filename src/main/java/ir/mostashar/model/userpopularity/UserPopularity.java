package ir.mostashar.model.userpopularity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.mostashar.model.user.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "userpopularitys")
public class UserPopularity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userpopularid", nullable = false)
    private User UserPopular;

}