package mira.space.kameleoon.services;

import mira.space.kameleoon.models.Quote;

public interface QuoteService {
    boolean isQuoteValid(Quote quote);
    Quote getWithNecessaryProperties(Quote quote);
}
