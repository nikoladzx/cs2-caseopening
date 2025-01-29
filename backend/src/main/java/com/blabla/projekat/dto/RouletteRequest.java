package com.blabla.projekat.dto;

public class RouletteRequest {
    private Double bet;
    private Long userId;
    private String betOption;

    public Double getBet() {
        return bet;
    }

    public void setBet(Double bet) {
        this.bet = bet;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBetOption() {
        return betOption;
    }

    public void setBetOption(String betOption) {
        this.betOption = betOption;
    }
}
