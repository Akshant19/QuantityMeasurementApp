package org.apps.quantitymeasurement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/quantities")
public class QuantityMeasurementRestControllerUC15 {
    private final IQuantityMeasurementServiceUC15 service;

    public QuantityMeasurementRestControllerUC15(IQuantityMeasurementServiceUC15 service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<QuantityDTOUC15> add(@RequestBody AddRequestUC15 request) {
        return ResponseEntity.ok(service.add(request.getFirst(), request.getSecond()));
    }

    @PostMapping("/convert")
    public ResponseEntity<QuantityDTOUC15> convert(
            @RequestBody QuantityDTOUC15 dto,
            @RequestParam String targetUnit) {
        return ResponseEntity.ok(service.convert(dto, targetUnit));
    }
}
