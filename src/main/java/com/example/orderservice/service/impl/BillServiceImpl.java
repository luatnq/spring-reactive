package com.example.orderservice.service.impl;

import com.example.orderservice.dto.BillDTO;
import com.example.orderservice.entity.Bill;
import com.example.orderservice.exception.BillNotFoundException;
import com.example.orderservice.mapper.BillMapper;
import com.example.orderservice.repository.BillRepository;
import com.example.orderservice.service.BillService;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
public class BillServiceImpl implements BillService{
    @Autowired
    private BillRepository billRepository;

    public void addBill(BillDTO billDTO){
        Bill createBill = BillMapper.convertToEntity(billDTO);
        billRepository.save(createBill).subscribe();
    }

    @Override
    public Mono<BillDTO> editBill(BillDTO billDTO) {
        Mono<Bill> updateBill = billRepository.findById(billDTO.getId())
                .map(bill -> {
                    bill.setOrders(billDTO.getOrders());
                    bill.setAddress(billDTO.getAddress());
                    bill.setEmail(billDTO.getEmail());
                    bill.setTotalMoney(billDTO.getTotalMoney());
                    bill.setPhoneNumber(billDTO.getPhoneNumber());
                    bill.setUpdatedAt(Instant.now());
                    return bill;
                }).flatMap(bill -> billRepository.save(bill))
                .switchIfEmpty(Mono.error(new BillNotFoundException("")));
        return BillMapper.convertToDTO(updateBill);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return billRepository.deleteById(id);
    }

    @Override
    public Flux<BillDTO> findAllByUid(String uid) {
        Flux<Bill> bills = billRepository.findAllByUidOrderByCreatedAtDesc(uid)
                .switchIfEmpty(Flux.error(new BillNotFoundException("")));
        return BillMapper.convertToDTOs(bills);
    }

    @Override
    public Flux<BillDTO> findALl(){
        Flux<Bill> bills = billRepository.findAllByOrderByCreatedAtDesc();
        return BillMapper.convertToDTOs(bills);
    }

}
