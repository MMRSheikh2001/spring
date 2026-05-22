package com.home.homeWork.repository;

import com.home.homeWork.entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DivisionRepository extends JpaRepository<Division, Integer> {
    //Find all divisions by Country Id
    List<Division> findByCountryId(Integer countryId);


    //ekhane JPQL use kore sobsomoy findBy dite hobe.
    //tarpor Country theke Id khuje nibe

    //Find all divisions by Country Name
    List<Division> findByCountryName(String countryName);

}
