package com.matkap.ecommerce.controller.shoppingCard;

import com.matkap.ecommerce.dto.requestDto.shoppingCard.ShoppingCardRequestDto;
import com.matkap.ecommerce.model.shopingCard.ShoppingCard;
import com.matkap.ecommerce.service.shopingCard.ShoppingCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-card")
public class ShoppingCardController {

    private final ShoppingCardService shoppingCardService;

    public ShoppingCardController(ShoppingCardService shoppingCardService) {
        this.shoppingCardService = shoppingCardService;
    }

    @PostMapping("/create")
    public ResponseEntity<ShoppingCard> createShoppingCard(@RequestBody ShoppingCardRequestDto shoppingCardRequestDto){
        ShoppingCard shoppingCard = shoppingCardService.createShoppingCard(shoppingCardRequestDto);
        return new ResponseEntity<>(shoppingCard, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCard> getShoppingCardById(@PathVariable Long id){
        ShoppingCard shoppingCard = shoppingCardService.getShoppingCardById(id);
        return new ResponseEntity<>(shoppingCard, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ShoppingCard>> getAllShoppingCards(){
        List<ShoppingCard> shoppingCards = shoppingCardService.getShoppingCards();
        return new ResponseEntity<>(shoppingCards, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteShoppingCard(@PathVariable Long id){
        shoppingCardService.deleteShoppingCard(id);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ShoppingCard> editShoppingCard(@PathVariable Long id,
                                                         @RequestBody ShoppingCardRequestDto shoppingCardRequestDto){
        ShoppingCard shoppingCard = shoppingCardService.editShoppingCard(id, shoppingCardRequestDto);
        return new ResponseEntity<>(shoppingCard, HttpStatus.OK);
    }


}
