package com.skh.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.skh.model.Employee;
import com.skh.exceptions.MyCustomException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ConsumerServiceImpl {

    public Mono<Employee> fetchEmployee(Integer retryCount) throws InterruptedException {
        return WebClient.create("http://localhost:9001")
                .get()
                .uri("/fetchEmployee/"+retryCount)
                .retrieve()
                .onRawStatus(statusCode -> statusCode == 500, response -> response.bodyToMono(JsonNode.class)
                        .handle((error, sink) -> sink.error(new NullPointerException(error.toString()+" onRawStatus"))))
                .bodyToMono(Employee.class)
                .flatMap(this::validateResponse)
                .retryWhen(
                        Retry.backoff(3, Duration.ofSeconds(2)) // 3 retries, 2 sec delay
                                .filter(this::checkExceptionType)
                                .doBeforeRetry(retrySignal -> System.out.println(String.format("totalRetriesInARow: %d, time: %s",(retrySignal.totalRetriesInARow() + 1), LocalDateTime.now())))
                                .onRetryExhaustedThrow((retryBackoffSpec,retrySignal) -> {
                                    throw new NullPointerException(String.format("Retry Exhausted..!!  %s %s", retryBackoffSpec, retrySignal));
                                })
                );
    }

    boolean checkExceptionType(Throwable throwable){
        System.out.println("Inside filter, Exception Matched? - " + (throwable instanceof NullPointerException ||
                throwable instanceof FileNotFoundException));
        return throwable instanceof NullPointerException ||
                throwable instanceof FileNotFoundException;
    }

    private Mono<Employee> validateResponse(Employee employeeResponse){

      //  employeeResponse = new Random().nextInt() % 2 == 0 ? employeeResponse = null : employeeResponse;

        if(null == employeeResponse){
            return Mono.error(new MyCustomException("Response is NULL"));
        }
        return Mono.just(employeeResponse);
    }



    /*public List<Employee> fetchAllEmployees() {
        Mono<Employee> response = WebClient.create("http://localhost:9001/fetchAllEmployees")
                .get()
                .uri("/data")
                .retrieve()
                .bodyToMono(Employee.class)
                .retry(3);

        return response.block();
    }
*/

}
