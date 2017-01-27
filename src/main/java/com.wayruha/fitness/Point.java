package com.wayruha.fitness;

public  class Point {

    int x,y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point point){
        this.x=point.getX();
        this.y=point.getY();
    }

    public Point clone(){  // for cloning
        return new Point(x,y);
    }

    public void setLocation(int x,int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;

    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }



    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
}
