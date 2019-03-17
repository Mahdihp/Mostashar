package ir.mostashar.model.doc;

/**
 * Created by mnm
 * User: mnm
 * Date: 3/13/19
 * Time: 21:13
 * http://github.com/ghaseminya
 */
public enum DocType {
    FILE(0),
    RESUME(1);

    public final int type;
    DocType(int type) {
        this.type = type;
    }

}
