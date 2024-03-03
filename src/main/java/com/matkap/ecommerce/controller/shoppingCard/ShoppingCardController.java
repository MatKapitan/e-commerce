package com.matkap.ecommerce.controller.shoppingCard;

import com.matkap.ecommerce.dto.requestDto.shoppingCard.ShoppingCardRequestDto;
import com.matkap.ecommerce.model.shopingCard.ShoppingCard;
import com.matkap.ecommerce.service.shopingCard.ShoppingCardService;
import jakarta.validation.Valid;
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
    public ResponseEntity<ShoppingCard> createShoppingCard(@Valid @RequestBody ShoppingCardRequestDto shoppingCardRequestDto){
        ShoppingCard shoppingCard = shoppingCardService.createShoppingCard(shoppingCardRequestDto);
        return new ResponseEntity<>(shoppingCard, HttpStatus.OK);
    }

    @GetMapping("/{shoppingCardId}")
    public ResponseEntity<ShoppingCard> getShoppingCardById(@PathVariable Long shoppingCardId){
        ShoppingCard shoppingCard = shoppingCardService.getShoppingCardById(shoppingCardId);
        return new ResponseEntity<>(shoppingCard, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ShoppingCard>> getAllShoppingCards(){
        List<ShoppingCard> shoppingCards = shoppingCardService.getShoppingCards();
        return new ResponseEntity<>(shoppingCards, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{shoppingCardId}")
    public ResponseEntity<Void> deleteShoppingCard(@PathVariable Long shoppingCardId){
        shoppingCardService.deleteShoppingCard(shoppingCardId);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/edit/{shoppingCardId}")
    public ResponseEntity<ShoppingCard> editShoppingCard(@PathVariable Long shoppingCardId,
                                                         @Valid @RequestBody ShoppingCardRequestDto shoppingCardRequestDto){
        ShoppingCard shoppingCard = shoppingCardService.editShoppingCard(shoppingCardId, shoppingCardRequestDto);
        return new ResponseEntity<>(shoppingCard, HttpStatus.OK);
    }


}
