package numericalIntegration;
import userIO.Parser;
public class IntervalWithStep {
    final double uLimit;
    final double lLimit;
    final int interval;
    final double h;
    
    IntervalWithStep(String uLimit, String lLimit, String interval){
        this.uLimit = Parser.parseConstant(uLimit);
        this.lLimit = Parser.parseConstant(lLimit);
        this.interval = Integer.parseInt(interval);
        this.h = (this.uLimit - this.lLimit) / (double) this.interval;
    }
}
