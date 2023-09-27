package com.demo.event.api.dto;

import com.demo.shared.model.TimestampEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventRequestDto extends TimestampEntity {

    @NotBlank(message = "thiếu name")
    @Column(unique = true)
    String name;

    @NotNull(message = "ko được trống")
    String location;

    String desc;

    Instant startTime;

    String slug;

    List<Long> listSpeakerId;

    Long speakerId;

    List<Long> listEventNewsId;

}
