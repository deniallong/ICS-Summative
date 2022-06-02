package utils;

public class Pair {
    
    public int a;
    public int b;

    public Pair(int a, int b)
    {
        this.a = a;
        this.b = b;
    }

    public String toString()
    {
        return "(" + a + ", " + b + ")";
    }

    public boolean equals(Pair pair)
    {
        return this.a == pair.a && this.b == pair.b;
    }
}
