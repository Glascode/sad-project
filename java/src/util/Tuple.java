package util;

public class Tuple<Double, Y> {

    private double arg0;
    private Y arg1;

    public Tuple(double arg0, Y arg1) {
        this.arg0 = arg0;
        this.arg1 = arg1;
    }

    public Tuple() {
        this(0, null);
    }

    public void setTuple(double arg0, Y arg1) {
        this.arg0 = arg0;
        this.arg1 = arg1;
    }

    public void set0(double arg) {
        this.arg0 = arg;
    }

    public double get0() {
        return arg0;
    }

    public void set1(Y arg) {
        this.arg1 = arg;
    }

    public Y get1() {
        return arg1;
    }

}
