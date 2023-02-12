package mira.space.kameleoon.controllers;

import mira.space.kameleoon.exceptions.NoSuchEntityException;
import mira.space.kameleoon.exceptions.EntityUpdateException;
import mira.space.kameleoon.models.Quote;
import mira.space.kameleoon.models.repositories.QuoteRepository;
import mira.space.kameleoon.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new NoSuchEntityException(id));
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
        if (updatedRecords == 0) {
            throw new EntityUpdateException(quote.getClass().getName(), id);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Quote> deleteQuote(@PathVariable Long id) {
        quoteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
