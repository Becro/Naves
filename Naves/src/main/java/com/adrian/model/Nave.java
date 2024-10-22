package com.adrian.model;

import java.io.Serializable;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;


@Entity 
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Nave")
public class Nave implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_nave")
    private Long idNave;
    @NonNull
    @Column(name="nombre")
    private String nombre;

}
