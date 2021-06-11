package com.example.orderservice.mapper;

import com.example.orderservice.dto.BillDTO;
import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.entity.Bill;
import com.example.orderservice.entity.Order;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BillMapper {
    public static ModelMapper modelMapper = new ModelMapper();

    public static Bill convertToEntity(BillDTO billDTO){
        return modelMapper.map(billDTO, Bill.class);
    }
    public static Flux<BillDTO> convertToDTOs(Flux<Bill> bills){
        return bills.map(bill -> BillDTO.builder()
                .id(bill.getId())
                .uid(bill.getUid())
                .address(bill.getAddress())
                .email(bill.getEmail())
                .orders(bill.getOrders())
                .fullName(bill.getFullName())
                .totalMoney(bill.getTotalMoney())
                .phoneNumber(bill.getPhoneNumber())
                .build());
    }

    public static Mono<BillDTO> convertToDTO(Mono<Bill> billMono){
        return billMono.map(bill -> BillDTO.builder()
                .id(bill.getId())
                .uid(bill.getUid())
                .address(bill.getAddress())
                .email(bill.getEmail())
                .orders(bill.getOrders())
                .fullName(bill.getFullName())
                .totalMoney(bill.getTotalMoney())
                .phoneNumber(bill.getPhoneNumber())
                .build());
    }
}
