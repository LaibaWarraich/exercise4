package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.observer.Observable;
import at.ac.fhcampuswien.fhmdb.observer.Observer;
import com.j256.ormlite.dao.Dao;

import java.util.ArrayList;
import java.util.List;

public class WatchlistRepository implements Observable {

    private static WatchlistRepository instance;
    private Dao<WatchlistMovieEntity, Long> dao;
    private final List<Observer> observers;

    private WatchlistRepository() throws DataBaseException {
        try {
            this.dao = DatabaseManager.getInstance().getWatchlistDao();
            this.observers = new ArrayList<>();
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public static WatchlistRepository getInstance() throws DataBaseException {
        if (instance == null) {
            synchronized (WatchlistRepository.class) {
                if (instance == null) {
                    instance = new WatchlistRepository();
                }
            }
        }
        return instance;
    }

    public List<WatchlistMovieEntity> getWatchlist() throws DataBaseException {
        try {
            return dao.queryForAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error while reading watchlist");
        }
    }

    public int addToWatchlist(WatchlistMovieEntity movie) throws DataBaseException {
        try {
            // only add movie if it does not exist yet
            long count = dao.queryBuilder().where().eq("apiId", movie.getApiId()).countOf();
            if (count == 0) {
                int result = dao.create(movie);
                notifyObservers("Movie added to watchlist successfully.");
                return result;
            } else {
                notifyObservers("Movie is already in the watchlist.");
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error while adding to watchlist");
        }
    }

    public int removeFromWatchlist(String apiId) throws DataBaseException {
        try {
            int result = dao.delete(dao.queryBuilder().where().eq("apiId", apiId).query());
            notifyObservers("Movie removed from watchlist successfully.");
            return result;
        } catch (Exception e) {
            throw new DataBaseException("Error while removing from watchlist");
        }
    }

    @Override
    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
