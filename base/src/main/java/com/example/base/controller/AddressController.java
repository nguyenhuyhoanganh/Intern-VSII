package com.example.base.controller;

import com.example.base.entity.Address;
import com.example.base.model.AddressDTO;
import com.example.base.model.ResponseDTO;
import com.example.base.service.impl.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author Phuong Oanh
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @GetMapping("")
    public ResponseDTO<List<AddressDTO>> getAll1() {
        return ResponseDTO.<List<AddressDTO>>builder()
                .data(addressService.getAll())
                .build();
    }

    @GetMapping("/{idUser}")
    public ResponseDTO<List<AddressDTO>> view(@PathVariable("idUser") Long idUser) {
        return ResponseDTO.<List<AddressDTO>>builder().
                data(addressService.findAddressByUserId(idUser)).build();
    }

    @PutMapping("/update/{id}")
    public ResponseDTO<AddressDTO> updateUser(@Valid @RequestBody AddressDTO addressDTO, @PathVariable Long id) {
        return ResponseDTO.<AddressDTO>builder()
                .data(addressService.update(id, addressDTO))
                .code(HttpStatus.OK.value())
                .build();
    }

    @PostMapping("/add")
    public ResponseDTO<AddressDTO> create(@Valid @RequestBody AddressDTO addressDTO) {
        return ResponseDTO.<AddressDTO>builder()
                .data(addressService.create(addressDTO))
                .code(HttpStatus.CREATED.value())
                .build();

    }

    @DeleteMapping("/{id}")
    public ResponseDTO<Address> deleteById(@PathVariable Long id) {
        addressService.deleteById(id);
        return ResponseDTO.<Address>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .build();
    }
}
