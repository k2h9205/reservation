package carrental.external;

public class Payment {

    private Long id;
    private Long carNo;
    private String paymentDate;
    private String status;
    private String paymentCancelDate;

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
    public String getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getPaymentCancelDate() {
        return paymentCancelDate;
    }
    public void setPaymentCancelDate(String paymentCancelDate) {
        this.paymentCancelDate = paymentCancelDate;
    }

}
