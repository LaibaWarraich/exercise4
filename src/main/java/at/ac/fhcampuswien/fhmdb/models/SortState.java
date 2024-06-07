package at.ac.fhcampuswien.fhmdb.models;

import javafx.collections.ObservableList;

public interface SortState {
    void sort(ObservableList<Movie> movies);
}
