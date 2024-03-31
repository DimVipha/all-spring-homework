package co.istad.demomobilebanking.feature.cardtype;

import co.istad.demomobilebanking.domain.CardType;
import co.istad.demomobilebanking.feature.cardtype.dto.CardTypeResponse;
import co.istad.demomobilebanking.mapper.CardTypeMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardTypeServiceImpl implements CardTypeService{
    private final CardTypeRepository cardTypeRepository;
    private final CardTypeMapper cardTypeMapper;
    @Override
    public List<CardTypeResponse> findAllCardTypes() {
        List<CardType> cardType = cardTypeRepository.findAll();
        return cardTypeMapper.toListCardTypesResponse(cardType);
    }

    @Override
    public CardTypeResponse findCardTypeByName(String name) {
        CardType cardType = cardTypeRepository.findByNameIgnoreCase(name).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Card type does not existing"
                )
        );

        return cardTypeMapper.toCardTypeResponse(cardType);
    }
}
