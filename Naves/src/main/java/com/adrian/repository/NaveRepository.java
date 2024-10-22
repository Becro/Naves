package com.adrian.repository;



import java.util.Optional;


import com.adrian.model.Nave;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface NaveRepository extends JpaRepository<Nave, Long>{

	Page<Nave> findAll(Pageable pageable);

	Optional<Nave> findById(Long id);

	Page<Nave> findByNombreContaining(String nombre,Pageable pageable);

}
