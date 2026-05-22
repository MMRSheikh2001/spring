package com.home.homeWork.controller;

import com.home.homeWork.entity.Country;
import com.home.homeWork.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries/")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @RequestMapping
    public ResponseEntity<Country> save(@RequestBody Country c){
      Country savedCountry=countryService.save(c);
      return new ResponseEntity<>(savedCountry, HttpStatus.CREATED);
    }

    @GetMapping
    public  ResponseEntity<List<Country>> getAll(){
        List<Country> list= countryService.findAll();
        return ResponseEntity.ok(list);
        //We can also use HttpStatus too like save method above
    }

    @GetMapping("{id}")
    public  ResponseEntity<Country> findById(@PathVariable("id") Integer id){
        Country c= countryService.getById(id)
                .orElseThrow(()->new RuntimeException("Country Not Found with this Id"));
        return  ResponseEntity.ok(c);

    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        countryService.delete(id);
        return  ResponseEntity.ok("Data Deleted");
    }

    @PutMapping("{id}")
    public ResponseEntity<Country> update(@PathVariable Integer id,
                                          @RequestBody Country c){
        c.setId(id);
       Country updatedCountry= countryService.save(c);
        return  ResponseEntity.ok(updatedCountry);
    }



}
