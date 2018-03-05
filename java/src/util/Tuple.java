package util;

public class Tuple<X, Y> {

    private X arg0;
    private Y arg1;

    public Tuple(X arg0, Y arg1) {
        this.arg0 = arg0;
        this.arg1 = arg1;
    }

    public Tuple() {
        this(null, null);
    }

    public void setTuple(X arg0, Y arg1) {
        this.arg0 = arg0;
        this.arg1 = arg1;
    }

    public void set0(X arg) {
        this.arg0 = arg;
    }

    public X get0() {
        return arg0;
    }

    public void set1(Y arg) {
        this.arg1 = arg;
    }

    public Y get1() {
        return arg1;
    }

}
