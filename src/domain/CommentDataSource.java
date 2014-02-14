package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicolas
 * Date: 09/09/13
 * Time: 13:23
 */
public class CommentDataSource {

    public static List<Comment> comments = new ArrayList<Comment>();
    static {
        for (int i = 0 ; i < 1000 ; i++){
            comments.add(new Comment());
        }
    }


}
