public class ColourPrint extends PrintJob {
    
    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int totalPages = getPages();
        int cost = 2000; // Biaya setup
        
        if (totalPages <= 10) {
            cost += totalPages * 1500;
        } else {
            cost += (10 * 1500) + ((totalPages - 10) * 1000);
        }
        
        return cost;
    }

    @Override
    public String label() {
        return "Colour";
    }
}