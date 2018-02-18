

public class PoolCalculator {
    private float volume;
    private float freeChlorine;
    private float totalChlorine;
    private float pH;
    private float alkalinity;
    private float calciumHardness;
    private float cyanuricAcid;
    private boolean isConcrete;

    // Might implement a way to change these in the app
    private float optimalFreeChlorine = 4.0f;
    private float optimalTotalChlorine = 4.0f;
    private float optimalpH = 7.5f;
    private float optimalAlkalinity = 100.0f;
    private float optimalCalciumHardness = 200.0f;
    private float optimalCyanuricAcid = 30.0f;

    private boolean alkalinityCorrect = false;

    public float getVolume() {
        return volume;
    }

    public float getFreeChlorine() {
        return freeChlorine;
    }

    public float getTotalChlorine() {
        return totalChlorine;
    }

    public float getpH() {
        return pH;
    }

    public float getAlkalinity() {
        return alkalinity;
    }

    public float getCalciumHardness() {
        return calciumHardness;
    }

    public float getCyanuricAcid() {
        return cyanuricAcid;
    }

    public boolean getIsConcrete() {
        return isConcrete;
    }

    public void setVolume(float userVolume) {
        this.volume = userVolume;
    }

    public void setFreeChlorine(float userFreeChlorine) {
        this.freeChlorine = userFreeChlorine;
    }

    public void setTotalChlorine(float userTotalChlorine) {
        this.totalChlorine = userTotalChlorine;
    }

    public void setpH(float userPh) {
        this.pH = userPh;
    }

    public void setAlkalinity(float userAlkalinity) {
        this.alkalinity = userAlkalinity;
    }

    public void setCalciumHardness(float userCalciumHardness) {
        this.calciumHardness = userCalciumHardness;
    }

    public void setCyanuricAcid(float userCyanuricAcid) {
        this.cyanuricAcid = userCyanuricAcid;
    }

    public void setConcrete(boolean concrete) {
        this.isConcrete = concrete;
    }

    private String chlorineMessage() {
        String message = "Chlorine: ";
        float combinedChlorine = totalChlorine - freeChlorine;
        if (totalChlorine < 2) {
            message += "Total chlorine low, increase chlorine feed rate. ";
        } else if (totalChlorine > 8){
            message += "Chlorine level high, decrease chlorine feeding. ";
        } else {
            message += "Chlorine level fine.";
        }

        if ((combinedChlorine) > 0.1) {
            double poundsCalHypo = ((volume / 10000) * 20 * combinedChlorine) / 16;
            message += String.format("Shock with at least %.2f lbs of calcium hypochlorite", poundsCalHypo);
        }
        return message;
    }

    private String phMessage() {
        String message = "pH: ";
        if (!alkalinityCorrect) {
            message += "Adjust alkalinity before balancing pH";
        } else if ((pH < 7.4) && (alkalinityCorrect)) {
            double poundsSodaAsh = ((volume / 10000) * 10.4) / 16;
            message += String.format("Add %.1f lbs of soda ash to raise pH by 0.2, then retest", poundsSodaAsh);
        } else if ((pH > 7.6) && (alkalinityCorrect)) {
            double poundsDryAcid = ((volume / 10000) * 20.8) / 16;
            double poundsMuriatic = ((volume / 10000) * 13.3);
            message += String.format("Add %.1f lbs of sodium bisulfate (dry acid), or %.1f oz muriatic acid", poundsDryAcid, poundsMuriatic);
        } else {
            message += "pH is fine";
        }
        return message;
    }

    private String alkalinityMessage() {
        String message = "Alkalinity: ";
        if (alkalinity < 80) {
            double poundsBicarb = (((volume / 10000) * 2.24 * (optimalAlkalinity - alkalinity)) / 16);
            message += String.format("Alkalinity low. Add %.1f lbs sodium bicarbonate", poundsBicarb);
            alkalinityCorrect = false;
        } else if (alkalinity > 120) {
            double flOzMuriatic = ((volume / 10000) * 2.6 * (alkalinity - optimalAlkalinity));
            message += String.format("Alkalinity high. Add %.1f fl oz muriatic acid.", flOzMuriatic);
            alkalinityCorrect = false;
        } else {
            alkalinityCorrect = true;
            message += "Alkalinity fine";
        }
        return message;
    }

    private String calciumHardnessMessage() {
        String message = "Calcium Hardness: ";
        if (calciumHardness < 200) {
            double poundsCalciumChloride100 = ((volume/10000) * 1.44 * (optimalCalciumHardness - calciumHardness)) / 16;
            double poundsCalciumChloride77 = ((volume/10000) * 1.92 * (optimalCalciumHardness - calciumHardness)) / 16;
            message += String.format("Add %.1f lbs 100%% calcium chloride, or %.1f lbs 77%% calcium chloride", poundsCalciumChloride100, poundsCalciumChloride77);
        } else if (calciumHardness > 400) {
            message += "Calcium hardness too high, partial drain and refill to reduce.";
        } else {
            message += "Calcium hardness is fine.";
        }
        return message;
    }

    private String cyanuricAcidMessage() {
        String message = "Cyanuric acid: ";
        if (cyanuricAcid < 20) {
            double poundsStabilizer = ((volume/10000) * 1.3 * (optimalCyanuricAcid - cyanuricAcid)) / 16;
            message += String.format("Add %.1f lbs dry stabilizer", poundsStabilizer);
        } else if (cyanuricAcid > 80) {
            message += "Cyanuric acid too high, partial drain and refill.";
        } else {
            message += "Cyanuric acid is fine.";
        }
        return message;
    }

    public String[] generateReport() {
        String[] report = {
                alkalinityMessage(),
                phMessage(),
                chlorineMessage(),
                calciumHardnessMessage(),
                cyanuricAcidMessage()};
        return report;
    }

}
