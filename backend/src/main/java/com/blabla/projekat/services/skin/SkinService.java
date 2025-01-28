package com.blabla.projekat.services.skin;

import com.blabla.projekat.dto.SkinDTO;
import com.blabla.projekat.entities.Skin;

import java.util.List;

public interface SkinService {
    List<SkinDTO> getSkins(Long userId);
}
