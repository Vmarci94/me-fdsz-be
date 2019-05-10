package hu.me.fdsz.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Util {

    public static <T> List<T> toList(Iterable<T> iterable) {

        if (iterable instanceof Collection) {
            return (List<T>) iterable;
        }

        List<T> cltn = new ArrayList<>();
        iterable.iterator().forEachRemaining(cltn::add);
        return cltn;
    }

}
