
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PoolCalculatorController {
    private PoolCalculatorView view;
    private PoolCalculator model;

    public PoolCalculatorController(PoolCalculator poolModel, PoolCalculatorView poolView) {
        this.model = poolModel;
        this.view = poolView;

        this.view.addGenerateButtonListener(new GenerateButtonListener());
    }


    public class GenerateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                model.setVolume(view.getVolume());
                model.setFreeChlorine(view.getFreeChlorine());
                model.setTotalChlorine(view.getTotalChlorine());
                model.setpH(view.getPh());
                model.setAlkalinity(view.getAlkalinity());
                model.setCalciumHardness(view.getCalciumHardness());
                model.setCyanuricAcid(view.getCyanuricAcid());
                view.displayReport(model.generateReport());

            } catch (Exception ex) {
                view.displayErrorMessage("Error: please fill in all fields with numbers");
                ex.printStackTrace();
            }
        }
    }
}
