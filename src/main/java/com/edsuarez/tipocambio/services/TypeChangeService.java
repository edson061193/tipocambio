package com.edsuarez.tipocambio.services;

import com.edsuarez.tipocambio.dto.TypeResponse;
import com.edsuarez.tipocambio.entity.TypeChange;
import io.reactivex.Completable;
import io.reactivex.Single;

import java.util.List;

public interface TypeChangeService {
    Single<String> add(TypeChange type);
    Completable update(TypeChange type);
    Single<List<TypeResponse>> getAll(int limit, int page);
    Single<TypeChange> getDetail(Long id);
    Completable delete(Long id);
}
