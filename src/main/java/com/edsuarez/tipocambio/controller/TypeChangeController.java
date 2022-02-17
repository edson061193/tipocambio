package com.edsuarez.tipocambio.controller;

import com.edsuarez.tipocambio.dto.BaseWebResponse;
import com.edsuarez.tipocambio.dto.TypeChangeRequest;
import com.edsuarez.tipocambio.dto.TypeResponse;
import com.edsuarez.tipocambio.entity.TypeChange;
import com.edsuarez.tipocambio.services.TypeChangeService;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.Single;

@RestController
@RequestMapping("/api/change")
public class TypeChangeController {

    @Autowired
    private TypeChangeService typeChangeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<BaseWebResponse>> add(@RequestBody TypeChange typeChangeRequest) {
        return typeChangeService.add(typeChangeRequest)
                .subscribeOn(Schedulers.io())
                .map(s -> ResponseEntity.created(URI.create("/api/change/" + s))
                        .body(BaseWebResponse.successNoData())
                );
    }

    @PutMapping(value = "/{idType}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<BaseWebResponse>> update(@PathVariable(value = "idType") Long idType,
                                                          @RequestBody TypeChangeRequest typeChangeRequest) {
        TypeChange typeChange = new TypeChange();
        BeanUtils.copyProperties(typeChangeRequest, typeChange);
        typeChange.setId(idType);

        return typeChangeService.update(typeChange)
                .subscribeOn(Schedulers.io())
                .toSingle(() -> ResponseEntity.ok(BaseWebResponse.successNoData()));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<BaseWebResponse<List<TypeResponse>>>> getAllBooks(
            @RequestParam(value = "limit", defaultValue = "5") int limit, @RequestParam(value = "page", defaultValue = "0") int page) {

        return typeChangeService.getAll(limit, page)
                .subscribeOn(Schedulers.io())
                .map(typeResponseList -> ResponseEntity.ok(BaseWebResponse.successWithData(typeResponseList)));
    }

    @GetMapping(value = "/{idTypeChange}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<BaseWebResponse<TypeResponse>>> getBookDetail(@PathVariable(value = "idTypeChange") Long idTypeChange) {
        return typeChangeService.getDetail(idTypeChange)
                .subscribeOn(Schedulers.io())
                .map(typeChange -> ResponseEntity.ok(BaseWebResponse.successWithData(toDtoTypeResponse(typeChange))));
    }

    @DeleteMapping(value = "/{idType}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<BaseWebResponse>> deleteBook(@PathVariable(value = "idType") Long idType) {
        return typeChangeService.delete(idType)
                .subscribeOn(Schedulers.io())
                .toSingle(() -> ResponseEntity.ok(BaseWebResponse.successNoData()));
    }

    private TypeResponse toDtoTypeResponse(TypeChange typeChange) {
        TypeResponse typeResponse = new TypeResponse();
        BeanUtils.copyProperties(typeChange, typeResponse);
        return typeResponse;
    }

}
