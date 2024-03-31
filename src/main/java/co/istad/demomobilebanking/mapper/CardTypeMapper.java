package co.istad.demomobilebanking.mapper;

import co.istad.demomobilebanking.domain.CardType;
import co.istad.demomobilebanking.feature.cardtype.dto.CardTypeResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardTypeMapper {
    List<CardTypeResponse> toListCardTypesResponse(List<CardType> cardTypeList);

    CardTypeResponse toCardTypeResponse(CardType cardType);
}
