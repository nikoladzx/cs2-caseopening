package com.blabla.projekat.dto;

public class CoinFlipRequest {
    private Long userId;
    private Double bet;
    private Boolean heads;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getBet() {
        return bet;
    }

    public void setBet(Double bet) {
        this.bet = bet;
    }

    public Boolean getHeads() {
        return heads;
    }

    public void setHeads(Boolean heads) {
        this.heads = heads;
    }
}
