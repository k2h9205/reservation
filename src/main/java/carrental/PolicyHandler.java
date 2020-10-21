package carrental;

import carrental.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyHandler{

    @Autowired
    ReservationRepository reservationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCarRented_UpdateStatus(@Payload CarRented carRented){

        if(carRented.isMe()){
            System.out.println("##### wheneverCarRented_UpdateStatus : " + carRented.toJson());

            List<Reservation> optional = reservationRepository.findByCarNo(carRented.getCarNo());
            for(Reservation reservation : optional) {
                reservation.setStatus(carRented.getStatus());
                reservationRepository.save(reservation);
            }
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCarRentCanceled_UpdateStatus(@Payload CarRentCanceled carRentCanceled){

        if(carRentCanceled.isMe()){
            System.out.println("##### wheneverCarRentCanceled_UpdateStatus : " + carRentCanceled.toJson());

            List<Reservation> optional = reservationRepository.findByCarNo(carRentCanceled.getCarNo());
            for(Reservation reservation : optional) {
                reservation.setStatus(carRentCanceled.getStatus());
                reservationRepository.save(reservation);
            }
        }
    }

}
