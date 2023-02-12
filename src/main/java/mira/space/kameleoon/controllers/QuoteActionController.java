package mira.space.kameleoon.controllers;

import mira.space.kameleoon.exceptions.NoSuchEntityException;
import mira.space.kameleoon.models.Quote;
import mira.space.kameleoon.models.Vote;
import mira.space.kameleoon.models.dto.QuoteDetailsDto;
import mira.space.kameleoon.models.repositories.QuoteRepository;
import mira.space.kameleoon.models.repositories.VoteRepository;
import mira.space.kameleoon.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1/quotes")
public class QuoteActionController {

    @Autowired
    VoteRepository voteRepository;
    @Autowired
    QuoteRepository quoteRepository;
    @Autowired
    VoteService voteService;

    @GetMapping("/random")
    public ResponseEntity<Quote> getRandomQuote() {
        Optional<Quote> randomQuote = quoteRepository.getRandom();
        if (randomQuote.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(randomQuote.get());
    }

    @GetMapping("/top")
    public ResponseEntity<List<Quote>> getTopQuotes() {
        Optional<List<Quote>> topQuotes = quoteRepository.findTop();
        if (topQuotes.isEmpty() || topQuotes.get().isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(topQuotes.get());
    }

    @GetMapping("/flop")
    public ResponseEntity<List<Quote>> getFlopQuotes() {
        Optional<List<Quote>> topQuotes = quoteRepository.findFlop();
        if (topQuotes.isEmpty() || topQuotes.get().isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(topQuotes.get());
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<QuoteDetailsDto> getQuoteDetails(@PathVariable Long id) {
        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new NoSuchEntityException(id));
        QuoteDetailsDto quoteDto = new QuoteDetailsDto(
                quote.getContent(),
                quote.getCreateTs(),
                quote.getVote().getRate(),
                quote.getOwner()
        );
        return ResponseEntity.ok(quoteDto);
    }

    @GetMapping("/{id}/graph")
    public ResponseEntity<Map<Date, Integer>> getVoteGraph(@PathVariable Long id) {
        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new NoSuchEntityException(id));
        return ResponseEntity.ok(quote.getVote().getUpdateTs());
    }

    @PutMapping("/{id}/vote")
    public ResponseEntity<Quote> voteOnQuote(@PathVariable Long id,
                                             @RequestParam(name = "vote") String voteAction) {
        Vote vote = voteRepository.findById(id).orElseThrow(() -> new NoSuchEntityException(id));
        voteService.updateRate(vote, voteAction);
        return ResponseEntity.ok().build();
    }
}
