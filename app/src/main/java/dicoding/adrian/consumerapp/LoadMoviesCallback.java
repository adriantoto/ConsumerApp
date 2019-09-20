package dicoding.adrian.consumerapp;

import android.database.Cursor;

public interface LoadMoviesCallback {
    void postExecute(Cursor movies);
}
