package co.istad.demomobilebanking.feature.cardtype;

import co.istad.demomobilebanking.feature.cardtype.dto.CardTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cardTypes")
public class CardTypeController {
    private final CardTypeService cardTypeService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<CardTypeResponse> findAllCardTypes() {
        return cardTypeService.findAllCardTypes();
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    CardTypeResponse findCardTypeByName(@PathVariable String name) {
        return cardTypeService.findCardTypeByName(name);
    }
}