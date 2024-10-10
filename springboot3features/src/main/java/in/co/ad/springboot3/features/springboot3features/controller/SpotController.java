package in.co.ad.springboot3.features.springboot3features.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.ad.springboot3.features.springboot3features.domain.Spot;
import in.co.ad.springboot3.features.springboot3features.repository.SpotRepository;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/spot")
@RequiredArgsConstructor
public class SpotController {

     private final SpotRepository spotRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Spot> getSpotById(@PathParam("id") int id) {

        Optional<Spot> spotDoOpt = spotRepository.findById(id);
        Spot spot = null;
        if (spotDoOpt.isPresent())
        spot = spotDoOpt.get();
        return new ResponseEntity<>(spot, HttpStatus.OK);
    }

}
