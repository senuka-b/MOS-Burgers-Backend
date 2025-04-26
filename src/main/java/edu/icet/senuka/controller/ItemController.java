package edu.icet.senuka.controller;

import edu.icet.senuka.dto.Item;
import edu.icet.senuka.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService service;

    @GetMapping("/all")
    public ResponseEntity<List<Item>> getAll() {
        List<Item> all = service.getAll();
        if (all.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(all);
    }

    @PostMapping("/create")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item addItem = service.addItem(item);


        return addItem == null ? ResponseEntity.badRequest().build()
            : ResponseEntity.status(HttpStatus.CREATED).body(addItem);
    }

    @PutMapping("/update")
    public ResponseEntity<Item> updateItem(@RequestBody Item item) {
        Item updatedItem = service.updateItem(item);
        return updatedItem != null ? ResponseEntity.ok(updatedItem)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteItem(@RequestParam Integer id) {
        boolean deleted = service.deleteItem(id);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
