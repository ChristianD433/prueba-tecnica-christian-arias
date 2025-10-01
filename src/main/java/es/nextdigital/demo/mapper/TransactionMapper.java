package es.nextdigital.demo.mapper;

import es.nextdigital.demo.entity.Transaction;
import es.nextdigital.dto.MovementDtoDto;
import es.nextdigital.dto.MovementsResponseDto;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "timestamp", target = "createdAt")
    MovementDtoDto toDto(Transaction transaction);

    List<MovementDtoDto> toDtoList(List<Transaction> transactions);

    default MovementsResponseDto toMovementsResponse(Page<Transaction> page) {
        MovementsResponseDto response = new MovementsResponseDto();
        response.setItems(
                page.getContent().stream()
                        .map(this::toDto)
                        .collect(Collectors.toList())
        );
        response.setPage(page.getNumber());
        response.setSize(page.getSize());
        response.setTotal((int) page.getTotalElements());
        return response;
    }

    default OffsetDateTime map(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.atOffset(ZoneOffset.UTC) : null;
    }
}

