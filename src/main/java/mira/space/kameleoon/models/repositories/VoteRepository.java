package mira.space.kameleoon.models.repositories;

import mira.space.kameleoon.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Transactional
    @Modifying
    @Query("update Vote v set v.rate = ?1 where v.id = ?2")
    int updateVote(Integer rate, Long id);
}
