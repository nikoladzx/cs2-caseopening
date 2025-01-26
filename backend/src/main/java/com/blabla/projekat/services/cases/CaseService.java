package com.blabla.projekat.services.cases;

import com.blabla.projekat.dto.CaseDTO;
import com.blabla.projekat.dto.ItemDTO;
import com.blabla.projekat.dto.SkinDTO;
import com.blabla.projekat.entities.Item;
import com.blabla.projekat.entities.Skin;

import java.io.IOException;
import java.util.List;

public interface CaseService {
    SkinDTO unbox(Long caseId, Long userId);
    List<CaseDTO> getCases();
    CaseDTO getCaseById(Long caseId);
    Boolean addCase(CaseDTO caseDTO) throws IOException;
    Boolean addItem(ItemDTO itemDTO) throws IOException;
}
