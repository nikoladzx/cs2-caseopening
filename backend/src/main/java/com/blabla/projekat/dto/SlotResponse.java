package com.blabla.projekat.dto;

import com.blabla.projekat.entities.SlotItem;

import java.util.List;

public class SlotResponse {
    private Double amount;
    private Boolean win;
    private List<SlotItemDTO> items;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getWin() {
        return win;
    }

    public void setWin(Boolean win) {
        this.win = win;
    }

    public List<SlotItemDTO> getItems() {
        return items;
    }

    public void setItems(List<SlotItemDTO> items) {
        this.items = items;
    }
}
