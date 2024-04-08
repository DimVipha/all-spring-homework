package co.istad.demomobilebanking.feature.media.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public record MediaResponse(
        String name,// unique
        String contentType,// jpg png, MP4,..
        String extension,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long size,
        String uri

) {
}
