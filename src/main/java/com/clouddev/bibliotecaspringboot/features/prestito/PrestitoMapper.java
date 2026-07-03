package com.clouddev.bibliotecaspringboot.features.prestito;

import org.springframework.stereotype.Component;

@Component
public class PrestitoMapper {

    public PrestitoDto toDto(PrestitoEntity entity) {
        if (entity == null) return null;

        PrestitoDto dto = new PrestitoDto();
        dto.setId(entity.getId());
        dto.setUtenteId(entity.getUtente().getId());
        dto.setLibroId(entity.getLibro().getId());
        dto.setDataInizio(entity.getDataInizio());
        dto.setDataScadenza(entity.getDataScadenza());
        dto.setDataRestituzioneEffettiva(entity.getDataRestituzioneEffettiva());
        dto.setStato(entity.getStato());
        return dto;
    }
}