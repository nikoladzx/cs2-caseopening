package com.blabla.projekat.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class CaseDTO {
    private Long id;
    private String name;
    //private MultipartFile img;
//    private byte[] returnedImg;
    private String img;
    private Double price;
    private List<ItemDTO> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public MultipartFile getImg() {
//        return img;
//    }
//
//    public void setImg(MultipartFile img) {
//        this.img = img;
//    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

//    public byte[] getReturnedImg() {
//        return returnedImg;
//    }
//
//    public void setReturnedImg(byte[] returnedImg) {
//        this.returnedImg = returnedImg;
//    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }
}
