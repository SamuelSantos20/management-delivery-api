package com.packge.manager.tosam.br.api_management_deliverieso.service;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Address;
import com.packge.manager.tosam.br.api_management_deliverieso.domain.Customer;
import com.packge.manager.tosam.br.api_management_deliverieso.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.packge.manager.tosam.br.api_management_deliverieso.repository.specification.AddressSpecification.*;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;


    public void save(Address address) {

        addressRepository.save(address);
    }


    public Page<Address> addressSearch(String id,
                                       String street,
                                       Integer number,
                                       String city,
                                       String neighborhood,
                                       String complement,
                                       String customer_id,
                                       Integer numberPage,
                                       Integer numberElements) {

        Specification<Address> spec = Specification.where((root, query, cb) -> cb.conjunction());


        if (id != null) {
            var uid = UUID.fromString(id);
            spec = spec.and(specificationId(uid));
        }

        if (street != null) {

            spec = spec.and(specificationStreet(street));
        }

        if (number != null) {

            spec = spec.and(specificationNumber(number));
        }

        if (city != null) {

            spec = spec.and(specificationCity(city));
        }

        if (neighborhood != null) {

            spec = spec.and(specificationNeighborhood(neighborhood));
        }

        if (complement != null) {

            spec = spec.and(specificationComplement(complement));
        }

        Pageable pageable = PageRequest.of(numberPage, numberElements);

        return addressRepository.findAll(spec, pageable);


    }


    public void Delete(Address address) {
        addressRepository.delete(address);
    }

    public Optional<Address> GetDetails(UUID id) {
        return Optional.ofNullable(addressRepository.findById(id).orElseGet(() -> null));
    }


    public void Update(Address address) {

        if (address == null) {
            throw new IllegalArgumentException("O valor enviado é null!");
        }

        addressRepository.save(address);

    }
}
