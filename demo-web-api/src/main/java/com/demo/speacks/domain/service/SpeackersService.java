package com.demo.speacks.domain.service;

import com.demo.event.domain.model.Event;
import com.demo.speacks.api.dto.SpeackerEventDto;
import com.demo.speacks.domain.model.Speakers;
import com.demo.speacks.infrastructure.repository.SpeackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpeackersService implements ISpeackersService {

    private final SpeackRepository speackRepository;

    @Override
    public Page<Speakers> getAll(Pageable pageable) {
        return speackRepository.findAll(pageable);
    }

    @Override
    public Speakers insert(Speakers speakers) {
        return speackRepository.save(speakers);
    }

    @Override
    public Speakers update(Long id, Speakers speakers) {
        speakers.setId(id);
        return speackRepository.save(speakers);
    }

    @Override
    public Boolean remove(Long id) {
        try {
            speackRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean removeAll() {
        try {
            speackRepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<Speakers> findById(Long id) {
        return speackRepository.findById(id);
    }

    @Override
    public Map<String, Object> getSpeackerEvents(Pageable pageable) {
        Page<Object[]> resultPage = speackRepository.findSpeakersEvent(pageable);
        List<SpeackerEventDto> speackerEventDtos = new ArrayList<>();

        for (Object[] obj : resultPage.getContent()) {
            SpeackerEventDto speackerEventDto = new SpeackerEventDto();
            speackerEventDto.setId(Long.parseLong(String.valueOf(obj[0])));
            speackerEventDto.setTitle((String) obj[1]);
            speackerEventDto.setImgUrl((String) obj[2]);
            speackerEventDto.setDescription((String) obj[3]);
            speackerEventDto.setPhoneNumber((String) obj[4]);
            speackerEventDto.setContact((String) obj[5]);
            speackerEventDto.setCompany((String) obj[7]);
            speackerEventDto.setMajor((String) obj[8]);
            speackerEventDto.setLevel((String) obj[9]);

            Event event = new Event();
            event.setId(Long.parseLong(String.valueOf(obj[11])));
            event.setName((String) obj[12]);
            speackerEventDto.setEvent(event);

            speackerEventDtos.add(speackerEventDto);
        }

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("code", 200);
        responseMap.put("data", speackerEventDtos);

        return responseMap;
    }

}
