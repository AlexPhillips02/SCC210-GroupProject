package com.mygdx.Board;

public class AlgorithmSquares extends Squares
{
    //All these used for shortest path algorithm
    public AlgorithmSquares previous;
    public AlgorithmSquares next;
    public Boolean visited = false;
    public int distance = -1;

    public AlgorithmSquares(int x, int y)
    {
        super(x, y);
    }
}
