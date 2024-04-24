package com.matkap.ecommerce.controller.shoppingCard;

import com.matkap.ecommerce.dto.requestDto.shoppingCard.ShoppingCardRequestDto;
import com.matkap.ecommerce.exception.ErrorResponse;
import com.matkap.ecommerce.model.shopingCard.ShoppingCard;
import com.matkap.ecommerce.service.shopingCard.ShoppingCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/shopping-card", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Shopping card Controller", description = "Create, retrieve, delete and edit shopping cards" )
public class ShoppingCardController {

    private final ShoppingCardService shoppingCardService;

    public ShoppingCardController(ShoppingCardService shoppingCardService) {
        this.shoppingCardService = shoppingCardService;
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of shopping cards"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Create Shopping card", description = "Creates a shopping card from the provided payload")
    @PostMapping("/create")
    public ResponseEntity<ShoppingCard> createShoppingCard(@Valid @RequestBody ShoppingCardRequestDto shoppingCardRequestDto){
        ShoppingCard shoppingCard = shoppingCardService.createShoppingCard(shoppingCardRequestDto);
        return new ResponseEntity<>(shoppingCard, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Shopping card doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of shopping card", content = @Content(schema = @Schema(implementation = ShoppingCard.class))),
    })
    @Operation(summary = "Retrieve Shopping card", description = "Returns a shopping card based on id" )
    @GetMapping("/{shoppingCardId}")
    public ResponseEntity<ShoppingCard> getShoppingCardById(@PathVariable Long shoppingCardId){
        ShoppingCard shoppingCard = shoppingCardService.getShoppingCardById(shoppingCardId);
        return new ResponseEntity<>(shoppingCard, HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "Successful retrieval of shopping card", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ShoppingCard.class))))
    @Operation(summary = "Retrieve Shopping cards", description = "Provides a list of all shopping cards" )
    @GetMapping("/all")
    public ResponseEntity<List<ShoppingCard>> getAllShoppingCards(){
        List<ShoppingCard> shoppingCards = shoppingCardService.getShoppingCards();
        return new ResponseEntity<>(shoppingCards, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Shopping card doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful deletion of shopping card")
    })
    @Operation(summary = "Deletes Shopping card", description = "Deletes a shopping card based on id" )
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
