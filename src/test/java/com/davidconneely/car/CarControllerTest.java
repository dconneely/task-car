package com.davidconneely.car;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CarControllerTest {
    private static <T> List<T> iterableToList(Iterable<T> iter) {
        return StreamSupport.stream(iter.spliterator(), false).toList();
    }

    @Test
    public void testGetAll() {
        CarRepository mockRepo = mock(CarRepository.class);
        when(mockRepo.findAll()).thenReturn(Arrays.asList(
                new Car(1, "model1"),
                new Car(2, "model2"),
                new Car(3, "model3")
        ));
        CarController controller = new CarController(mockRepo);
        ResponseEntity<Iterable<Car>> response = controller.getCars(null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, iterableToList(response.getBody()).size());
    }

    @Test
    public void testGetModel() {
        CarRepository mockRepo = mock(CarRepository.class);
        when(mockRepo.findAll()).thenReturn(Arrays.asList(
                new Car(1, "model1"),
                new Car(2, "model2"),
                new Car(3, "model3")
        ));
        CarController controller = new CarController(mockRepo);
        ResponseEntity<Iterable<Car>> response = controller.getCars("model3");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, iterableToList(response.getBody()).size());
    }

    @Test
    public void testGetEmpty() {
        CarRepository mockRepo = mock(CarRepository.class);
        when(mockRepo.findAll()).thenReturn(Arrays.asList(
                new Car(1, "model1"),
                new Car(2, "model2"),
                new Car(3, "model3")
        ));
        CarController controller = new CarController(mockRepo);
        ResponseEntity<Iterable<Car>> response = controller.getCars("model99");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
