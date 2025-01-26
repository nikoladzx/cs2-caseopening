package com.blabla.projekat.dto;

import com.blabla.projekat.enums.Type;
import org.springframework.web.multipart.MultipartFile;

public class ItemDTO {
    private MultipartFile img;
    private byte[] returnedImg;
    private String name;
    private Double price;
    private Type type;
    private Long caseId;

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public byte[] getReturnedImg() {
        return returnedImg;
    }

    public void setReturnedImg(byte[] returnedImg) {
        this.returnedImg = returnedImg;
    }
}
