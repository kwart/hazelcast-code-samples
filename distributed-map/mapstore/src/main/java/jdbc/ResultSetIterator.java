package jdbc;

import java.io.Closeable;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

/**
 * A Closable iterator over a ResultSet. Returns the first column as an object
 **/
public class ResultSetIterator<T> implements Iterator<T>, Closeable {

    private static final Object NOT_LOADED = new Object();

    private ResultSet resultSet;
    private Object nextObject = NOT_LOADED;

    public ResultSetIterator(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    @Override
    public boolean hasNext() {
        try {
            checkNextLoaded();
            return nextObject != NOT_LOADED;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T next() {
        try {
            checkNextLoaded();
            Object currentObject = nextObject;
            nextObject = NOT_LOADED;
            return (T) currentObject;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {
        try {
            resultSet.close();
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    private void checkNextLoaded() throws SQLException {
        if (nextObject == NOT_LOADED) {
            if (resultSet.next()) {
                nextObject = resultSet.getObject(1);
            }
        }
    }
}
