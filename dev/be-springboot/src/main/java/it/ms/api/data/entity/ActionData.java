package it.ms.api.data.entity;

public class ActionData {

    private int x;
    private int y;
    private int actionCode;

    public ActionData(int x, int y, int actionCode) {
        this.x = x;
        this.y = y;
        this.actionCode = actionCode;
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
    public int getActionCode() {
        return actionCode;
    }
    public void setActionCode(int actionCode) {
        this.actionCode = actionCode;
    }
}