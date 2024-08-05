package com.evandeemos.desafiopicpay.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

@Service
public class EmailService {

    public void sendEmail(String emailServiceUrl) {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            URI uri = URI.create(emailServiceUrl);
            BodyPublisher body = BodyPublishers.ofString("");
            HttpRequest request = HttpRequest.newBuilder(uri).POST(body).build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 204) {
                throw new RuntimeException("Sistema de email fora do ar!");
            }
        }
        catch (UncheckedIOException | IOException | InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
