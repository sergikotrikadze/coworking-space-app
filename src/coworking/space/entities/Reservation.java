package coworking.space.entities;

public class Reservation {
    private long id;
    private long workspaceId;
    private String customerLogin;
    private String date;
    private String startTime;
    private String endTime;
    private static long autoIncrementId = 1;

    public Reservation(long workspaceId, String customerLogin, String date, String startTime, String endTime) {
        this.id = autoIncrementId++;
        this.workspaceId = workspaceId;
        this.customerLogin = customerLogin;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getId() {
        return id;
    }

    public long getWorkspaceId() {
        return workspaceId;
    }

    public String getCustomerLogin() {
        return customerLogin;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", workspaceId=" + workspaceId +
                ", customerLogin='" + customerLogin + '\'' +
                ", date='" + date + '\'' +
                ", time='" + startTime + " - " + endTime + '\'' +
                '}';
    }
}
