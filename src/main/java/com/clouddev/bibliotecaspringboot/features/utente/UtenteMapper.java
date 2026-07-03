package com.clouddev.bibliotecaspringboot.features.utente;

import org.springframework.stereotype.Component;

@Component
public class UtenteMapper {

    public UtenteEntity toEntity(UtenteDto dto) {
        if (dto == null) return null;

        return UtenteEntity.builder()
                .nome(dto.getNome())
                .cognome(dto.getCognome())
                .email(dto.getEmail())
                .build();
    }

    public UtenteDto toDto(UtenteEntity entity) {
        if (entity == null) return null;

        UtenteDto dto = new UtenteDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCognome(entity.getCognome());
        dto.setEmail(entity.getEmail());
        return dto;
    }
}