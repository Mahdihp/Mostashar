package ir.mostashar.model.doc;

/**
 * Created by mnm
 * User: mnm
 * Date: 3/13/19
 * Time: 21:13
 * http://github.com/ghaseminya
 */
public enum DocType {
    File(0),
    Resume(1);
    public final int type;

    public DocType(int type) {
        this.type = type;
    }

}
