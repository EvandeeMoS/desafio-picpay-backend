package com.evandeemos.desafiopicpay.service;

import com.evandeemos.desafiopicpay.domain.transaction.Transaction;
import com.evandeemos.desafiopicpay.domain.transaction.TransactionRepository;
import com.evandeemos.desafiopicpay.domain.transaction.dto.SuccefullyCreatedTransactionDto;
import com.evandeemos.desafiopicpay.domain.transaction.dto.TransactionCreationDto;
import com.evandeemos.desafiopicpay.domain.user.User;
import com.evandeemos.desafiopicpay.domain.user.UserRepository;
import com.evandeemos.desafiopicpay.domain.user.UserType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    String transactionAuthUri = "https://util.devi.tools/api/v2/authorize";

    @Transactional
    public SuccefullyCreatedTransactionDto createTransaction(TransactionCreationDto data) throws RuntimeException {
        User payer = userRepository.findById(data.payerId()).orElseThrow(() ->
                new RuntimeException("Pagador não encontrado"));

        if (payer.getType() == UserType.MERCHANT) {
            throw new RuntimeException("O pagador não pode ser do tipo lojista");
        }
        if (payer.getWallet().getMoney().compareTo(data.amount()) < 0) {
            throw new RuntimeException("O pagador não possui saldo o suficiente");
        }

        User payee = userRepository.findById(data.payeeId()).orElseThrow(() ->
                new RuntimeException("Destinatário não encontrado"));

        if (!isTransactionAuthorized()) {
            throw new RuntimeException("Transação não autorizada!");
        }

        payer.getWallet().setMoney(payer.getWallet().getMoney().subtract(data.amount()));
        payee.getWallet().setMoney(payee.getWallet().getMoney().add(data.amount()));
        Transaction transaction = new Transaction(data.amount(), payer, payee);

        transactionRepository.save(transaction);

        emailService.sendEmail("https://util.devi.tools/api/v1/notify)");

        return new SuccefullyCreatedTransactionDto(transaction);
    }

    private boolean isTransactionAuthorized() throws RuntimeException {
        TransactionAuthorizationDto authData = getTransactionAuthrorization();
        return (authData.status().equals("success") && authData.data().authorization());
    }

    private TransactionAuthorizationDto getTransactionAuthrorization()
            throws RuntimeException {
        ObjectMapper objectMapper = new ObjectMapper();
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            URI uri = URI.create(transactionAuthUri);
            HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), TransactionAuthorizationDto.class);
        }
        catch (UncheckedIOException | IOException | InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
