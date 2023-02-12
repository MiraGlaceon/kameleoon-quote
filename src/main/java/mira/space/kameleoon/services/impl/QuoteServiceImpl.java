package mira.space.kameleoon.services.impl;

import mira.space.kameleoon.exceptions.UserSessionIdException;
import mira.space.kameleoon.models.Quote;
import mira.space.kameleoon.models.User;
import mira.space.kameleoon.models.Vote;
import mira.space.kameleoon.models.repositories.UserRepository;
import mira.space.kameleoon.services.QuoteService;
import mira.space.kameleoon.services.VoteService;
import mira.space.kameleoon.utils.ValidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Date;
import java.util.Optional;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    VoteService voteService;

    @Override
    public boolean isQuoteValid(Quote quote) {
        if (quote == null) {
            return false;
        }
        ValidUtils.checkValid("content", quote.getContent());
        return true;
    }

    @Override
    public Quote getWithNecessaryProperties(Quote quote) {
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        Optional<User> owner = userRepository.findBySessionId(sessionId);
        if (owner.isEmpty()) {
            throw new UserSessionIdException(); // user must be logged in
        }
        quote.setOwner(owner.get());
        quote.setUpdateTs(new Date());
        Vote vote = voteService.createAndPersistVote();
        quote.setVote(vote);
        return quote;
    }
}
