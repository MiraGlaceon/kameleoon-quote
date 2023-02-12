package mira.space.kameleoon.services.impl;

import mira.space.kameleoon.exceptions.EntityUpdateException;
import mira.space.kameleoon.exceptions.IncorrectParamException;
import mira.space.kameleoon.models.Vote;
import mira.space.kameleoon.models.repositories.VoteRepository;
import mira.space.kameleoon.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteRepository voteRepository;

    private static final String UP_VOTE = "up";
    private static final String DOWN_VOTE = "down";

    @Override
    public Vote createAndPersistVote() {
        Vote vote = new Vote();
        Map<Date, Integer> voteUpdates = new HashMap<>() {{
            put(new Date(), 0);
        }};
        vote.setUpdateTs(voteUpdates);
        return voteRepository.save(vote);
    }


    /***
     * Update Vote properties depending on action
     *
     * @throws IncorrectParamException if action not found
     * @throws EntityUpdateException if were updated zero records in db, but must be at least one
     */
    @Override
    public void updateRate(Vote vote, String action) {
        Integer rate;
        switch (action.toLowerCase()) {
            case UP_VOTE -> rate = 1;
            case DOWN_VOTE -> rate = -1;
            default -> throw new IncorrectParamException(action);
        }
        vote.setRate(vote.getRate() + rate);
        vote.getUpdateTs().put(new Date(), vote.getRate());
        int updatedVotes = voteRepository.updateVote(vote.getRate(), vote.getId());
        if (updatedVotes == 0) {
            throw new EntityUpdateException(vote.getClass().getName(), vote.getId());
        }
    }
}
