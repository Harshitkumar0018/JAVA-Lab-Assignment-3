public class Loader implements Runnable {
    private String action;

    public Loader(String action) {
        this.action = action;
    }

    @Override
    public void run() {
        try {
            System.out.print(action);
            for (int i = 0; i < 5; i++) {
                Thread.sleep(300);
                System.out.print(".");
            }
            System.out.println();
        } catch (InterruptedException e) {
            System.out.println("\nLoader interrupted.");
        }
    }
}
