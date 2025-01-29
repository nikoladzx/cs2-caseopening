package com.blabla.projekat.dto;

import com.blabla.projekat.enums.Type;
import org.springframework.web.multipart.MultipartFile;

public class SkinDTO {
    private Long id;
    private byte[] img;
    private String name;
    private Double price;
    private Type type;
    private Double condition;
    private Boolean stattrak;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
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

    public Double getCondition() {
        return condition;
    }

    public void setCondition(Double condition) {
        this.condition = condition;
    }

    public Boolean getStattrak() {
        return stattrak;
    }

    public void setStattrak(Boolean stattrak) {
        this.stattrak = stattrak;
    }
}
