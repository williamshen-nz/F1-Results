package Formula1.Model;

import Helpers.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

public class StartingGrid {
    private ArrayList<GridPosition> gridPositions;
    private String notes;

    public StartingGrid() {
        this.gridPositions = new ArrayList<>(20);
    }

    public ArrayList<GridPosition> getGridPositions() {
        return gridPositions;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getNotes() {
        return notes;
    }

    public void add(GridPosition gridPosition) {
        gridPositions.add(gridPosition);
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return JSON.stringify(this);
    }
}
