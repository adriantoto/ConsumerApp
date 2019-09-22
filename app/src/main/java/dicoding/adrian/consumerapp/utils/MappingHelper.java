package dicoding.adrian.consumerapp.utils;

import android.database.Cursor;

import java.util.ArrayList;

import dicoding.adrian.consumerapp.entity.MovieItem;

import static android.provider.BaseColumns._ID;
import static dicoding.adrian.consumerapp.db.DatabaseContract.MovieColumns.BACKDROP;
import static dicoding.adrian.consumerapp.db.DatabaseContract.MovieColumns.OVERVIEW;
import static dicoding.adrian.consumerapp.db.DatabaseContract.MovieColumns.POSTER;
import static dicoding.adrian.consumerapp.db.DatabaseContract.MovieColumns.RELEASED;
import static dicoding.adrian.consumerapp.db.DatabaseContract.MovieColumns.SCORE;
import static dicoding.adrian.consumerapp.db.DatabaseContract.MovieColumns.TITLE;


public class MappingHelper {
    public static ArrayList<MovieItem> mapCursorToArrayList(Cursor moviesCursor) {

        ArrayList<MovieItem> moviesList = new ArrayList<>();

        while (moviesCursor.moveToNext()) {
            int id = moviesCursor.getInt(moviesCursor.getColumnIndexOrThrow(_ID));
            String poster = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(POSTER));
            double score = moviesCursor.getDouble(moviesCursor.getColumnIndexOrThrow(SCORE));
            String title = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(TITLE));
            String overview = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(OVERVIEW));
            String backdrop = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(BACKDROP));
            String released = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(RELEASED));
            moviesList.add(new MovieItem(id, poster, score, title, overview, backdrop, released));
        }

        return moviesList;
    }
}
