package com.microservices.rpg.paymentservice;

import com.stripe.model.Charge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final StripeClient stripeClient;

    PaymentController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @PostMapping("/payment/charge")
    public Charge chargeCard(@RequestHeader String token,
                             @RequestHeader Double amount) throws Exception {
        return this.stripeClient.chargeCreditCard(token, amount);
    }
}
