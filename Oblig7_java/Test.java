public class Test {
    private int x, y;

    public Test(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int hentX() { return x; }

    public void endreX(int x) { this.x = x; }

    public int hentY() { return y; }

    public void endreY(int y) { this.y = y; }

    @Override
    public String toString() { return "(" + x + "," + y + ")"; }
}
