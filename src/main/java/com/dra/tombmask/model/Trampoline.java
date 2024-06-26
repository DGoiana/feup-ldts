package com.dra.tombmask.model;

import com.dra.tombmask.utils.CORNER;

public class Trampoline extends Element{
    private final CORNER corner;
    public Trampoline(Position position, CORNER corner) {
        super(position.getX(), position.getY());
        this.corner = corner;
    }

    public CORNER getCorner() {
        return corner;
    }
}
