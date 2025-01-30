package com.blabla.projekat.services.slot;

import com.blabla.projekat.dto.SlotDTO;
import com.blabla.projekat.dto.SlotItemDTO;
import com.blabla.projekat.dto.SlotRequest;
import com.blabla.projekat.dto.SlotResponse;
import com.blabla.projekat.entities.SlotItem;

import java.util.List;

public interface SlotService {
    SlotResponse playSlot(SlotRequest slotRequest);
    List<SlotDTO> getSlots();
    List<SlotItemDTO> getSlotItems(Long slotId);
}
