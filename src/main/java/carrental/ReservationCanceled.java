package carrental;

public class ReservationCanceled extends AbstractEvent {

    private Long id;
    private Long carNo;
    private String status;
    private String reserveCancelDate;

    public ReservationCanceled(){
        super();
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
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getReserveCancelDate() {
        return reserveCancelDate;
    }

    public void setReserveCancelDate(String reserveCancelDate) {
        this.reserveCancelDate = reserveCancelDate;
    }
}
