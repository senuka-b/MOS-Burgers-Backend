package edu.icet.senuka.service;

import edu.icet.senuka.dto.Item;

import java.util.List;

public interface ItemService {
    Item addItem(Item item);
    Item updateItem(Item item);
    Boolean deleteItem(Integer id);
    List<Item> getAll();
}
