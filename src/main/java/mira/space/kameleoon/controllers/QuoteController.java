package mira.space.kameleoon.controllers;

import mira.space.kameleoon.exceptions.NotSuchQuoteException;
import mira.space.kameleoon.models.Quote;
import mira.space.kameleoon.models.User;
import mira.space.kameleoon.models.Vote;
import mira.space.kameleoon.models.repositories.QuoteRepository;
import mira.space.kameleoon.models.repositories.UserRepository;
import mira.space.kameleoon.services.QuoteService;
import mira.space.kameleoon.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/quotes")
public class QuoteController {

    @Autowired
    QuoteRepository quoteRepository;
    @Autowired
    QuoteService quoteService;

    @GetMapping("/{id}")
    public ResponseEntity<Quote> getQuote(@PathVariable Long id) {
        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new NotSuchQuoteException(id));
        return ResponseEntity.ok(quote);
    }

    @PostMapping
    public ResponseEntity<Quote> addQuote(@RequestBody Quote quote) {
        if (!quoteService.isQuoteValid(quote)) {
            return ResponseEntity.badRequest().build();
        }
        quote = quoteService.getWithNecessaryProperties(quote);
        return ResponseEntity.ok(quoteRepository.save(quote));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quote> updateQuote(@PathVariable Long id, @RequestBody Quote quote) {
        if (!quoteService.isQuoteValid(quote)) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Quote> optionalQuote = quoteRepository.findById(id);
        if (optionalQuote.isEmpty()) {
            quote = quoteService.getWithNecessaryProperties(quote);
            return ResponseEntity.ok(quoteRepository.save(quote));
        }
        int updatedRecords = quoteRepository.updateQuote(quote.getContent(), new Date(), id);
        if (updatedRecords != 1) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Quote> deleteQuote(@PathVariable Long id) {
        quoteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
