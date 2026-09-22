public class CarWash extends WashService {

    public CarWash(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        int days = getDays();
        int charge = 15000; 
        
        if (days <= 3) {
            charge += days * 35000;
        } else {
            charge += (3 * 35000) + ((days - 3) * 25000);
        }
        
        return charge;
    }

    @Override
    public String label() {
        return "Car";
    }
}