package numericalIntegration;

import userIO.Parser;

class numIntegrationConditioner {
    double[] generateTable(double[] table, Limits limit, IntervalWithStep measurements, String function) {
        table[0] = limit.lLimit;
        table[measurements.interval] = limit.uLimit;

        //generate x
        for (int i = 1; i < measurements.interval; i++) {
            table[i] = table[i - 1] + measurements.h;
        }

        // generates f(x)
        for (int i = 0; i < table.length; i++) {
            table[i] = getFunctionVal(function, table[i]);
        }
        //returns f(x)
        return table;
    }
    
    private double getFunctionVal(String function, double value){
        return Parser.parse(function, value);
    }
}
