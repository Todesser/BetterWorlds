package de.todesser.BetterWorlds.core.world;

public enum WorldType {

    EMPTY(new EmptyWorld());

    private IWorldCreator worldCreator;

    WorldType(IWorldCreator worldCreator) {
        this.worldCreator = worldCreator;
    }

    public IWorldCreator getWorldCreator() {
        return worldCreator;
    }

}
