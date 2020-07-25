package org.mpa.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "trainWorkouts")
public class Workout {

    @Id
    @Column(columnDefinition="serial primary key")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "username", referencedColumnName = "username", columnDefinition = "STRING")
    private User username;

    @Column
    @Temporal(TemporalType.DATE)
    private Date wDate;

    @Column
    private String type;

    @Column
    private String description;

    @Column
    private boolean meditation;
}
