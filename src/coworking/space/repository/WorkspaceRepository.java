package coworking.space.repository;

import coworking.space.entities.Workspace;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class WorkspaceRepository {

    private Set<Workspace> workspaces;

    public WorkspaceRepository() {
        workspaces = new HashSet<>();
    }

    public void addWorkspace(Workspace workspace) {
        workspaces.add(workspace);
    }

    public Workspace getWorkspaceById(long id) {
        return workspaces.stream()
                .filter(w -> w.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No such workspace"));
    }

    public void deleteWorkspaceById(long id) {
        Workspace workspace = workspaces.stream()
                .filter(w -> w.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No such workspace"));
        workspaces.remove(workspace);
    }

    public Set<Workspace> getOnlyAvailableSpaces() {
        return workspaces.stream()
                .filter(Workspace::isAvailable)
                .collect(Collectors.toSet());
    }
}
