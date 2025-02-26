package coworking.space.repository;

import coworking.space.entities.Workspace;
import coworking.space.entities.WorkspaceType;
import coworking.space.exceptions.DataAccessException;

import java.io.*;
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


    public void loadSpacesFromFile(String fileName) throws DataAccessException {
        File file = new File(fileName);
        if (!file.exists()) {
            
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 3) {
                    throw new DataAccessException("Invalid data format in file: " + line);
                }
                Workspace workspace = new Workspace();
                try {
                    workspace.setWorkspaceType(Enum.valueOf(WorkspaceType.class, parts[0].trim()));
                } catch (IllegalArgumentException e) {
                    throw new DataAccessException("Invalid workspace type in file: " + parts[0].trim(), e);
                }
                try {
                    workspace.setPrice(Double.parseDouble(parts[1].trim()));
                } catch (NumberFormatException e) {
                    throw new DataAccessException("Invalid price in file: " + parts[1].trim(), e);
                }
                workspace.setAvailable(Boolean.parseBoolean(parts[2].trim()));
                workspaces.add(workspace);
            }
        } catch (IOException e) {
            throw new DataAccessException("Error reading file: " + fileName, e);
        }
    }


    public void saveSpacesToFile(String fileName) throws DataAccessException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Workspace workspace : workspaces) {
                String line = workspace.getWorkspaceType().name() + "," +
                        workspace.getPrice() + "," +
                        workspace.isAvailable();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new DataAccessException("Error writing to file: " + fileName, e);
        }
    }
}
