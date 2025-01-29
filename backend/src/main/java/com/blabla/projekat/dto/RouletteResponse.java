package com.blabla.projekat.dto;

public class RouletteResponse {
    private Long drawnNumber;
    private String drawnColor;
    private Boolean win;

    public Long getDrawnNumber() {
        return drawnNumber;
    }

    public void setDrawnNumber(Long drawnNumber) {
        this.drawnNumber = drawnNumber;
    }

    public String getDrawnColor() {
        return drawnColor;
    }

    public void setDrawnColor(String drawnColor) {
        this.drawnColor = drawnColor;
    }

    public Boolean getWin() {
        return win;
    }

    public void setWin(Boolean win) {
        this.win = win;
    }
}
