package PollutedWorld.region;

public class Region {

    private final String name;
    private final String world;
    private final int minX, maxX, minZ, maxZ;

    public Region(String name, String world, int minX, int maxX, int minZ, int maxZ) {
        this.name = name;
        this.world = world;
        this.minX = minX;
        this.maxX = maxX;
        this.minZ = minZ;
        this.maxZ = maxZ;
    }

    public String getWorld() {
        return world;
    }

    public boolean contains(int x, int z) {
        return x >= minX && x <= maxX && z >= minZ && z <= maxZ;
    }

    public int getMinX() { return minX; }
    public int getMaxX() { return maxX; }
    public int getMinZ() { return minZ; }
    public int getMaxZ() { return maxZ; }
}
