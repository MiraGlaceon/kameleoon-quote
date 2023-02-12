package mira.space.kameleoon.models.repositories;

import mira.space.kameleoon.models.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Transactional
    @Modifying
    @Query("update Quote q set q.content = ?1, q.updateTs = ?2 where q.id = ?3")
    int updateQuote(String content, Date updateTs, Long id);

    @Query("select q from Quote q order by RAND() limit 1")
    Optional<Quote> getRandom();

    @Query("select q from Quote q " +
            "join q.vote v " +
            "order by v.rate desc limit 10")
    Optional<List<Quote>> findTop();

    @Query("select q from Quote q " +
            "join q.vote v " +
            "order by v.rate asc limit 10")
    Optional<List<Quote>> findFlop();
}
