package am.adrian.dungeonkeeper.common;

import am.adrian.dungeonkeeper.helper.Point2D;

public interface ConsoleGameObject extends GameObject {

    Point2D getCoords();

    char getConsoleChar();
}