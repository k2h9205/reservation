package carrental;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Reservation_table")
public class Reservation {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long carNo;
    private String reserveDate;
    private String status;
    private Double carPrice;
    private String reserveCancelDate;

    @PostPersist
    public void onPostPersist(){
        System.out.println("##### onPostPersist status = " + this.getStatus());

        // 차량 예약-> paymentRequest
        if(this.getStatus().equals("RESERVED")) {
            Reserved reserved = new Reserved();
            BeanUtils.copyProperties(this, reserved);
            reserved.publishAfterCommit();

            //Following code causes dependency to external APIs
            // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

            carrental.external.Payment payment = new carrental.external.Payment();
            System.out.println("##### (paymentRequest)payment : " + payment);
            // mappings goes here
            payment.setCarNo(this.getCarNo());
            payment.setStatus(this.getStatus());
            payment.setPaymentDate(this.getReserveDate());

            ReservationApplication.applicationContext.getBean(carrental.external.PaymentService.class)
                    .paymentRequest(payment);
        }

    }

    @PostUpdate
    public void onPostUpdate(){
        System.out.println("##### PostUpdate status = " + this.getStatus());

        // 차량 예약취소-> paymentCancel
        if(this.getStatus().equals("RESERVATION_CANCELED")) {
            ReservationCanceled reservationCanceled = new ReservationCanceled();
            BeanUtils.copyProperties(this, reservationCanceled);
            reservationCanceled.publishAfterCommit();

            //Following code causes dependency to external APIs
            // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

            carrental.external.Payment payment = new carrental.external.Payment();
            // mappings goes here
            System.out.println("##### (paymentCancel)payment : " + payment);

            payment.setCarNo(this.getCarNo());
            payment.setStatus(this.getStatus());
            payment.setPaymentCancelDate(this.getReserveCancelDate());

            ReservationApplication.applicationContext.getBean(carrental.external.PaymentService.class)
                    .paymentCancel(payment);
        }

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getCarNo() {
        return carNo;
    }

    public void setCarNo(Long carNo) {
        this.carNo = carNo;
    }
    public String getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(String reserveDate) {
        this.reserveDate = reserveDate;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Double carPrice) {
        this.carPrice = carPrice;
    }
    public String getReserveCancelDate() {
        return reserveCancelDate;
    }

    public void setReserveCancelDate(String reserveCancelDate) {
        this.reserveCancelDate = reserveCancelDate;
    }




}
