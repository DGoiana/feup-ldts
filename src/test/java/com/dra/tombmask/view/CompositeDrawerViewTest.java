package com.dra.tombmask.view;

import com.dra.tombmask.GUI.GUI;
import com.dra.tombmask.model.Coin;
import com.dra.tombmask.model.Hero;
import com.dra.tombmask.model.Position;
import com.dra.tombmask.model.Wall;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;

public class CompositeDrawerViewTest {
    @Test
    public void drawSingleTest() throws IOException {
        GUI gui = Mockito.mock(GUI.class);
        Hero hero = new Hero(new Position(0,0));
        HeroView heroView = Mockito.mock(HeroView.class);
        CompositeDrawerView compositeDrawerView = new CompositeDrawerView(gui);
        compositeDrawerView.draw(hero,heroView);

        Mockito.verify(heroView,Mockito.times(1)).draw(hero,gui);
    }
    @Test
    public void drawListTest() {
        GUI gui = Mockito.mock(GUI.class);
        Wall w1 = new Wall(0,0);
        Wall w2 = new Wall(1,0);
        Wall w3 = new Wall(2,0);
        List<Wall> wallList = Arrays.asList(w1,w2,w3);
        WallView wallView = Mockito.mock(WallView.class);
        CompositeDrawerView compositeDrawerView = new CompositeDrawerView(gui);
        compositeDrawerView.draw(wallList,wallView);

        Mockito.verify(wallView,Mockito.times(3)).draw(Mockito.any(Wall.class),eq(gui));
    }
}
