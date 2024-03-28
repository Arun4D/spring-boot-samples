package in.co.ad.springboot3.features.springboot3features.controller;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/batch")
@RequiredArgsConstructor
public class StopStartController {


    private static  CompletableFuture<String> completableFuture;
    
    @PostMapping("/start")
    public ResponseEntity<String> start(String jobName) throws InterruptedException, ExecutionException {

        completableFuture = CompletableFuture.supplyAsync(() -> {
            for(int index =1; index <= 4 ; index ++) {
                System.err.println("index: " + index);
                sleep(10000);
            }
            
            return "Hello";
        });

        long timeOutValue = 60000L;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;

        completableFuture.orTimeout(timeOutValue, timeUnit);

        return new ResponseEntity<>("Started", HttpStatus.CREATED);
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stop(String jobName) {


        completableFuture.cancel(true);
        
        try {
          completableFuture.join(); // Actually, CompletionException is thrown here.
        } catch (CancellationException e) {
          System.out.println("Exception was thrown from f.join()");
          e.printStackTrace(System.out);
        }

        return new ResponseEntity<>("Stopped", HttpStatus.CREATED);
    }
 
    static void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
