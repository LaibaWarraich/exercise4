package at.ac.fhcampuswien.fhmdb.models;

import javafx.collections.ObservableList;
import java.util.Comparator;

public class DescendingState implements SortState {
    @Override
    public void sort(ObservableList<Movie> movies) {
        movies.sort(Comparator.comparing(Movie::getTitle).reversed());
    }

}
