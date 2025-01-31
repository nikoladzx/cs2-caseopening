package com.blabla.projekat.services.cases;

import com.blabla.projekat.dto.CaseDTO;
import com.blabla.projekat.dto.ItemDTO;
import com.blabla.projekat.dto.SkinDTO;
import com.blabla.projekat.entities.Case;
import com.blabla.projekat.entities.Item;
import com.blabla.projekat.entities.Skin;
import com.blabla.projekat.entities.User;
import com.blabla.projekat.enums.Type;
import com.blabla.projekat.repositories.CaseRepository;
import com.blabla.projekat.repositories.ItemRepository;
import com.blabla.projekat.repositories.SkinRepository;
import com.blabla.projekat.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class CaseServiceImpl implements CaseService{
    private static final Random random = new Random();
    private static final double BLUE_PROBABILITY = 79.92;
    private static final double PURPLE_PROBABILITY = 15.98;
    private static final double PINK_PROBABILITY = 3.2;
    private static final double RED_PROBABILITY = 0.64;
//    private static final double GOLD_PROBABILITY = 0.26;
//    private static final double FACTORY_NEW = 7;
//    private static final double MINIMAL_WEAR = 8;
//    private static final double FIELD_TESTED = 23;
//    private static final double WELL_WORN = 7;
//    private static final double BATTLE_SCARRED = 55;

    private final CaseRepository caseRepository;
    private final UserRepository userRepository;
    private final SkinRepository skinRepository;
    private final ItemRepository itemRepository;

    public CaseServiceImpl(CaseRepository caseRepository, UserRepository userRepository, SkinRepository skinRepository, ItemRepository itemRepository) {
        this.caseRepository = caseRepository;
        this.userRepository=userRepository;

        this.skinRepository = skinRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public SkinDTO unbox(Long caseId, Long userId) {
        Optional<Case> crate = caseRepository.findById(caseId);
        if (crate.isEmpty())
            return null;
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty())
            return null;
        if (user.get().getBalance() < crate.get().getPrice())
            return null;
        user.get().setBalance(user.get().getBalance()-crate.get().getPrice() );
        Type selectedType = determineItemType();
        List<Item> possibleItems = crate.get().getItems().stream()
                .filter(item -> item.getType() == selectedType)
                .collect(Collectors.toList());
        if (possibleItems.isEmpty()) {
            throw new IllegalStateException("No items found for rarity: " + selectedType);
        }
        Item item = possibleItems.get(random.nextInt(possibleItems.size()));
        Skin skin = new Skin();
        skin.setItem(item);
        skin.setUser(user.get());
        double rollStattrak = random.nextDouble();
        if (rollStattrak<0.1)
            skin.setStattrak(true);
        if (rollStattrak>0.1)
            skin.setStattrak(false);
        double rollCondition = random.nextDouble();
        skin.setCondition(rollCondition);
        double multiplier = 0.5;
        if (rollCondition < 0.07)
            multiplier = 3;
        if (rollCondition> 0.07 && rollCondition < 0.15)
            multiplier = 2;
        if (rollCondition>0.15 && rollCondition<0.38)
            multiplier = 1;
        if (rollCondition>0.38 && rollCondition<0.45)
            multiplier = 0.75;
        skin.setPrice(item.getPrice() * (rollStattrak < 0.1 ? 2 : 1) * multiplier);
        skinRepository.save(skin);
        SkinDTO skinDTO = new SkinDTO();
        skinDTO.setCondition(skin.getCondition());
        skinDTO.setType(skin.getItem().getType());
        skinDTO.setName(skin.getItem().getName());
        skinDTO.setPrice(skin.getPrice());
        skinDTO.setStattrak(skin.getStattrak());
        skinDTO.setImg(skin.getItem().getImg());
        return skinDTO;
    }

    @Override
    public List<CaseDTO> getCases() {
        List<Case> caseList = caseRepository.findAll();
        if (caseList.isEmpty())
            return null;
        return caseList.stream().map(Case::caseDTO).collect(Collectors.toList());
    }

    @Override
    public CaseDTO getCaseById(Long caseId) {
        if (caseId == null)
            return null;
        Optional<Case> crate = caseRepository.findById(caseId);
        if (crate.isEmpty())
            return null;
        CaseDTO newCase = new CaseDTO();
        newCase.setId(crate.get().getId());
        newCase.setName(crate.get().getName());
        newCase.setPrice(crate.get().getPrice());
        newCase.setImg(crate.get().getImg());
        newCase.setItems(crate.get().getItems().stream().map(Item::itemDTO).collect(Collectors.toList()));
        return newCase;
    }

    @Override
    public Boolean addCase(CaseDTO caseDTO) throws IOException {
        if (caseDTO == null)
            return false;
        if (caseDTO.getName() == null || caseDTO.getImg()== null || caseDTO.getPrice() == null)
            return false;
        Case newCase = new Case();
        newCase.setPrice(caseDTO.getPrice());
        newCase.setName(caseDTO.getName());
        newCase.setImg(caseDTO.getImg());
        caseRepository.save(newCase);
        return true;
    }

    @Override
    public Boolean addItem(ItemDTO itemDTO) throws IOException {
        if (itemDTO == null)
            return false;
        if (itemDTO.getCaseId() == null || itemDTO.getImg() == null || itemDTO.getPrice()==null || itemDTO.getName() == null || itemDTO.getType() == null)
            return null;
        Optional<Case> crate = caseRepository.findById(itemDTO.getCaseId());
        if (crate.isPresent()) {
            Item item = new Item();
            item.setImg(itemDTO.getImg());
            item.setPrice(itemDTO.getPrice());
            item.setType(itemDTO.getType());
            item.setName(itemDTO.getName());
            item.setCrate(crate.get());
            itemRepository.save(item);
            return true;
        }
        return false;
    }

    private Type determineItemType() {
        double roll = random.nextDouble() * 100;
        double cumulative = 0;

        cumulative += BLUE_PROBABILITY;
        if (roll < cumulative) return Type.BLUE;

        cumulative += PURPLE_PROBABILITY;
        if (roll < cumulative) return Type.PURPLE;

        cumulative += PINK_PROBABILITY;
        if (roll < cumulative) return Type.PINK;

        cumulative += RED_PROBABILITY;
        if (roll < cumulative) return Type.RED;

        return Type.GOLD;
    }
}
