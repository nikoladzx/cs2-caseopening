package com.blabla.projekat.repositories;

import com.blabla.projekat.dto.SkinDTO;
import com.blabla.projekat.entities.Skin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkinRepository extends JpaRepository<Skin,Long> {
    List<Skin> findByUserId(Long userId);
}
