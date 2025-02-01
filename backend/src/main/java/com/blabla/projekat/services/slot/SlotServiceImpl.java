package com.blabla.projekat.services.slot;

import com.blabla.projekat.dto.SlotDTO;
import com.blabla.projekat.dto.SlotItemDTO;
import com.blabla.projekat.dto.SlotRequest;
import com.blabla.projekat.dto.SlotResponse;
import com.blabla.projekat.entities.Item;
import com.blabla.projekat.entities.Slot;
import com.blabla.projekat.entities.SlotItem;
import com.blabla.projekat.entities.User;
import com.blabla.projekat.repositories.SlotItemRepository;
import com.blabla.projekat.repositories.SlotRepository;
import com.blabla.projekat.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SlotServiceImpl implements SlotService{

    private final UserRepository userRepository;
    private final SlotRepository slotRepository;
    private final SlotItemRepository slotItemRepository;

    public SlotServiceImpl(UserRepository userRepository, SlotRepository slotRepository, SlotItemRepository slotItemRepository) {
        this.userRepository = userRepository;
        this.slotRepository = slotRepository;
        this.slotItemRepository = slotItemRepository;
    }


    @Override
    public SlotResponse playSlot(SlotRequest slotRequest) {
        if (slotRequest == null)
            return null;
        Optional<User> optionalUser = userRepository.findById(slotRequest.getUserId());
        if (optionalUser.isEmpty())
            return null;
        Optional<Slot> optionalSlot = slotRepository.findById(slotRequest.getSlotId());
        if (optionalSlot.isEmpty())
            return null;
        if (optionalUser.get().getBalance()<slotRequest.getBet() || slotRequest.getBet()<0.01)
            return null;
        Double multiplier = 0.0;
        SlotResponse response = new SlotResponse();
        List<SlotItem> items = new ArrayList<>();
        Random random = new Random();
        for (int i =0; i<3; i++) {
            int rng = random.nextInt(optionalSlot.get().getItems().size());
            items.add(optionalSlot.get().getItems().get(rng));
        }
        response.setItems(items.stream().map(item -> {
            SlotItemDTO slotItemDTO = new SlotItemDTO();
            slotItemDTO.setId(item.getId());
            slotItemDTO.setImagepath(item.getImagepath());
            return slotItemDTO;
        }).toList());

        if (items.get(0) == items.get(1) && items.get(1) == items.get(2))
        {
            multiplier= items.get(0).getMultiplier() * items.get(1).getMultiplier();
            response.setWin(true);
            response.setAmount(slotRequest.getBet()*multiplier);
        }
        else if (items.get(0) == items.get(1))
        {
            multiplier= items.get(0).getMultiplier();
            response.setWin(true);
            response.setAmount(slotRequest.getBet()*multiplier);
        }
        else if (items.get(2) == items.get(1))
        {
            multiplier= items.get(2).getMultiplier();
            response.setWin(true);
            response.setAmount(slotRequest.getBet()*multiplier);
        }
        else {
            response.setWin(false);
            response.setAmount(slotRequest.getBet());
        }
        optionalUser.get().setBalance(optionalUser.get().getBalance() + multiplier*slotRequest.getBet() - slotRequest.getBet());
        userRepository.save(optionalUser.get());
        return response;
    }

    @Override
    public List<SlotDTO> getSlots() {
        List<Slot> slotList = slotRepository.findAll();
        List<SlotDTO> slotDTOList = new ArrayList<>();
        slotDTOList = slotList.stream().map(slot -> {
            SlotDTO slotDTO = new SlotDTO();
            slotDTO.setId(slot.getId());
            slotDTO.setName(slot.getName());
            slotDTO.setImagepath(slot.getImagepath());
            return slotDTO;
        }).toList();
        return slotDTOList;
    }

    @Override
    public List<SlotItemDTO> getSlotItems(Long slotId) {
        if (slotId == null)
            return null;
        Optional<Slot> optionalSlot = slotRepository.findById(slotId);
        if (optionalSlot.isPresent())
        {
            List<SlotItem> slotItemList = optionalSlot.get().getItems();
            List<SlotItemDTO> slotItemDTOList = slotItemList.stream().map(slotItem -> {
                SlotItemDTO slotItemDTO = new SlotItemDTO();
                slotItemDTO.setId(slotItem.getId());
                slotItemDTO.setImagepath(slotItem.getImagepath());
                return slotItemDTO;
            }).toList();
            return slotItemDTOList;
        }
        return null;
    }

    @Override
    public Boolean addSlot(SlotDTO slotDTO) {
        if (slotDTO == null)
            return null;
        if (slotDTO.getImagepath() == null || slotDTO.getName() == null)
            return null;
        Slot slot = new Slot();
        slot.setImagepath(slotDTO.getImagepath());
        slot.setName(slotDTO.getName());
        slotRepository.save(slot);
        return true;
    }

    @Override
    public Boolean addSlotItem(SlotItemDTO slotItemDTO, Long slotId) {
        if (slotItemDTO == null)
            return null;
        if (slotItemDTO.getImagepath() ==null || slotItemDTO.getMultiplier() == null || slotId == null)
            return null;
        Optional<Slot> optionalSlot = slotRepository.findById(slotId);
        if (optionalSlot.isPresent())
        {
            SlotItem slotItem = new SlotItem();
            slotItem.setSlot(optionalSlot.get());
            return true;
        }
        return false;
    }
}
