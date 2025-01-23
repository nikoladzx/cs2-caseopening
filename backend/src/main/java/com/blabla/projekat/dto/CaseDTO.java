package com.blabla.projekat.dto;

import org.springframework.web.multipart.MultipartFile;

public class CaseDTO {
    private String name;
    private MultipartFile img;
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
