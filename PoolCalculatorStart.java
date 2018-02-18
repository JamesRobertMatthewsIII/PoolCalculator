public class PoolCalculatorStart {

    public static void main(String[] args) {

        PoolCalculator model = new PoolCalculator();
        PoolCalculatorView view = new PoolCalculatorView();
        PoolCalculatorController controller = new PoolCalculatorController(model, view);
    }
}
