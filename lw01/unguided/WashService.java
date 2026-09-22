public abstract class WashService implements Billable {
    private String id;
    private int days;

    protected WashService(String id, int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Jumlah hari harus lebih dari 0");
        }
        this.id = id;
        this.days = days;
    }

    public String getId() {
        return id;
    }

    public int getDays() {
        return days;
    }

    public int calculateCharge(int units) {
        if (units <= 0) {
            throw new IllegalArgumentException("Jumlah unit harus lebih dari 0");
        }
        return units * calculateCharge();
    }

    public String label() {
        return "Service";
    }

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}