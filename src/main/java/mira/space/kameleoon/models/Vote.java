package mira.space.kameleoon.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "positive_rate")
    private Integer positiveRate = 0;

    @Column(name = "negative_rate")
    private Integer negativeRate = 0;

    @Column(name = "update_ts", nullable = false)
    @ElementCollection
    @CollectionTable(name = "updates", joinColumns = @JoinColumn(name = "vote_id"))
    private List<Date> updateTs;
}
