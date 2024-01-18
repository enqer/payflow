package com.example.payflow.service;

import com.example.payflow.dto.CardDTO;
import com.example.payflow.dto.mapper.CardDTOMapper;
import com.example.payflow.model.AccountNumber;
import com.example.payflow.model.Card;
import com.example.payflow.model.CardDetails;
import com.example.payflow.repository.AccountNumberRepository;
import com.example.payflow.repository.CardDetailsRepository;
import com.example.payflow.repository.CardRepository;
import com.example.payflow.util.NumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CardService {

    private final CardRepository cardRepository;
    private final AccountNumberRepository accountNumberRepository;
    private final CardDetailsRepository cardDetailsRepository;
    private final CardDTOMapper cardDTOMapper;

    public List<CardDTO> getCardByAccountId(Long id){
        return accountNumberRepository.findById(id).get().getCards()
                .stream()
                .map(cardDTOMapper).toList();
    }
    public CardDTO createCard(Long id){
        LocalDate currentDate = LocalDate.now();
        Optional<AccountNumber> ac = accountNumberRepository.findById(id);
        if (ac.isPresent()) {
            var c = Card.builder()
                    .validDate(currentDate.plusYears(4))
                    .cardNumber(NumberGenerator.generateAccountNumber())
                    .cvv(NumberGenerator.generateCVV())
                    .accountNumberCard(ac.orElseThrow())
                    .build();
            var cd = CardDetails.builder()
                    .active(false)
                    .blocked(false)
                    .idCard(c)
                    .pin(null)
                    .build();
            cardRepository.save(c);
            cardDetailsRepository.save(cd);
            CardDTO cardDTO = cardDTOMapper.apply(c);
            return cardDTO;
        }
        return null;
    }
    public Card removeCardById(Long id) {
        Optional<Card> c = cardRepository.findById(id);
        if(c.isPresent()){
            cardRepository.deleteById(id);
            return c.orElseThrow();
        }
        return null;
    }
}
