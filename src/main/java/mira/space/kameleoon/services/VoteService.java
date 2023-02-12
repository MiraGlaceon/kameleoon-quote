package mira.space.kameleoon.services;

import mira.space.kameleoon.models.Vote;

public interface VoteService {
    Vote createAndPersistVote();
    void updateRate(Vote vote, String action);
}
