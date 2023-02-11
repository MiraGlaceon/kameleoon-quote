package mira.space.kameleoon.services.impl;

import mira.space.kameleoon.models.Vote;
import mira.space.kameleoon.models.repositories.VoteRepository;
import mira.space.kameleoon.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteRepository voteRepository;

    @Override
    public Vote createAndPersistVote() {
        Vote vote = new Vote();
        List<Date> voteUpdates = new ArrayList<>() {{
            add(new Date());
        }};
        vote.setUpdateTs(voteUpdates);
        return voteRepository.save(vote);
    }
}
