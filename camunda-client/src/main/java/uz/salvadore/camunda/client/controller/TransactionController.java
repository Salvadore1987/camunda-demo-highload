package uz.salvadore.camunda.client.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.salvadore.camunda.client.dto.Transaction;
import uz.salvadore.camunda.client.service.TransactionService;

import java.net.URI;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/transactions")
@Tag(name = "transaction", description = "Microloan Transaction Endpoint")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionController {

  TransactionService service;

  @PostMapping
  public ResponseEntity<Object> save(@RequestBody Transaction transaction) {
    return Optional.ofNullable(transaction)
      .map(service::save)
      .map(id -> ResponseEntity.created(URI.create("http://localhost:8081/microloan-service/api/v1/transactions")).build())
      .orElseThrow();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Transaction> getTransaction(@PathVariable("id") Long id) {
    return Optional.of(id)
      .map(service::getTransaction)
      .map(ResponseEntity::ok)
      .orElseThrow();
  }

}
