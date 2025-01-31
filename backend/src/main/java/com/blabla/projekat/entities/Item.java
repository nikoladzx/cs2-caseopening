package com.blabla.projekat.entities;

import com.blabla.projekat.dto.ItemDTO;
import com.blabla.projekat.enums.Type;
import jakarta.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(columnDefinition = "BYTEA")
//    private byte[] img;
    private String name;
    private String img;
    private Double price;
    private Type type;
    @ManyToOne
    @JoinColumn(name = "case_id")
    private Case crate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public byte[] getImg() {
//        return img;
//    }
//
//    public void setImg(byte[] img) {
//        this.img = img;
//    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
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

    public Case getCrate() {
        return crate;
    }

    public void setCrate(Case crate) {
        this.crate = crate;
    }

    public ItemDTO itemDTO()
    {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(id);
        itemDTO.setCaseId(getCrate().getId());
       // itemDTO.setReturnedImg(getImg());
        itemDTO.setImg(img);
        itemDTO.setType(getType());
        itemDTO.setPrice(getPrice());
        itemDTO.setName(getName());
        return itemDTO;
    }
}
