package edu.icet.senuka.service.impl;

import edu.icet.senuka.dto.Item;
import edu.icet.senuka.entity.ItemEntity;
import edu.icet.senuka.repository.ItemRepository;
import edu.icet.senuka.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ModelMapper mapper;
    private final ItemRepository repository;

    @Override
    public Item addItem(Item item) {
        return mapper.map(repository.save(mapper.map(item, ItemEntity.class)), Item.class);
    }

    @Override
    public Item updateItem(Item item) {
        if (!repository.existsById(item.getId())) return null;

        return addItem(item);
    }

    @Override
    public Boolean deleteItem(Integer id) {
        if (!repository.existsById(id)) return false;

        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Item> getAll() {
        return repository.findAll()
                .stream()
                .map(itemEntity -> mapper.map(itemEntity, Item.class))
                .toList();
    }
}
