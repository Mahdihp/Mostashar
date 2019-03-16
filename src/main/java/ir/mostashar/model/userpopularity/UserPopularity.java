package ir.mostashar.model.userpopularity;

import ir.mostashar.model.user.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "userpopularitys")
public class UserPopularity {

    /**
     * برای انتخاب مشاورین جهت خدمت کاربر لیست مشاورین انتخابی
     * خود را به سمت سرور میفرستد
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userpopu", nullable = false)
    private User userPopu;

}
