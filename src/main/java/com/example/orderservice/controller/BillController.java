package com.example.orderservice.controller;

import com.example.orderservice.dto.BillDTO;
import com.example.orderservice.entity.Bill;
import com.example.orderservice.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/bills")
@CrossOrigin
public class BillController {
    @Autowired
    private BillService billService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = "application/json")
    public void createBill(@RequestBody BillDTO billDTO) {
        billService.addBill(billDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PutMapping(produces = "application/json")
    public Mono<BillDTO> updateBill(@RequestBody BillDTO billDTO){
        return billService.editBill(billDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Mono<Void> deleteById(@PathVariable String id){
        return billService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(params = "uid",produces = "application/json")
    public Flux<BillDTO> getAllByUid(@RequestParam("uid") String uid){
        return billService.findAllByUid(uid);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(produces = "application/json")
    public Flux<BillDTO> getAll(){
        return billService.findALl();
    }
}
