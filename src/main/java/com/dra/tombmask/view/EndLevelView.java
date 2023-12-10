package com.dra.tombmask.view;

import com.dra.tombmask.GUI.GUI;
import com.dra.tombmask.model.EndLevel;
import com.dra.tombmask.model.Hero;
import com.dra.tombmask.utils.ElementsSymbol;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

public class EndLevelView implements ElementView<EndLevel>{
    @Override
    public void draw(EndLevel endLevel, GUI gui,TextGraphics textGraphics) {
        TerminalPosition terminalPosition = new TerminalPosition(endLevel.getPosition().getX(),endLevel.getPosition().getY());
        textGraphics.putString(terminalPosition, ElementsSymbol.endLevelElement.symbol);
    }
    public void draw(EndLevel endLevel, GUI gui){
        TextGraphics textGraphics = gui.getScreen().newTextGraphics();
        draw(endLevel,gui,textGraphics);
    }
}
