package es.nextdigital.demo.mapper;

import es.nextdigital.demo.entity.Card;
import es.nextdigital.dto.CardStatusResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CardMapper {

    @Mapping(source = "id", target = "cardId")
    @Mapping(source = "active", target = "isActive")
    CardStatusResponseDto toDto(Card card);
}

