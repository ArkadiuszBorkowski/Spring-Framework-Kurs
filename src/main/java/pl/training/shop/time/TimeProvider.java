package pl.training.shop.time;

import java.time.Instant;

public interface TimeProvider {

    Instant getTimestamp();

}
