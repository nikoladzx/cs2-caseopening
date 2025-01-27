package com.blabla.projekat.entities;

import com.blabla.projekat.dto.CaseDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "cases")
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "BYTEA")
    private byte[] img;
    private Double price;
    @OneToMany(mappedBy = "crate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public CaseDTO caseDTO() {
        CaseDTO caseDTO = new CaseDTO();
        caseDTO.setId(id);
        caseDTO.setName(name);
        caseDTO.setReturnedImg(getImg());
        caseDTO.setPrice(getPrice());
        caseDTO.setItems(getItems().stream()
                .map(Item::itemDTO)
                .collect(Collectors.toList()));
        return caseDTO;
    }

}
