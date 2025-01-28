package com.blabla.projekat.entities;

import com.blabla.projekat.dto.SkinDTO;
import com.blabla.projekat.enums.Type;
import jakarta.persistence.*;

@Entity
public class Skin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Double price;
    private Double condition;
    private Boolean stattrak;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public SkinDTO skinDTO()
    {
        SkinDTO skinDTO = new SkinDTO();
        skinDTO.setName(item.getName());
        skinDTO.setType(item.getType());
        skinDTO.setImg(item.getImg());
        skinDTO.setCondition(condition);
        skinDTO.setStattrak(stattrak);
        skinDTO.setPrice(price);
        return skinDTO;
    }

}
