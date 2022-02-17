package com.edsuarez.tipocambio.services.impl;

import com.edsuarez.tipocambio.dto.TypeResponse;
import com.edsuarez.tipocambio.entity.TypeChange;
import com.edsuarez.tipocambio.repository.TypeChangeRepository;
import com.edsuarez.tipocambio.services.TypeChangeService;
import io.reactivex.Completable;
import io.reactivex.Single;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.nio.channels.FileChannel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TypeChangeImpl implements TypeChangeService {

@Autowired
private TypeChangeRepository typeChangeRepository;

    @Override
    public Single<String> add(TypeChange type) {
        return Single.create(singleSubscriber -> {
            Optional<TypeChange> typeChange = typeChangeRepository.findById(type.getId());
            if (typeChange.isPresent())
                singleSubscriber.onError(new EntityExistsException());
            else {
                Long idTypeChange = typeChangeRepository.save(type).getId();
                singleSubscriber.onSuccess(String.valueOf(idTypeChange));
            }
        });
    }

    @Override
    public Completable update(TypeChange typeChange) {
        return Completable.create(completableSubscriber -> {
            Optional<TypeChange> typeChangeResponse = typeChangeRepository.findById(typeChange.getId());
            if (!typeChangeResponse.isPresent())
                completableSubscriber.onError(new EntityNotFoundException());
            else {
                typeChange.setLastUpdate(LocalDateTime.now());
                typeChangeRepository.save(typeChange);
                completableSubscriber.onComplete();
            }
        });
    }

    @Override
    public Single<List<TypeResponse>> getAll(int limit, int page) {
        return findAllFromRepository(limit, page)
                .map(this::converterResponseList);
    }

    private List<TypeResponse> converterResponseList(List<TypeChange> typeChanges) {
        return typeChanges
                .stream()
                .map(this::converterResponseDto)
                .collect(Collectors.toList());
    }

    private TypeResponse converterResponseDto(TypeChange typeChange) {
        TypeResponse typeResponse = new TypeResponse();
        BeanUtils.copyProperties(typeChange, typeResponse);
        return typeResponse;
    }

    private Single<List<TypeChange>>  findAllFromRepository(int limit, int page) {
        return Single.create(singleSubscriber -> {
            List<TypeChange> typeChanges = typeChangeRepository.findAll(PageRequest.of(page, limit)).getContent();
            singleSubscriber.onSuccess(typeChanges);
        });
    }

    @Override
    public Single<TypeChange> getDetail(Long id) {
        return Single.create(singleSubscriber -> {
            Optional<TypeChange> optionalBook = typeChangeRepository.findById(id);
            if (!optionalBook.isPresent())
                singleSubscriber.onError(new EntityNotFoundException());
            else {
                singleSubscriber.onSuccess(optionalBook.get());
            }
        });
    }

    @Override
    public Completable delete(Long id) {
        return Completable.create(completableSubscriber -> {
            Optional<TypeChange> optionalBook = typeChangeRepository.findById(id);
            if (!optionalBook.isPresent())
                completableSubscriber.onError(new EntityNotFoundException());
            else {
                typeChangeRepository.delete(optionalBook.get());
                completableSubscriber.onComplete();
            }
        });
    }
}
