package pl.training.shop.time;

import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public interface TimeProvider {

    Instant getTimestamp();

}
