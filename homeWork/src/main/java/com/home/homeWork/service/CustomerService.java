package com.home.homeWork.service;

import com.home.homeWork.entity.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public interface CustomerService {
    Customer save(Customer c, MultipartFile filr);

    List<Customer> findAll();
    Optional<Customer> getById(Long id);
    void delete(Long id);

}
