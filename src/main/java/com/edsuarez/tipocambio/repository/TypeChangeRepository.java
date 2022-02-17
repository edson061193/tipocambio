package com.edsuarez.tipocambio.repository;

import com.edsuarez.tipocambio.entity.TypeChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface TypeChangeRepository extends JpaRepository<TypeChange, Serializable> {
}
