package mira.space.kameleoon.models.dto;

import mira.space.kameleoon.models.User;

import java.util.Date;

public record QuoteDetailsDto(String content, Date createTs, Integer rate, User owner) {
}
