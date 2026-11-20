package numericalIntegration;

import userIO.Parser;

class Limits {
    double uLimit;
    double lLimit;
    
    Limits(String uLimit, String lLimit){
        this.uLimit = Parser.parseConstant(uLimit);
        this.lLimit = Parser.parseConstant(lLimit);
    }
}
