package coworking.space.entities;

import java.util.Objects;

public class Workspace {
    
    private long id;
    private WorkspaceType workspaceType;
    private double price;
    private boolean available;
    private static long autoImplementedId = 1;
    
    public Workspace() {
        available = true;
        id = autoImplementedId;
        autoImplementedId++;
    }

    public long getId() {
        return id;
    }
    
    public WorkspaceType getWorkspaceType() {
        return workspaceType;
    }

    public void setWorkspaceType(WorkspaceType workspaceType) {
        this.workspaceType = workspaceType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Workspace{" +
                "id=" + id +
                ", workspaceType=" + workspaceType +
                ", price=" + price +
                ", available=" + available +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Workspace workspace = (Workspace) o;
        return id == workspace.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
