package ru.yandex.practicum.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.dto.DeliveryDto;
import ru.yandex.practicum.dto.OrderDto;
import ru.yandex.practicum.service.DeliveryService;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/delivery")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PutMapping
    public DeliveryDto planDelivery(@RequestBody @Valid DeliveryDto deliveryDto) {
        try {
            log.info("Создать новую доставку в БД {}", deliveryDto);
            return deliveryService.planDelivery(deliveryDto);
        } catch (Exception e) {
            log.error("Ошибка создания новой доставки.");
            throw e;
        }
    }

    @PostMapping("/successful")
    public void deliverySuccessful(@RequestBody UUID deliveryId) {
        try {
            log.info("Успешная доставка товара: {}", deliveryId);
            deliveryService.deliverySuccessful(deliveryId);
        } catch (Exception e) {
            log.error("Error: deliverySuccessful");
            throw e;
        }
    }

    @PostMapping("/picked")
    public void deliveryPicked(@RequestBody UUID deliveryId) {
        try {
            log.info("Получение товара в доставку: {}", deliveryId);
            deliveryService.deliveryPicked(deliveryId);
        } catch (Exception e) {
            log.error("Error: deliveryPicked");
            throw e;
        }
    }

    @PostMapping("/failed")
    public void deliveryFailed(@RequestBody UUID deliveryId) {
        try {
            log.info("Товар не удалось вручить: {}", deliveryId);
            deliveryService.deliveryFailed(deliveryId);
        } catch (Exception e) {
            log.error("Error: deliveryFailed");
        }
    }

    @PostMapping("/cost")
    public void deliveryCost(@RequestBody @Valid OrderDto orderDto) {
        try {
            log.info("Расчет полной стоимости доставки заказа: {}", orderDto);
            deliveryService.deliveryCost(orderDto);
        } catch (Exception e) {
            log.error("Error: deliveryCost");
            throw e;
        }
    }
}
