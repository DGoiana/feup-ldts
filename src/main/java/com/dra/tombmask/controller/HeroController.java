package com.dra.tombmask.controller;

import com.dra.tombmask.Game;
import com.dra.tombmask.model.*;
import com.dra.tombmask.state.GameState;
import com.dra.tombmask.utils.ACTION;
import com.dra.tombmask.utils.DIRECTION;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class HeroController extends AbstractController<Arena>{
    private final Hero hero = getModel().getHero();
    public HeroController(Arena arena){
        super(arena);
    }

    public Element moveHero(){
        if(hero.isShielded()) hero.setShieldedTime(hero.getShieldedTime() - 0.08);
        if(hero.isMagnet()) hero.setMagnetTime(hero.getMagnetTime() - 0.08);
        int x = hero.getPosition().getX();
        int y = hero.getPosition().getY();
        switch (hero.getDirection()){
            case UP:
                y--;
                if(!getModel().isEmpty(new Position(x,y))){
                    hero.setDirection(DIRECTION.IDLE);
                    break;
                }
                if(checkCollision(new Position(x, y)) != null) return checkCollision(new Position(x,y));
                hero.setPosition(new Position(x,y));
                break;
            case DOWN:
                y++;
                if(!getModel().isEmpty(new Position(x,y))){
                    hero.setDirection(DIRECTION.IDLE);
                    break;
                }
                if(checkCollision(new Position(x, y)) != null) return checkCollision(new Position(x,y));
                hero.setPosition(new Position(x,y));
                break;
            case LEFT:
                x--;
                if(!getModel().isEmpty(new Position(x,y))){
                    hero.setDirection(DIRECTION.IDLE);
                    break;
                }
                if(checkCollision(new Position(x, y)) != null) return checkCollision(new Position(x,y));
                hero.setPosition(new Position(x,y));
                break;
            case RIGHT:
                x++;
                if(!getModel().isEmpty(new Position(x,y))){
                    hero.setDirection(DIRECTION.IDLE);
                    break;
                }
                if(checkCollision(new Position(x, y)) != null) return checkCollision(new Position(x,y));
                hero.setPosition(new Position(x,y));
                break;
            case IDLE:
                break;
        }
        return null;
    }

    private Element checkCollision(Position position){
        if(getModel().getElementAtPosition(position) instanceof PowerUp){
            ((PowerUp) getModel().getElementAtPosition(position)).getStrategy().execute(getModel());
            return null;
        }
        else if(getModel().getElementAtPosition(position) instanceof Collectable){
            ((Collectable) getModel().getElementAtPosition(position)).collect(getModel());
            return null;
        }
        return getModel().getElementAtPosition(position);
    }

    private void collectAdjacentCoins(){
        int heroX = hero.getPosition().getX();
        int heroY = hero.getPosition().getY();
        for(int x = heroX - 1; x <= heroX + 1; x++){
            for(int y = heroY - 1; y <= heroY + 1; y++){
                Element e = getModel().getElementAtPosition(new Position(x,y));
                if(e instanceof Collectable) ((Collectable) e).collect(getModel());
            }
        }
    }

    @Override
    public void executeState(Game game, ACTION action) throws IOException, InterruptedException {
        switch (action){
            case UP:
                if(hero.getDirection() != DIRECTION.IDLE) break;
                hero.setDirection(DIRECTION.UP);
                break;
            case DOWN:
                if(hero.getDirection() != DIRECTION.IDLE) break;
                hero.setDirection(DIRECTION.DOWN);
                break;
            case LEFT:
                if(hero.getDirection() != DIRECTION.IDLE) break;
                hero.setDirection(DIRECTION.LEFT);
                break;
            case RIGHT:
                if(hero.getDirection() != DIRECTION.IDLE) break;
                hero.setDirection(DIRECTION.RIGHT);
                break;
        }
        if(hero.isMagnet()) collectAdjacentCoins();
        if(moveHero() instanceof EndLevel) {
            game.setCurrentArena(game.currentArena + 1);
            try {
                String path = "./src/main/resources/levels/level"+game.currentArena;
                game.setState(new GameState(new Arena(60, 30,path)));
            }catch (IOException e){
                game.setState(null);
            }
        }
        else if((moveHero() instanceof Bat || moveHero() instanceof Spike)){
            if(hero.isShielded()){
                hero.setShieldedTime(0.0);
            }
            else{
                game.setState(null);
            }
        }
    }
}
