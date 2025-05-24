package com.skills.indicator.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "activity")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer sport;

    private Integer reading;

    private Integer english;

    private Integer others;

    private Integer education;

    @Column(name = "write_off")
    private Integer writeOff;

    @ManyToOne()
    @JoinColumn(name="user_id")
    private User user;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

}
