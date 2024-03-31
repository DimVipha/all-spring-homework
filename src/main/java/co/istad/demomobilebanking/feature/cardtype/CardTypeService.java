package co.istad.demomobilebanking.feature.cardtype;

import co.istad.demomobilebanking.feature.cardtype.dto.CardTypeResponse;

import java.util.List;

public interface CardTypeService {
    List<CardTypeResponse> findAllCardTypes();
    CardTypeResponse findCardTypeByName(String name);
}
