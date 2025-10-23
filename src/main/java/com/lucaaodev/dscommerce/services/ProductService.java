package com.lucaaodev.dscommerce.services;

import com.lucaaodev.dscommerce.dto.ProductDTO;
import com.lucaaodev.dscommerce.entities.Product;
import com.lucaaodev.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = repository.findAll(pageable);
        return result.map(x -> new ProductDTO(x));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
         // Optional<Product> result = repository.findById(id);  //BUSCOU NO BANCO O PRODUTO COM ESSE ID E GRAVA EM RESULT
        // Product product = result.get(); //PEGO_TODO O PRODUTO
        // ProductDTO dto = new ProductDTO(product); //CONVERTO ELE PARA UM DTO
        // return dto; //RETORNO DTO

        Product product = repository.findById(id).get();
        return new ProductDTO(product);
    }



}
