package dicoding.adrian.consumerapp.utils;

import android.database.Cursor;

import java.util.ArrayList;

import dicoding.adrian.consumerapp.entity.MovieItem;

import static android.provider.BaseColumns._ID;
import static dicoding.adrian.consumerapp.db.DatabaseContract.MovieColumns.POSTER;
import static dicoding.adrian.consumerapp.db.DatabaseContract.MovieColumns.SCORE;


public class MappingHelper {
    public static ArrayList<MovieItem> mapCursorToArrayList(Cursor moviesCursor) {

        ArrayList<MovieItem> moviesList = new ArrayList<>();

        while (moviesCursor.moveToNext()) {
            int id = moviesCursor.getInt(moviesCursor.getColumnIndexOrThrow(_ID));
            String poster = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(POSTER));
            double score = moviesCursor.getDouble(moviesCursor.getColumnIndexOrThrow(SCORE));
            moviesList.add(new MovieItem(id, poster, score));
        }

        return moviesList;
    }
}
