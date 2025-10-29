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


    @Transactional
    public ProductDTO insert(ProductDTO dto){

         Product entity = new Product();

         copyDtoToEntity(dto, entity);

        entity = repository.save(entity);

        return new ProductDTO(entity);

    }


    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){

        Product entity = repository.getReferenceById(id);

        copyDtoToEntity(dto, entity);

        entity = repository.save(entity);

        return new ProductDTO(entity);

    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }


    private void copyDtoToEntity(ProductDTO dto, Product entity) {

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());

    }


}
