package uz.salvadore.camunda.client.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.salvadore.camunda.client.dto.ApplicationDto;
import uz.salvadore.camunda.client.dto.ErrorResult;
import uz.salvadore.camunda.client.service.ClaimService;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/loan/claim")
@Tag(name = "claim", description = "Microloan Claim Endpoint")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClaimController {

  ClaimService claimService;

  @Operation(summary = "Start process for microloan", description = "Start process for microloan", tags = {"claim"})
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "CREATED"),
    @ApiResponse(responseCode = "400", description = "BAD_REQUEST",
      content = @Content(schema = @Schema(implementation = ErrorResult.class))),
  })
  @PostMapping(value = "/start")
  public ResponseEntity<Void> start(@Valid @RequestBody final ApplicationDto request) {
    final Long appId = claimService.startProcess(request);
    return ResponseEntity.created(URI.create("/api/v1/loan/claim/" + appId)).build();
  }

}
