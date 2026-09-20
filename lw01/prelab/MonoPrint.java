public class MonoPrint extends PrintJob {
    
    public MonoPrint(String id, int pages) {
        super(id, pages); // Memanggil konstruktor parent
    }

    @Override
    public int calculateCharge() {
        return getPages() * 500;
    }

    @Override
    public String label() {
        return "Mono";
    }
}