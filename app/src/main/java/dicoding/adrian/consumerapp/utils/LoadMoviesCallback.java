package dicoding.adrian.consumerapp.utils;

import android.database.Cursor;

public interface LoadMoviesCallback {
    void postExecute(Cursor movies);
}
