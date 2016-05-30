package com.dk.view.controller;

import com.dk.data.model.PaymentInstrument;
import com.dk.enums.CreditCardScheme;
import com.dk.enums.PaymentInstrumentStatus;
import com.dk.service.PaymentInstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PaymentInstrumentController {

    @Autowired
    private PaymentInstrumentService paymentInstrumentService;

    @RequestMapping("/")
    public ModelAndView home() {
        final Map<String, Object> model = new HashMap<String, Object>();
        final List<PaymentInstrument> paymentInstruments = paymentInstrumentService.findPaymentInstruments();
        model.put("paymentInstruments", paymentInstruments);
        return new ModelAndView("home", model);
    }

    @RequestMapping(value = "/lockPaymentInstrument", method = RequestMethod.POST)
    public String lockPaymentInstrument(@RequestParam Long paymentInstrumentId, RedirectAttributes redirectAttributes) {
        String message = paymentInstrumentService.updateLockStatusPaymentInstrument(paymentInstrumentId, PaymentInstrumentStatus.Locked);
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/";
    }

    @RequestMapping(value = "/unlockPaymentInstrument", method = RequestMethod.POST)
    public String unlockPaymentInstrument(@RequestParam Long paymentInstrumentId, RedirectAttributes redirectAttributes) {
        String message = paymentInstrumentService.updateLockStatusPaymentInstrument(paymentInstrumentId, PaymentInstrumentStatus.Active);
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/";
    }

    @RequestMapping(value = "/createBankAccount", method = RequestMethod.POST)
    public String createBankAccount(
            @RequestParam String accountHolder,
            @RequestParam String bankName,
            @RequestParam String iban,
            @RequestParam String bic,
            RedirectAttributes redirectAttributes
    ) {
        long bankAccountId = paymentInstrumentService.createBankAccount(accountHolder, bankName, iban, bic);
        redirectAttributes.addFlashAttribute("message", String.format("Bank account with ID %s created.", bankAccountId));
        return "redirect:/";
    }

    @RequestMapping(value = "/createCreditCard", method = RequestMethod.POST)
    public String createCreditCard(
            @RequestParam String accountHolder,
            @RequestParam CreditCardScheme scheme,
            @RequestParam String pan,
            @RequestParam String expiryMonth,
            @RequestParam String expiryYear,
            RedirectAttributes redirectAttributes
    ) {
        //TODO: In the case of release better implement verification on UI, JSTL if-instanceof-then for example.
        int expiryMonthValuated = 0;
        int expiryYearValuated = 0;

        //NOTE: instanceof id the heaviest operation in Java, better to don't use it

        //TODO: In a case of evaluation in Java the following code have to be in Service
        try {
            expiryMonthValuated = Integer.parseInt(expiryMonth);
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("message", "Months is not a number. Please input the correct one.");
            return "redirect:/";
        }

        try {
            expiryYearValuated = Integer.parseInt(expiryYear);
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("message", "Year is not a number. Please input the correct one.");
            return "redirect:/";
        }

        long creditCardId = paymentInstrumentService.createCreditCard(accountHolder, scheme, pan, expiryMonthValuated, expiryYearValuated);
        redirectAttributes.addFlashAttribute("message", String.format("Credit card with ID %s created.", creditCardId));
        return "redirect:/";
    }

}
