package com.dra.tombmask.state;

import com.dra.tombmask.GUI.GUI;
import com.dra.tombmask.Game;
import com.dra.tombmask.controller.ArenaController;
import com.dra.tombmask.model.Arena;
import com.dra.tombmask.utils.ACTION;
import com.dra.tombmask.view.GameView;

import java.io.IOException;

public class GameState implements AbstractState{
    public ArenaController arenaController;
    public GameView gameViewer;
    public GameState(Arena arena) throws IOException {
        arenaController = new ArenaController(arena);
        gameViewer = new GameView(arena);
    }
    @Override
    public void nextState(Game game, GUI gui) throws IOException, InterruptedException {
        ACTION action = gui.getAction(gui.getUserInput());
        arenaController.executeState(game, action);
        gameViewer.draw(gui);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        GameState gameState = (GameState) obj;

        return gameState.arenaController.getModel().getWidth() == arenaController.getModel().getWidth()
                && gameState.arenaController.getModel().getHeight() == arenaController.getModel().getHeight()
                && gameState.arenaController.getModel().getPath().equals(arenaController.getModel().getPath());
    }
}
